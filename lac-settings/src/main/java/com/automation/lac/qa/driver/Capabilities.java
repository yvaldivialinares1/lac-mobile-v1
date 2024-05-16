package com.automation.lac.qa.driver;

import static com.automation.lac.qa.driver.AppiumConstants.ANDROID_ADB_EXEC_TIME_OUT;
import static com.automation.lac.qa.driver.AppiumConstants.ANDROID_AVD_LAUNCH_TIME_OUT;
import static com.automation.lac.qa.driver.AppiumConstants.ANDROID_DEVICE_READY_TIME_OUT;
import static com.automation.lac.qa.driver.AppiumConstants.ANDROID_INSTALL_TIME_OUT;
import static com.automation.lac.qa.driver.AppiumConstants.APP_VERSION;
import static com.automation.lac.qa.driver.AppiumConstants.FULL_RESET;
import static com.automation.lac.qa.driver.AppiumConstants.NEW_COMMAND_TIMEOUT;
import static com.automation.lac.qa.driver.AppiumConstants.NO_RESET;
import static com.automation.lac.qa.driver.AppiumConstants.deviceName;
import static com.automation.lac.qa.driver.AppiumConstants.mobileApp;
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

  private static final UiAutomator2Options UI_AUTOMATOR_2_OPTIONS = new UiAutomator2Options();
  private static final XCUITestOptions XCUI_TEST_OPTIONS = new XCUITestOptions();

  /**
   * Constructs a map of BrowserStack-specific options for Appium sessions.
   *
   * @return A map containing BrowserStack options.
   */
  private static synchronized HashMap<String, Object> browserstackOptions() {
    HashMap<String, Object> browserstackOptions = new HashMap<>();
    browserstackOptions.put("projectName", "FanAPP");
    browserstackOptions.put("buildName", "LAC - FanAPP - " + platformName + "-" + APP_VERSION);
    browserstackOptions.put("buildTag", "regression");
    browserstackOptions.put("networkLogs", true);
    browserstackOptions.put("sessionName", TestContextManager.getTestContext().get("ScenarioName"));
    browserstackOptions.put("debug", true);
    browserstackOptions.put("interactiveDebugging", true);
    browserstackOptions.put("idleTimeout", 120);
    browserstackOptions.put("appiumVersion", "2.4.1");
    return browserstackOptions;
  }

  /**
   * Configures and returns Android-specific capabilities for Appium tests.
   * Sets BrowserStack options for "Lts" apps and general device and session settings.
   *
   * @return Configured UiAutomator2Options for Android testing.
   */
  public static synchronized UiAutomator2Options setAndroidCapabilities() {
    if (mobileApp.endsWith("Lts")) {
      UI_AUTOMATOR_2_OPTIONS.setCapability("bstack:options", browserstackOptions());
    }
    UI_AUTOMATOR_2_OPTIONS
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
      .setApp(mobileApp);
    UI_AUTOMATOR_2_OPTIONS.setCapability("unicodeKeyboard", true);
    UI_AUTOMATOR_2_OPTIONS.setCapability("resetKeyboard", true);
    UI_AUTOMATOR_2_OPTIONS.setCapability("disableIdLocatorAutocompletion", true);
    return UI_AUTOMATOR_2_OPTIONS;
  }

  /**
   * Configures and returns iOS-specific capabilities for Appium tests.
   * Applies BrowserStack options for "Lts" apps and sets device and session configurations.
   *
   * @return Configured XCUITestOptions for iOS testing.
   */
  public static synchronized XCUITestOptions setIosCapabilities() {
    if (mobileApp.endsWith("Lts")) {
      XCUI_TEST_OPTIONS.setCapability("bstack:options", browserstackOptions());
    }
    XCUI_TEST_OPTIONS.setUdid(AppiumConstants.DEVICE_UDID)
      .setPlatformName(platformName)
      .setDeviceName(deviceName)
      .setPlatformVersion(platformVersion)
      .setFullReset(FULL_RESET)
      .setNoReset(NO_RESET)
      .setOrientation(PORTRAIT)
      .setWdaConnectionTimeout(Duration.ofSeconds(AppiumConstants.NEW_COMMAND_TIMEOUT))
      .setWdaStartupRetryInterval(
        Duration.ofSeconds(AppiumConstants.WDA_STARTUP_RETRY_INTERVAL))
      .setApp(mobileApp);
    XCUI_TEST_OPTIONS.setCapability("wdaLaunchTimeout", ANDROID_AVD_LAUNCH_TIME_OUT);
    return XCUI_TEST_OPTIONS;
  }

}
