package com.automation.lac.qa.driver;

import com.automation.lac.qa.utils.CustomException;
import com.automation.lac.qa.utils.PropertiesManager;
import java.util.Objects;
import java.util.Optional;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.testng.xml.XmlSuite;

@Slf4j
@UtilityClass
public class AppiumConstants {

  public static final String ANDROID_PLATFORM = "Android";
  public static final String IOS_PLATFORM = "iOS";
  public static String platformName;
  public static String platformVersion;
  public static String deviceName;
  public static String mobileApp;
  public static final String DEVICE_UDID = PropertiesManager.getParameter("framework.device.udid");
  public static final String HUB_URL =
    PropertiesManager.getParameter("framework.mobile.hub.url");
  public static final boolean FULL_RESET =
    Boolean.parseBoolean(getOptionalParameterOrDefault("framework.mobile.full.reset", "false"));
  public static final boolean NO_RESET =
    Boolean.parseBoolean(getOptionalParameterOrDefault("framework.mobile.no.reset", "false"));
  public static final int ANDROID_INSTALL_TIME_OUT =
    Integer.parseInt(PropertiesManager.getParameter("framework.mobile.android.install.timeout"));
  public static final int ANDROID_ADB_EXEC_TIME_OUT =
    Integer.parseInt(PropertiesManager.getParameter("framework.mobile.android.adb.exec.timeout"));
  public static final int ANDROID_DEVICE_READY_TIME_OUT =
    Integer.parseInt(
      PropertiesManager.getParameter("framework.mobile.android.device.ready.timeout"));
  public static final int ANDROID_AVD_LAUNCH_TIME_OUT =
    Integer.parseInt(PropertiesManager.getParameter("framework.mobile.android.avd.launch.timeout"));
  public static final String APP_VERSION =
    getOptionalParameterOrDefault("framework.mobile.app.version", "<UnknownAppVersion>");
  public static final int NEW_COMMAND_TIMEOUT =
    Integer.parseInt(PropertiesManager.getParameter("framework.mobile.new.command.timeout"));
  public static final int WDA_STARTUP_RETRY_INTERVAL =
    getTimeoutOrDefault();
  public static final String VIDEO_RECORDING =
    getOptionalParameterOrDefault("framework.videoRecording","false");

  public static final String BS_USER_NAME =
    PropertiesManager.getPropertyFromEnv("BS_USERNAME");
  public static final String BS_ACCESS_KEY =
    PropertiesManager.getPropertyFromEnv("BS_PASSWORD");

  /**
   * Initializes Appium configuration variables from the XML suite.
   *
   * @param xmlSuite XmlSuite containing the configuration parameters.
   */
  public static synchronized void setAppiumVariables(XmlSuite xmlSuite) {
    platformName =
      PropertiesManager.getXmlParameters(xmlSuite, "framework.platform.name");
    platformVersion =
      PropertiesManager.getXmlParameters(xmlSuite, "framework.platform.version");
    deviceName =
      PropertiesManager.getXmlParameters(xmlSuite, "framework.device.name");
    mobileApp =
      PropertiesManager.getXmlParameters(xmlSuite, "framework.mobile.app");
  }

  /**
   * Retrieves an optional parameter from the properties manager or returns null if not found.
   *
   * @param parameterName The name of the parameter to retrieve.
   * @return The value of the parameter or null if not present.
   */
  public static String getOptionalParameter(String parameterName) {
    try {
      return PropertiesManager.getParameter(parameterName);
    } catch (CustomException e) {
      log.warn("Parameter '{}' not found: {}", parameterName, e.getMessage());
      return null;
    }
  }

  /**
   * Retrieves an optional parameter from the properties manager or returns the default value if
   * not found.
   *
   * @param parameterName The name of the parameter to retrieve.
   * @param defaultValue  The default value to return if the parameter is not found.
   * @return The value of the parameter or the default value if not present.
   */
  private static String getOptionalParameterOrDefault(String parameterName, String defaultValue) {
    return Objects.requireNonNullElse(getOptionalParameter(parameterName), defaultValue);
  }

  /**
   * Retrieves a timeout value from the properties manager or returns the default value if not
   * found.
   *
   * @return The timeout value or the default value if not present.
   */
  private static int getTimeoutOrDefault() {
    return Optional.ofNullable(getOptionalParameter(
        "framework.mobile.ios.wda.startup.retry.interval"))
      .map(Integer::parseInt)
      .orElse(20);
  }
}
