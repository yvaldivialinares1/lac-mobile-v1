package com.automation.lac.qa.staffapp.mobile.hooks;

import static com.automation.lac.qa.allure.AllureLogger.attachScreenShot;
import static com.automation.lac.qa.browserstack.SessionService.addBrowserStackPublicLinks;
import static com.automation.lac.qa.browserstack.SessionService.setBrowserStackStatus;
import static com.automation.lac.qa.recording.Recording.startRecording;
import static com.automation.lac.qa.recording.Recording.stopRecording;
import static com.automation.lac.qa.staffapp.api.models.PaymentMethodFile.getActiveAndNotUsedPaymentMethods;
import static com.automation.lac.qa.staffapp.mobile.enums.StaffAppKeys.PAYMENT_METHODS;
import static com.automation.lac.qa.staffapp.mobile.enums.StaffAppKeys.SESSION_ID;
import static com.automation.lac.qa.utils.TestContextManager.cleanTestContext;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.assertions.SoftAssertManager;
import com.automation.lac.qa.driver.Device;
import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.utils.TestContextManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks {

  /**
   * Prepares the test environment for a new scenario.
   * Sets the scenario name, initializes the Appium driver, and logs the session ID.
   *
   * @param scenario The scenario currently being executed.
   */
  @Before(order = 0)
  public void initDriver(Scenario scenario) {
    log.info("Starting scenario: {}", scenario.getName());
    TestContextManager.getTestContext()
      .set("ProjectName", scenario.getUri().getPath().split("features/")[1].split("/")[0]);
    TestContextManager.getTestContext().set("ScenarioName", scenario.getName());
    MobileBaseScreen.setDriver(Device.createAppiumDriver());
    getTestContext().set(SESSION_ID.name(), MobileBaseScreen.getDriver().getSessionId().toString());
    log.info("SessionId: {}", MobileBaseScreen.getDriver().getSessionId().toString());
    startRecording();
  }

  /**
   * Loads test data into the context before each scenario, including payment methods,
   * vehicle information, and mini account details.
   */
  @Before(order = 1)
  public void baseLoadData() {
    getTestContext().set(PAYMENT_METHODS.name(), getActiveAndNotUsedPaymentMethods());
  }

  /**
   * Finalizes the test environment after each scenario execution.
   * This includes asserting all soft assertions, logging the scenario's end, quitting the driver,
   * and cleaning the test context.
   *
   * @param scenario The scenario that has just been executed.
   */
  @After(order = 1)
  public void closeDriver(Scenario scenario) {
    stopRecording(scenario.getName());
    log.info("Ending scenario: {}", scenario.getName());
    if (MobileBaseScreen.getDriver() != null) {
      MobileBaseScreen.getDriver().quit();
      MobileBaseScreen.removeDriver();
    }
    cleanTestContext();
  }

  /**
   * Adds the BrowserStack public URL to the test report for the given scenario.
   * This is useful when tests are run on BrowserStack and there is a need to provide
   * a quick link to the test execution video or logs.
   *
   * @param scenario The Cucumber {@link Scenario} object representing the test scenario.
   */
  @After(order = 2)
  public void addingBrowserStackPublicUrl(Scenario scenario) {
    setBrowserStackStatus(MobileBaseScreen.getDriver(), String.valueOf(scenario.getStatus()));
    addBrowserStackPublicLinks(getTestContext().get(SESSION_ID.name()));
  }

  @After(order = 3)
  public void assertAll() {
    SoftAssertManager.assertAll();
  }

  /**
   * Attaches evidence to the report if the scenario has failed. This method can be used,
   * for example, to take screenshots or gather logs when a test scenario fails.
   *
   * @param scenario The Cucumber {@link Scenario} object representing the test scenario.
   */
  @After(order = 4)
  public void attachEvidenceIfScenarioFailed(Scenario scenario) {
    if (scenario.isFailed()) {
      attachScreenShot("screenShoot");
    }
  }
}
