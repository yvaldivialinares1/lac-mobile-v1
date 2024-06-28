package com.automation.lac.qa.browserstack;

import static com.automation.lac.qa.browserstack.enums.BrowserStackData.BS_API_CLOUD;
import static com.automation.lac.qa.browserstack.enums.BrowserStackData.BS_SESSIONS;
import static com.automation.lac.qa.driver.AppiumConstants.BS_ACCESS_KEY;
import static com.automation.lac.qa.driver.AppiumConstants.BS_USER_NAME;
import static com.automation.lac.qa.driver.AppiumConstants.MOBILE_APP;
import static io.restassured.http.ContentType.JSON;

import com.automation.lac.qa.driver.AppiumConstants;
import com.automation.lac.qa.utils.CustomException;
import com.fasterxml.jackson.databind.JsonNode;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;

@UtilityClass
public class SessionService {

  /**
   * Get session details from Browser Stack
   */
  public static JsonNode getSessionDetails(String sessionId) {
    Response response = RestAssured.given()
      .baseUri(BS_API_CLOUD.getText())
      .contentType(JSON)
      .auth().basic(BS_USER_NAME, BS_ACCESS_KEY)
      .when()
      .get(BS_SESSIONS.getText() + sessionId + ".json")
      .then()
      .extract().response();
    return response.as(JsonNode.class);
  }

  /**
   * Adds BrowserStack session details as attachments in Allure reports.
   * It retrieves the session details from BrowserStack and attaches the public
   * and video URLs to the Allure report for easy access and review.
   *
   * @param sessionId The session ID for the current BrowserStack session.
   */
  public static void addBrowserStackPublicLinks(String sessionId) {
    if (MOBILE_APP.endsWith("Lts")) {
      JsonNode session = SessionService.getSessionDetails(sessionId);
      Allure.addAttachment("BrowserStack Public URL:",
        session.get("automation_session").get("public_url").asText());
      Allure.addAttachment("BrowserStack Video URL:",
        session.get("automation_session").get("video_url").asText());
    }
  }

  /**
   * Sets the BrowserStack test status for BrowserStack-integrated test executions.
   *
   * @param driver The Appium driver for the test session.
   * @param status The test status ("PASSED" or "FAILED").
   */
  public static synchronized void setBrowserStackStatus(AppiumDriver driver, String status) {
    if (MOBILE_APP.endsWith("Lts")) {
      JSONObject executorObject = new JSONObject();
      JSONObject argumentsObject = new JSONObject();
      if (status.equals("PASSED")) {
        argumentsObject.put("status", "passed");
        argumentsObject.put("reason", "Test Successful!");
      } else {
        argumentsObject.put("status", "failed");
        argumentsObject.put("reason", "Test Failed!");
      }
      executorObject.put("action", "setSessionStatus");
      executorObject.put("arguments", argumentsObject);
      ((JavascriptExecutor) driver).executeScript(
        String.format("browserstack_executor: %s", executorObject));
    }
  }

  /**
   * Gets the Appium server URL, with BrowserStack credentials for "Lts" apps.
   *
   * @return The constructed or default hub URL.
   * @throws CustomException If required properties are missing.
   */
  public static String getHubUrl() {
    if (MOBILE_APP.endsWith("Lts")) {
      if (AppiumConstants.HUB_URL.isEmpty() || AppiumConstants.HUB_URL.isBlank()) {
        throw new CustomException("framework.mobile.hub.url is NULL or Empty");
      }
      if (MOBILE_APP.endsWith("Lts")) {
        assert BS_USER_NAME != null;
        if ((BS_USER_NAME.isEmpty() || BS_USER_NAME.isBlank())) {
          assert BS_ACCESS_KEY != null;
          if (BS_ACCESS_KEY.isEmpty() || BS_ACCESS_KEY.isBlank()) {
            throw new CustomException("'BS_USER_NAME' or 'BS_USER_NAME' is NULL or Empty");
          }
        }
      }
      return "https://" + BS_USER_NAME + ":" + BS_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
    }
    return AppiumConstants.HUB_URL;
  }
}