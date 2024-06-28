package com.automation.lac.qa.utils.mobile;

import static com.automation.lac.qa.driver.AppiumConstants.WAIT_TIMEOUT;
import static com.automation.lac.qa.pages.MobileBaseScreen.getDriver;
import static com.automation.lac.qa.utils.mobile.WaitActions.createFluentWait;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.qameta.allure.Allure;
import java.time.Duration;
import java.util.HashMap;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Slf4j
@UtilityClass
public class AlertActions {

  HashMap<String, String> args = new HashMap<>();

  /**
   * Wait for alert to be present
   *
   * @param buttonLabel expected button label
   */
  public void tapIosAlertOption(String buttonLabel) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    try {
      createFluentWait(MobileBaseScreen.getDriver(),
        Duration.ofSeconds(10), Duration.ofSeconds(1), "Alert is not present").until(
        ExpectedConditions.alertIsPresent());
      args.put("buttonLabel", buttonLabel);
      args.put("action", "accept");
      getDriver().executeScript("mobile: alert", args, null);
    } catch (Exception e) {
      log.error("Could not tap on iOS alert option {} ", buttonLabel, e);
      throw e;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_TIMEOUT));
    }
  }

  /**
   * Verify if iOS alert is displayed
   *
   * @return boolean
   */
  public boolean isDeviceAlertDisplayed(String expectedAlert) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    try {
      createFluentWait(MobileBaseScreen.getDriver(),
        Duration.ofSeconds(10), Duration.ofSeconds(1), "Alert is not present").until(
        ExpectedConditions.alertIsPresent());
      Allure.step(String.format("%s alert was displayed", expectedAlert));
      return true;
    } catch (Exception e) {
      Allure.step(String.format("%s alert was not displayed", expectedAlert));
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_TIMEOUT));
    }
  }
}
