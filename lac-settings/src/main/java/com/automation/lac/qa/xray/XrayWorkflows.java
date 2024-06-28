package com.automation.lac.qa.xray;

import com.automation.lac.qa.environment.Environments;
import com.automation.lac.qa.utils.Constants;
import com.automation.lac.qa.utils.CustomException;
import com.automation.lac.qa.xray.api.XrayApi;
import com.automation.lac.qa.xray.builder.XrayBuilder;
import com.automation.lac.qa.xray.dto.Iteration;
import com.automation.lac.qa.xray.dto.Parameter;
import com.automation.lac.qa.xray.dto.UpdateExecutionRequest;
import com.automation.lac.qa.xray.dto.graphql.Data;
import com.automation.lac.qa.xray.dto.graphql.GetTests;
import com.automation.lac.qa.xray.dto.graphql.GraphqlRequest;
import com.automation.lac.qa.xray.dto.graphql.GraphqlResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class XrayWorkflows {

  private XrayWorkflows() {
  }

  protected static XrayApi xrayApi = new XrayApi();
  protected static XrayBuilder xrayBuilder = new XrayBuilder();
  protected static List<Iteration> iterations = new ArrayList<>();

  /**
   * Update Test Execution with current Test Case
   *
   * @param testExecutionId  String
   * @param testContext      ITestContext
   * @param testResultDetail ITestResult
   */
  public static void updateTestExecution(String testExecutionId, ITestContext testContext,
                                         ITestResult testResultDetail, String testStatus) {
    ITestNGMethod testNgMethod = testResultDetail.getMethod();
    String summary = testContext.getSuite().getXmlSuite().getName();
    final boolean testHasDataProvider = !Arrays.asList(testResultDetail.getParameters()).isEmpty();
    final Xray xrayAnnotation = testResultDetail.getMethod().getConstructorOrMethod().getMethod()
      .getAnnotation(Xray.class);
    if (xrayAnnotation != null) {
      for (String testKey : xrayAnnotation.test()) {
        GraphqlResponse graphqlResponse = getTestCaseFromXray(testKey);
        if (graphqlResponse.getData().getGetTests().getTotal() == 1) {
          log.info("The Test ID '{}' exist", testKey);
          UpdateExecutionRequest request =
            xrayBuilder.updateExecutionRequest(testExecutionId, Constants.PROJECT_ID,
              "[" + Environments.byName(System.getProperty("environment")).getName() + "]"
                + " Execution of automation framework - " + summary,
              testKey, testStatus, addTestComment(testResultDetail),
              graphqlResponse);
          if (testHasDataProvider) {
            resetIteration(testNgMethod);
            List<Parameter> parameters = addIterationParams(testResultDetail.getParameters());
            addIterationBuilder(testStatus, parameters);
            int limit = getNumberOfDataProviderRows(testResultDetail);
            executeTestInLastIteration(testNgMethod, request, graphqlResponse, limit);
          } else {
            executeTestWithoutDataProvider(request, graphqlResponse);
          }
        } else {
          log.error("The Test ID '{}' doesn't exist", testKey);
        }
      }
    }
  }

  private static GraphqlResponse getTestCaseFromXray(String testKey) {
    if (!testKey.isEmpty()) {
      GraphqlRequest graphqlRequest = xrayBuilder.getGraphqlRequest(testKey);
      return xrayApi.getTestInfo(graphqlRequest);
    } else {
      return emptyResponse();
    }
  }

  private static void executeTestWithoutDataProvider(UpdateExecutionRequest request,
                                                     GraphqlResponse graphqlResponse) {
    if (graphqlResponse.getData().getGetTests().getResults().get(0).getSteps().isEmpty()) {
      xrayApi.postImportExecution(
        removeAttributeDto(new String[] {"testInfo", "iterations"}, request));
    } else {
      xrayApi.postImportExecution(removeAttributeDto(new String[] {"iterations"}, request));
    }
  }

  private static void executeTestInLastIteration(ITestNGMethod testNgMethod,
                                                 UpdateExecutionRequest request,
                                                 GraphqlResponse graphqlResponse, int limit) {
    if (testNgMethod.getCurrentInvocationCount() == limit) {
      request.getTests().get(0).setIterations(iterations);
      String testIterationStatus = finalTestStatus(request);
      request.getTests().get(0).setStatus(testIterationStatus);
      if (graphqlResponse.getData().getGetTests().getResults().get(0).getSteps().isEmpty()) {
        xrayApi.postImportExecution(removeAttributeDto(new String[] {"testInfo"}, request));
      } else {
        xrayApi.postImportExecution(removeAttributeDto(new String[] {""}, request));
      }
    }
  }

  private static void resetIteration(ITestNGMethod testNgMethod) {
    if (testNgMethod.getCurrentInvocationCount() == 1) {
      iterations = new ArrayList<>();
    }
  }

  private static String finalTestStatus(UpdateExecutionRequest request) {
    for (Iteration iteration : request.getTests().get(0).getIterations()) {
      if (!iteration.getStatus().equals("PASSED")) {
        return "FAILED";
      }
    }
    return "PASSED";
  }

  private static List<Parameter> addIterationParams(Object[] paramsMethod) {
    List<Parameter> parameters = new ArrayList<>();
    int count = 0;
    for (Object param : paramsMethod) {
      parameters.add(Parameter.builder()
        .name("arg" + count)
        .value(String.valueOf(param))
        .build());
      count++;
    }
    return parameters;
  }

  private static void addIterationBuilder(String testStatus, List<Parameter> parameters) {
    iterations.add(Iteration.builder()
      .status(testStatus)
      .parameters(parameters)
      .build());
  }

  @SneakyThrows
  private static int getNumberOfDataProviderRows(ITestResult testResult) {
    int totalRecords = 0;
    Object instance = testResult.getInstance();
    ITestNGMethod testNgMethod = testResult.getMethod();
    Method testMethod = testNgMethod.getConstructorOrMethod().getMethod();
    Test testMethodTestAnnotation = testMethod.getAnnotation(Test.class);
    String dataProviderName = testMethodTestAnnotation.dataProvider();
    Method[] allTestClassMethods = instance.getClass().getMethods();
    for (Method method : allTestClassMethods) {
      if (method.isAnnotationPresent(DataProvider.class)) {
        DataProvider dataProviderAnnotation = method.getAnnotation(DataProvider.class);
        String thisDataProviderName = dataProviderAnnotation.name();
        if (dataProviderName.equals(thisDataProviderName)) {
          Object[][] dataProvider = (Object[][]) method.invoke(instance);
          totalRecords = dataProvider.length;
        }
      }
    }
    return totalRecords;
  }

  private static String removeAttributeDto(String[] property, Object dtoTarget) {
    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept(property);
    FilterProvider filters = new SimpleFilterProvider().addFilter("customTest", filter);
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writer(filters).writeValueAsString(dtoTarget);
    } catch (JsonProcessingException e) {
      throw new CustomException("Could not modify model.", e);
    }
  }

  private static String addTestComment(ITestResult testResultDetail) {
    return testResultDetail.isSuccess()
      ? "Successful execution." : "Failed execution: " + testResultDetail.getThrowable();
  }

  /**
   * Generate GraphqlResponse with 0 records
   *
   * @return GraphqlResponse
   */
  private static GraphqlResponse emptyResponse() {
    return GraphqlResponse.builder()
      .data(Data.builder()
        .getTests(GetTests.builder()
          .total(0)
          .build())
        .build())
      .build();
  }

}
