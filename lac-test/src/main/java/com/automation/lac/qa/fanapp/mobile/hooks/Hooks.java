package com.automation.lac.qa.fanapp.mobile.hooks;

import static com.automation.lac.qa.allure.AllureLogger.attachScreenShot;
import static com.automation.lac.qa.browserstack.SessionService.addBrowserStackPublicLinks;
import static com.automation.lac.qa.browserstack.SessionService.setBrowserStackStatus;
import static com.automation.lac.qa.faker.AleatoryData.createRandomMiniAccounts;
import static com.automation.lac.qa.faker.AleatoryData.createRandomVehicles;
import static com.automation.lac.qa.fanapp.api.models.PaymentMethodFile.getActiveAndNotUsedPaymentMethods;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.MINI_ACCOUNTS_INFO;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PAYMENT_METHODS;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.SESSION_ID;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.VEHICLES_INFO;
import static com.automation.lac.qa.recording.Recording.startRecording;
import static com.automation.lac.qa.recording.Recording.stopRecording;
import static com.automation.lac.qa.utils.TestContextManager.cleanTestContext;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.assertions.SoftAssertManager;
import com.automation.lac.qa.driver.Device;
import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.utils.TestContextManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
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
  public void beforeScenario(Scenario scenario) {
    log.info("Starting scenario: {}", scenario.getName());
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
    getTestContext().set(VEHICLES_INFO.name(), createRandomVehicles(5));
    getTestContext().set(MINI_ACCOUNTS_INFO.name(), createRandomMiniAccounts(5));
  }

  /**
   * Attaches a screenshot to the report if the current step fails.
   *
   * @param scenario The current Cucumber scenario.
   */
  @AfterStep
  public void afterStep(Scenario scenario) {
    if (scenario.isFailed()) {
      attachScreenShot("screenShoot");
    }
  }

  /**
   * Finalizes the test environment after each scenario execution.
   * This includes asserting all soft assertions, logging the scenario's end,
   * updating the BrowserStack status, adding BrowserStack public links, quitting the driver,
   * and cleaning the test context.
   *
   * @param scenario The scenario that has just been executed.
   */
  @After
  public void afterScenario(Scenario scenario) {
    stopRecording(scenario.getName());
    SoftAssertManager.assertAll();
    log.info("Ending scenario: {}", scenario.getName());
    if (MobileBaseScreen.getDriver() != null) {
      setBrowserStackStatus(MobileBaseScreen.getDriver(),
        String.valueOf(scenario.getStatus()));
      addBrowserStackPublicLinks(getTestContext().get(SESSION_ID.name()));
      MobileBaseScreen.getDriver().quit();
      MobileBaseScreen.removeDriver();
    }
    cleanTestContext();
  }
}
