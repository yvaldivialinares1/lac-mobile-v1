package com.automation.lac.qa.driver;


import static com.automation.lac.qa.browserstack.SessionService.getHubUrl;
import static com.automation.lac.qa.driver.AppiumConstants.ANDROID_PLATFORM;
import static com.automation.lac.qa.driver.AppiumConstants.IOS_PLATFORM;
import static com.automation.lac.qa.driver.AppiumConstants.NEW_COMMAND_TIMEOUT;
import static com.automation.lac.qa.driver.AppiumConstants.platformName;

import com.automation.lac.qa.utils.CustomException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.net.URL;
import java.time.Duration;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.net.Urls;

@Slf4j
@UtilityClass
public class Device {

  /**
   * Creates and returns an instance of AppiumDriver based on the specified platform.
   * This is a synchronized method, ensuring that only one driver is created at a time across
   * all threads.
   *
   * @return An instance of AndroidDriver or IOSDriver based on the platformName.
   * @throws CustomException If the platformName is not recognized.
   */
  @SneakyThrows
  public static synchronized AppiumDriver createAppiumDriver() {
    URL url = Urls.from(getHubUrl()).toURL();
    AppiumDriver driver = switch (platformName) {
      case ANDROID_PLATFORM -> new AndroidDriver(url, Capabilities.setAndroidCapabilities());
      case IOS_PLATFORM -> new IOSDriver(url, Capabilities.setIosCapabilities());
      default -> throw new CustomException(
        "PlatformName '" + platformName + "' is not a valid option.");
    };
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    return driver;
  }

}
