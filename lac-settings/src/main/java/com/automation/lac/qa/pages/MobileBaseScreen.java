package com.automation.lac.qa.pages;

import static com.automation.lac.qa.driver.AppiumConstants.ANDROID_PLATFORM;
import static com.automation.lac.qa.driver.AppiumConstants.deviceName;
import static com.automation.lac.qa.driver.AppiumConstants.platformName;
import static com.automation.lac.qa.utils.Constants.IPAD_DEVICE_NAME;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import com.automation.lac.qa.driver.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.PageFactory;

@Slf4j
@Getter
public abstract class MobileBaseScreen {

  private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();

  /**
   * Retrieves the current thread's instance of AppiumDriver.
   *
   * @return The AppiumDriver instance for the current thread.
   */
  public static AppiumDriver getDriver() {
    return driverThreadLocal.get();
  }

  /**
   * Sets the AppiumDriver instance for the current thread.
   *
   * @param driver The AppiumDriver instance to be associated with the current thread.
   */
  public static void setDriver(AppiumDriver driver) {
    driverThreadLocal.set(driver);
  }

  /**
   * Quits the current thread's instance of AppiumDriver and removes it from the thread local
   * storage.
   */
  public static void removeDriver() {
    AppiumDriver driver = driverThreadLocal.get();
    if (driver != null) {
      driver.quit();
      driverThreadLocal.remove();
    }
  }

  /**
   * Constructs a MobileBaseScreen object and initializes page elements.
   * If the AppiumDriver is not set for the current thread, it creates a new instance.
   */
  protected MobileBaseScreen() {
    if (getDriver() == null) {
      setDriver(Device.createAppiumDriver());
    }
    try {
      PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    } catch (Exception e) {
      log.error("Failed to initialize page elements using PageFactory. "
          + "There might be an issue with the Appium driver initialization or "
          + "the elements' locators may be invalid. Exception message: {}",
        e.getMessage(),
        e);
    }
  }

  /**
   * Checks if the current platform is Android.
   *
   * @return {@code true} if the current platform is Android, {@code false} otherwise.
   */
  public static boolean isAndroid() {
    return platformName.equalsIgnoreCase(ANDROID_PLATFORM);
  }

  /**
   * Checks if the current device is an iPad.
   *
   * @return {@code true} if the current device is an iPad, {@code false} otherwise.
   */
  public static boolean isIpad() {
    return containsIgnoreCase(deviceName, IPAD_DEVICE_NAME);
  }

}