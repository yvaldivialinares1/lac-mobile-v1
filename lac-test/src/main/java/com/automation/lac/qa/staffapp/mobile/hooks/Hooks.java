package com.automation.lac.qa.staffapp.mobile.hooks;

import static com.automation.lac.qa.allure.AllureLogger.attachScreenShot;
import static com.automation.lac.qa.browserstack.SessionService.addBrowserStackPublicLinks;
import static com.automation.lac.qa.browserstack.SessionService.setBrowserStackStatus;

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
    TestContextManager.getTestContext()
      .set("sessionId", MobileBaseScreen.getDriver().getSessionId().toString());
    log.info("SessionId: " + MobileBaseScreen.getDriver().getSessionId().toString());
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
    SoftAssertManager.assertAll();
    log.info("Ending scenario: {}", scenario.getName());
    if (MobileBaseScreen.getDriver() != null) {
      setBrowserStackStatus(MobileBaseScreen.getDriver(),
        String.valueOf(scenario.getStatus()));
      addBrowserStackPublicLinks(TestContextManager.getTestContext().get("sessionId"));
      MobileBaseScreen.getDriver().quit();
      MobileBaseScreen.removeDriver();
    }
    TestContextManager.cleanTestContext();
  }
}
