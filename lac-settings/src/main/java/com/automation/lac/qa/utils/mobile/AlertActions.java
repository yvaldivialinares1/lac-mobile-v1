package com.automation.lac.qa.utils.mobile;

import static com.automation.lac.qa.pages.MobileBaseScreen.getDriver;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.AppiumDriver;
import java.time.Duration;
import java.util.HashMap;
import java.util.NoSuchElementException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

@Slf4j
@UtilityClass
public class AlertActions {

  HashMap<String, String> args = new HashMap<>();

  /**
   * Creates a FluentWait instance for AppiumDriver with a specified timeout and polling interval.
   *
   * @param timeout The maximum time to wait for a condition.
   * @param polling The interval to poll for a condition.
   * @return A FluentWait instance configured for AppiumDriver.
   */
  public static FluentWait<AppiumDriver> fluentWait(Duration timeout, Duration polling) {
    return new FluentWait<>(MobileBaseScreen.getDriver())
      .withTimeout(timeout)
      .pollingEvery(polling)
      .ignoring(NoSuchElementException.class);
  }

  /**
   * Wait for alert to be present
   * @param buttonLabel expected button label
   */
  public void tapIosAlertOption(String buttonLabel) {
    fluentWait(
      Duration.ofSeconds(10), Duration.ofSeconds(1)).until(ExpectedConditions.alertIsPresent());
    args.put("buttonLabel", buttonLabel);
    args.put("action", "accept");
    getDriver().executeScript("mobile: alert", args, null);
  }
}
