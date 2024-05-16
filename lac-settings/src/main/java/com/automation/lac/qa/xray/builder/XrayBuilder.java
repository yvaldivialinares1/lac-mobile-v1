package com.automation.lac.qa.xray.builder;


import static com.automation.lac.qa.utils.Constants.PROJECT_ID;
import static com.automation.lac.qa.utils.Constants.XRAY_CLIENT_ID;
import static com.automation.lac.qa.utils.Constants.XRAY_CLIENT_SECRET;

import com.automation.lac.qa.xray.JiraProject.TestExecutionIssue;
import com.automation.lac.qa.xray.dto.AuthenticateRequest;
import com.automation.lac.qa.xray.dto.Fields;
import com.automation.lac.qa.xray.dto.Info;
import com.automation.lac.qa.xray.dto.IssueRequest;
import com.automation.lac.qa.xray.dto.IssueType;
import com.automation.lac.qa.xray.dto.Project;
import com.automation.lac.qa.xray.dto.Test;
import com.automation.lac.qa.xray.dto.TestInfo;
import com.automation.lac.qa.xray.dto.UpdateExecutionRequest;
import com.automation.lac.qa.xray.dto.graphql.GraphqlRequest;
import com.automation.lac.qa.xray.dto.graphql.GraphqlResponse;
import com.automation.lac.qa.xray.dto.graphql.Step;
import com.automation.lac.qa.xray.dto.graphql.Variables;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XrayBuilder {

  public XrayBuilder() {
    // Do nothing because of X and Y.
  }

  private static final String TEST_EXECUTION_DESCRIPTION =
    "This execution is automatically created by the automation framework";

  /**
   * return model of Authenticate
   *
   * @return Authenticate
   */
  public AuthenticateRequest getCredentials() {
    return AuthenticateRequest.builder()
      .clientId(XRAY_CLIENT_ID)
      .clientSecret(XRAY_CLIENT_SECRET)
      .build();
  }

  /**
   * set model to add and update test into test execution
   *
   * @param testExecutionId      String
   * @param projectId            String
   * @param testExecutionSummary String
   * @param testKey              String
   * @param testStatus           String
   * @param testComment          String
   * @param graphqlResponse      GraphqlResponse
   * @return UpdateExecutionRequest
   */
  public UpdateExecutionRequest updateExecutionRequest(String testExecutionId, String projectId,
                                                       String testExecutionSummary,
                                                       String testKey, String testStatus,
                                                       String testComment,
                                                       GraphqlResponse graphqlResponse) {
    if (graphqlResponse.getData().getGetTests().getResults().get(0).getSteps().isEmpty()) {
      return UpdateExecutionRequest.builder()
        .testExecutionKey(testExecutionId)
        .info(Info.builder()
          .project(projectId)
          .summary(testExecutionSummary)
          .description(TEST_EXECUTION_DESCRIPTION)
          .build())
        .tests(Collections.singletonList(setTest(testKey, testStatus, testComment)))
        .build();
    } else {
      return UpdateExecutionRequest.builder()
        .testExecutionKey(testExecutionId)
        .info(Info.builder()
          .project(projectId)
          .summary(testExecutionSummary)
          .description(TEST_EXECUTION_DESCRIPTION)
          .build())
        .tests(
          Collections.singletonList(setTest(testKey, testStatus, testComment, graphqlResponse)))
        .build();
    }
  }

  /**
   * @param testKey String
   * @param status  String
   * @param comment String
   * @return Test
   */
  public Test setTest(String testKey, String status, String comment) {
    return Test.builder()
      .testKey(testKey)
      .status(status)
      .comment(comment)
      .build();
  }

  /**
   * @param testKey         String
   * @param status          String
   * @param comment         String
   * @param graphqlResponse GraphqlResponse
   * @return Test
   */
  public Test setTest(String testKey, String status, String comment,
                      GraphqlResponse graphqlResponse) {
    List<com.automation.lac.qa.xray.dto.Step> steps = new ArrayList<>();
    for (Step step : graphqlResponse.getData()
      .getGetTests().getResults().get(0).getSteps()) {
      steps.add(com.automation.lac.qa.xray.dto.Step.builder()
        .action(step.getAction())
        .data(step.getData())
        .result(step.getResult())
        .build());
    }
    return Test.builder()
      .testKey(testKey)
      .status(status)
      .comment(comment)
      .testInfo(TestInfo.builder()
        .steps(steps)
        .type(graphqlResponse.getData().getGetTests().getResults().get(0).getTestType().getName())
        .build())
      .build();
  }

  /**
   * model of issue body request to create Test Execution
   *
   * @return IssueRequest
   */
  public IssueRequest getTestExecution() {
    return IssueRequest.builder()
      .fields(Fields.builder()
        .project(Project.builder()
          .key(PROJECT_ID)
          .build())
        .summary("Execution of automation framework")
        .description(TEST_EXECUTION_DESCRIPTION)
        .issuetype(IssueType.builder()
          .id(TestExecutionIssue.getJiraIssueCode(PROJECT_ID))
          .build())
        .build())
      .build();
  }

  /**
   * Query to get the test case information
   *
   * @param testKey String
   * @return GraphqlRequest
   */
  public GraphqlRequest getGraphqlRequest(String testKey) {
    final String query = "query Tests($jql: String!, $limit: Int!) { "
      + " getTests(jql: $jql, limit: $limit) {"
      + "    total"
      + "    start"
      + "    limit"
      + "    results {"
      + "      issueId"
      + "      jira(fields: [\"key\"])"
      + "      projectId"
      + "      testType {"
      + "          name"
      + "          kind"
      + "      },"
      + "      steps {"
      + "          id"
      + "          data"
      + "          action"
      + "          result"
      + "          attachments {"
      + "              id"
      + "              filename"
      + "          }"
      + "      },"
      + "      preconditions(limit: 10) {"
      + "        total"
      + "        start"
      + "        limit"
      + "        results {"
      + "          issueId"
      + "          projectId"
      + "          jira(fields: [\"key\"])"
      + "        }"
      + "      }"
      + "    }"
      + "  }"
      + "}";
    return GraphqlRequest.builder()
      .query(query)
      .variables(Variables.builder()
        .jql("key=" + testKey)
        .limit(1)
        .build())
      .build();
  }
}
