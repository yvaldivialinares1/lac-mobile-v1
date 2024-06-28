package com.automation.lac.qa.listener;


import static com.automation.lac.qa.utils.Constants.JENKINS_ENABLED;
import static com.automation.lac.qa.utils.Constants.XRAY_ENABLED;

import com.automation.lac.qa.allure.AllureLogger;
import com.automation.lac.qa.driver.AppiumConstants;
import com.automation.lac.qa.utils.StringOperations;
import com.automation.lac.qa.xray.XrayWorkflows;
import com.automation.lac.qa.xray.api.XrayApi;
import com.automation.lac.qa.xray.builder.XrayBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.testng.IExecutionListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class TestListener
  implements ISuiteListener, ITestListener, IExecutionListener,
  IInvokedMethodListener {

  protected AllureLogger report = new AllureLogger();
  @Getter
  @Setter
  private static String testExecutionId;

  @Override
  public void onExecutionStart() {
    log.info("Cleaning Allure Report.");
    IExecutionListener.super.onExecutionStart();
    report.deleteOldAllureReports();

    if (XRAY_ENABLED || JENKINS_ENABLED) {
      XrayApi xrayApi = new XrayApi();
      XrayBuilder xrayBuilder = new XrayBuilder();
      setTestExecutionId(xrayApi.createTestExecution(xrayBuilder.getTestExecution()).getKey());
      log.info("Test Execution ID: {}", getTestExecutionId());
    }
  }

  @Override
  public void onStart(ISuite suite) {
    ISuiteListener.super.onStart(suite);
    log.info("Setting Allure Environment");
    AppiumConstants.setAppiumVariables(suite.getXmlSuite());
    report.setEnvironment();
  }

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    IInvokedMethodListener.super.afterInvocation(method, testResult);
  }

  @Override
  public void onTestStart(ITestResult testResult) {
    ITestListener.super.onTestStart(testResult);
    log.info("** Starting test: {}", testResult.getName());
  }

  @Override
  public void onTestSuccess(ITestResult testResult) {
    ITestListener.super.onTestSuccess(testResult);
    log.info("**** Test finished with status: PASSED");
    xrayExecution(testResult, "PASSED");
  }

  @Override
  public void onTestFailure(ITestResult testResult) {
    ITestListener.super.onTestSuccess(testResult);
    log.error("**** Test finished with status: FAILED");
    xrayExecution(testResult, "FAILED");
  }

  @Override
  public void onTestSkipped(ITestResult testResult) {
    ITestListener.super.onTestSkipped(testResult);
    log.warn("**** Test finished with status: SKIPPED");
    xrayExecution(testResult, "SKIPPED");
  }

  private void xrayExecution(ITestResult testResult, String testStatus) {
    ITestContext testContext = testResult.getTestContext();
    if (XRAY_ENABLED || JENKINS_ENABLED) {
      XrayWorkflows.updateTestExecution(getTestExecutionId(), testContext, testResult,
        testStatus);
      StringOperations.clearSecrets();
    }
  }
}
