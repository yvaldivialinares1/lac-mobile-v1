package com.automation.lac.qa.xray.api;

import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.utils.Constants;
import com.automation.lac.qa.utils.CustomException;
import com.automation.lac.qa.xray.JiraProject.Project;
import com.automation.lac.qa.xray.builder.XrayBuilder;
import com.automation.lac.qa.xray.dto.AuthenticateRequest;
import com.automation.lac.qa.xray.dto.IssueRequest;
import com.automation.lac.qa.xray.dto.JiraResponse;
import com.automation.lac.qa.xray.dto.graphql.GraphqlResponse;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

@Slf4j
public class XrayApi {

  private static final String XRAY_CLOUD_URL = "https://xray.cloud.getxray.app/api/";
  private static final String MSG = "Request {}";

  protected static String token = postAuthenticate();

  /**
   * Generate Xray Bearer Token
   */
  public static String postAuthenticate() {
    AuthenticateRequest requestBody = new XrayBuilder().getCredentials();
    Response response = RestAssured.given()
      .baseUri(XRAY_CLOUD_URL)
      .contentType(JSON)
      .body(requestBody)
      .when()
      .post("/v1/authenticate")
      .then()
      .extract().response();

    if (response.statusCode() != SC_OK) {
      log.error(XRAY_CLOUD_URL);
      log.error(MSG, requestBody);
    }
    Assert.assertEquals(response.statusCode(), SC_OK, "Xray - POST /authenticate ->\n "
      + response.getBody().asString());
    return response.getBody().asString().replace("\"", "");
  }

  /**
   * @param request Object
   */
  public void postImportExecution(Object request) {
    Response response = RestAssured.given()
      .baseUri(XRAY_CLOUD_URL)
      .contentType(JSON)
      .header("Authorization", "Bearer " + token)
      .body(request)
      .when()
      .post("/v2/import/execution")
      .then()
      .extract().response();

    if (response.statusCode() != SC_OK) {
      log.error("Xray - POST /import/execution");
      log.error(MSG, request);
      log.error("Response {}", response.getBody());
      Allure.step("Xray - POST /import/execution -> " + response.getBody().asString());
    }
  }

  /**
   * Create issues on Jira
   *
   * @param request IssueRequest
   * @return JiraResponse
   */
  public JiraResponse createTestExecution(IssueRequest request) {
    String baseUri = Project.getJiraUri(Constants.PROJECT_ID);
    Response response = RestAssured.given()
      .baseUri(baseUri)
      .contentType(JSON)
      .auth().preemptive().basic(Constants.JIRA_USER_ID, Constants.JIRA_API_TOKEN)
      .body(request)
      .when()
      .post("/rest/api/2/issue")
      .then()
      .extract().response();
    if (response.statusCode() != SC_CREATED) {
      log.error("Jira Uri {}", baseUri);
      log.error(MSG, request);
      Allure.step("Jira - POST /rest/api/2/issue -> " + response.getBody().asString());
      throw new CustomException(
        "It was not possible to create Test Execution: " + response.getBody().asString());
    }
    return response.as(JiraResponse.class);
  }

  /**
   * Get test cases information
   *
   * @param request Object
   * @return GraphqlResponse
   */
  public GraphqlResponse getTestInfo(Object request) {
    Response response = RestAssured.given()
      .baseUri(XRAY_CLOUD_URL)
      .contentType(JSON)
      .header("Authorization", "Bearer " + token)
      .body(request)
      .when()
      .post("/v2/graphql")
      .then()
      .extract().response();

    return response.as(GraphqlResponse.class);
  }

}
