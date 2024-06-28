package com.automation.lac.qa.driver;

import static com.automation.lac.qa.driver.AppiumConstants.ANDROID_ADB_EXEC_TIME_OUT;
import static com.automation.lac.qa.driver.AppiumConstants.ANDROID_AVD_LAUNCH_TIME_OUT;
import static com.automation.lac.qa.driver.AppiumConstants.ANDROID_DEVICE_READY_TIME_OUT;
import static com.automation.lac.qa.driver.AppiumConstants.ANDROID_INSTALL_TIME_OUT;
import static com.automation.lac.qa.driver.AppiumConstants.APP_VERSION;
import static com.automation.lac.qa.driver.AppiumConstants.FULL_RESET;
import static com.automation.lac.qa.driver.AppiumConstants.MOBILE_APP;
import static com.automation.lac.qa.driver.AppiumConstants.NEW_COMMAND_TIMEOUT;
import static com.automation.lac.qa.driver.AppiumConstants.NO_RESET;
import static com.automation.lac.qa.driver.AppiumConstants.deviceName;
import static com.automation.lac.qa.driver.AppiumConstants.platformName;
import static com.automation.lac.qa.driver.AppiumConstants.platformVersion;
import static org.openqa.selenium.ScreenOrientation.PORTRAIT;

import com.automation.lac.qa.utils.TestContextManager;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import java.time.Duration;
import java.util.HashMap;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Capabilities {

  /**
   * Constructs a map of BrowserStack-specific options for Appium sessions.
   *
   * @return A map containing BrowserStack options.
   */
  private static synchronized HashMap<String, Object> browserstackOptions() {
    var application = TestContextManager.getTestContext().get("ProjectName");
    HashMap<String, Object> browserstackOptions = new HashMap<>();
    browserstackOptions.put("projectName", application);
    browserstackOptions.put("buildName",
      "LAC - " + application + " - " + platformName + "-" + APP_VERSION);
    browserstackOptions.put("buildTag", "regression");
    browserstackOptions.put("networkLogs", true);
    browserstackOptions.put("sessionName", TestContextManager.getTestContext().get("ScenarioName"));
    browserstackOptions.put("debug", true);
    browserstackOptions.put("interactiveDebugging", true);
    browserstackOptions.put("idleTimeout", 120);
    browserstackOptions.put("appiumVersion", "2.4.1");
    browserstackOptions.put("noBlankPolling", true);
    return browserstackOptions;
  }

  /**
   * Configures and returns Android-specific capabilities for Appium tests.
   * Sets BrowserStack options for "Lts" apps and general device and session settings.
   *
   * @return Configured UiAutomator2Options for Android testing.
   */
  public static synchronized UiAutomator2Options setAndroidCapabilities() {
    UiAutomator2Options automator2Options = new UiAutomator2Options();
    if (MOBILE_APP.endsWith("Lts")) {
      automator2Options.setCapability("bstack:options", browserstackOptions());
    }
    automator2Options
      .setDeviceName(deviceName)
      .setPlatformName(platformName)
      .setPlatformVersion(platformVersion)
      .setFullReset(FULL_RESET)
      .setNoReset(NO_RESET)
      .setOrientation(PORTRAIT)
      .setNewCommandTimeout(Duration.ofSeconds(NEW_COMMAND_TIMEOUT))
      .setAndroidInstallTimeout(Duration.ofSeconds(ANDROID_INSTALL_TIME_OUT))
      .setAdbExecTimeout(Duration.ofSeconds(ANDROID_ADB_EXEC_TIME_OUT))
      .setAvdReadyTimeout(Duration.ofSeconds(ANDROID_DEVICE_READY_TIME_OUT))
      .setAvdLaunchTimeout(Duration.ofSeconds(ANDROID_AVD_LAUNCH_TIME_OUT))
      .setApp(MOBILE_APP);
    automator2Options.setCapability("unicodeKeyboard", true);
    automator2Options.setCapability("resetKeyboard", true);
    automator2Options.setCapability("disableIdLocatorAutocompletion", true);
    return automator2Options;
  }

  /**
   * Configures and returns iOS-specific capabilities for Appium tests.
   * Applies BrowserStack options for "Lts" apps and sets device and session configurations.
   *
   * @return Configured XCUITestOptions for iOS testing.
   */
  public static synchronized XCUITestOptions setIosCapabilities() {
    XCUITestOptions xcuiTestOptions = new XCUITestOptions();
    if (MOBILE_APP.endsWith("Lts")) {
      xcuiTestOptions.setCapability("bstack:options", browserstackOptions());
    }
    xcuiTestOptions.setUdid(AppiumConstants.DEVICE_UDID)
      .setPlatformName(platformName)
      .setDeviceName(deviceName)
      .setPlatformVersion(platformVersion)
      .setFullReset(FULL_RESET)
      .setNoReset(NO_RESET)
      .setOrientation(PORTRAIT)
      .setWdaConnectionTimeout(Duration.ofSeconds(NEW_COMMAND_TIMEOUT))
      .setNewCommandTimeout(Duration.ofSeconds(NEW_COMMAND_TIMEOUT))
      .setWdaStartupRetryInterval(
        Duration.ofSeconds(AppiumConstants.WDA_STARTUP_RETRY_INTERVAL))
      .setApp(MOBILE_APP);
    xcuiTestOptions.setCapability("wdaLaunchTimeout", ANDROID_AVD_LAUNCH_TIME_OUT);
    return xcuiTestOptions;
  }
}