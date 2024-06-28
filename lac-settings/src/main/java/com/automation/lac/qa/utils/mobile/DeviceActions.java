package com.automation.lac.qa.utils.mobile;

import static com.automation.lac.qa.driver.AppiumConstants.IOS_PLATFORM;
import static com.automation.lac.qa.driver.AppiumConstants.platformName;
import static com.automation.lac.qa.pages.MobileBaseScreen.getDriver;
import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;
import static com.automation.lac.qa.utils.mobile.SwipeActions.getCenter;

import com.automation.lac.qa.utils.CustomException;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import java.util.Map;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Slf4j
@UtilityClass
public class DeviceActions {

  /**
   * Clicks on the given web element. This method performs a click action on the specified {@link
   * WebElement}. The action is accompanied by a description for logging or reporting purposes.
   *
   * @param element     The {@link WebElement} to be clicked.
   * @param description A text description of the element to be clicked, used for logging.
   */
  @Step("Click on '{description}'")
  public static void click(WebElement element, String description) {
    element.click();
  }

  /**
   * Clicks on the given web element. This method performs a click action on the specified {@link
   * WebElement}. The action is accompanied by a description for logging or reporting purposes.
   *
   * @param element     The {@link WebElement} to be clicked.
   * @param description A text description of the element to be clicked, used for logging.
   */
  @Step("Click on '{description}'")
  public static void clickOnCoordinates(WebElement element, String description) {
    int x = element.getRect().getX() + element.getSize().width / 2;
    int y = element.getRect().getY() + element.getSize().height / 2;
    new Actions(getDriver()).moveToLocation(x, y).click().build().perform();
  }

  /**
   * Sends a sequence of keystrokes to the specified web element. This method simulates typing into
   * an element, which may set its value. The action includes a description for the purpose of
   * logging or reporting.
   *
   * @param element     The {@link WebElement} to which the keystrokes should be sent.
   * @param keys        The sequence of keys to send to the element.
   * @param description A text description of the action for logging or documentation purposes.
   */
  @Step("Send keys {keys} into '{description}'")
  public static void sendKeys(WebElement element, String keys, String description) {
    element.sendKeys(keys);
    hideKeyboard("done");
  }

  /**
   * Sets the text of a web element representing a text field to the specified value.
   *
   * @param element     The WebElement to which the text will be set.
   * @param text        The String value to set in the text field.
   * @param description A message describing the action or purpose of setting the text.
   */
  public static void setTextField(WebElement element, String text, String description) {
    click(element, description);
    sendKeys(element, text, description);
    hideKeyboard(null);
  }

  /**
   * Sets the text of a web element representing a text field to the specified value.
   *
   * @param element     The WebElement to which the text will be set.
   * @param text        The String value to set in the text field.
   * @param description A message describing the action or purpose of setting the text.
   */
  @Step("Send keys {text} into '{description}'")
  public static void performText(WebElement element, String text, String description) {
    click(element, description);
    new Actions(getDriver()).sendKeys(text).perform();
  }

  /**
   * Sets the text of a web element representing a text field to the specified value. Special method
   * as iOS TextFields are not interactable as they used to be. Implementation to be able to
   * continue with mission executions.
   *
   * <p>Sometimes upper elements have their limit with screen boundaries to place click correctly, a
   * tap in the 95% of the element height is necessary
   *
   * @param element     The WebElement to which the text will be set.
   * @param text        The String value to set in the text field.
   * @param description A message describing the action or purpose of setting the text.
   * @deprecated This method is deprecated because it's not the expected behavior
   */
  @Deprecated(since = "iOS")
  @Step("Send keys {text} into '{description}'")
  public static void specialIosSetText(WebElement element, String text,
                                       String description, boolean isNumericKeyboard) {
    int y1Coordinate;
    if (element.getRect().getY() != 0) {
      y1Coordinate = getCenter(element).getY();
    } else {
      y1Coordinate = (int) Math.round(element.getRect().getHeight() * 0.95);
    }
    new Actions(getDriver())
      .moveToLocation(getCenter(element).getX(), y1Coordinate)
      .click()
      .perform();
    new Actions(getDriver()).sendKeys(text).perform();
    if (!isNumericKeyboard)
      hideKeyboard("done");
  }

  /**
   * Clears the text content of the given web element and logs the action using Allure reporting.
   *
   * @param element The WebElement whose text content needs to be cleared.
   */
  public static void clear(WebElement element) {
    element.clear();
  }

  /**
   * Hide keyboard when shown
   *
   * @param keyName key name for ios case
   */
  public static void hideKeyboard(String keyName) {
    if (platformName.equalsIgnoreCase(IOS_PLATFORM)) {
      var ios = (IOSDriver) getDriver();
      if (ios.isKeyboardShown())
        try {
          ios.hideKeyboard(keyName);
        } catch (Exception ex) {
          log.warn(ex.getMessage());
        }
    }
  }

  /**
   * Retrieves the web element based on the provided locator Catches NoSuchElementException,
   * StaleElementReferenceException, and other exceptions, logging them with Allure
   */
  public static WebElement getElement(By by) {
    WebElement element;
    try {
      element = getDriver().findElement(by);
    } catch (NoSuchElementException var4) {
      throw new CustomException("No Such Element " + by, var4);
    } catch (StaleElementReferenceException var5) {
      throw new CustomException("Stale Element Reference " + by, var5);
    } catch (Exception var6) {
      throw new CustomException("Exception " + by, var6);
    }
    return element;
  }

  /**
   * Retry restarting the app
   */
  public static void retryRestartingMobileApp() {
    restartMobileApp(2);
  }

  /**
   * Restart the app every time is needed
   *
   * @param time refers to the retry number times for restarting the App
   */
  public static void restartMobileApp(int time) {
    for (int i = 0; i < time; i++) {
      try {
        String appActivity =
          getDriver()
            .getCapabilities()
            .getCapability(isAndroid() ? "appPackage" : "bundleId")
            .toString();
        if (isAndroid()) {
          AndroidDriver androidDriver = ((AndroidDriver) getDriver());
          androidDriver.executeScript("mobile: clearApp", Map.of("appId", appActivity));
          androidDriver.activateApp(appActivity);
        } else {
          IOSDriver iosDriver = ((IOSDriver) getDriver());
          iosDriver.terminateApp(appActivity);
          iosDriver.activateApp(appActivity);
        }
        break;
      } catch (Exception e) {
        log.error("Unexpected error restarting the app: {}", e.getMessage());
      }
    }
  }

  /**
   * Gets the text of an element for both iOS and Android
   * iOS attribute is not constant as expected text can be located in different attributes.
   *
   * @param element      Element to get the text from
   * @param iosAttribute iOS Attribute to get text from e.g. "value", "label", "name", etc.
   * @return text content from element
   */
  public static String getTextFromElement(WebElement element, String iosAttribute) {
    String attribute = isAndroid() ? "text" : iosAttribute;
    return element.getAttribute(attribute);
  }
}
