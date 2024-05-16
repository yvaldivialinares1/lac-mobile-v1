package com.automation.lac.qa.utils.mobile;

import static com.automation.lac.qa.driver.AppiumConstants.IOS_PLATFORM;
import static com.automation.lac.qa.driver.AppiumConstants.platformName;
import static com.automation.lac.qa.utils.mobile.SwipeActions.getCenter;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.utils.CustomException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Slf4j
@UtilityClass
public class DeviceActions {

  private static final String NO_SUCH_ELEMENT_EXCEPTION
    = "An element could not be located on the page using the given search parameters.";
  private static final String SEND_KEYS = "Send keys ";
  private static final String INTO = " into ";

  private static AppiumDriver getDriver() {
    return MobileBaseScreen.getDriver();
  }

  /**
   * Clicks on the given web element.
   * This method performs a click action on the specified {@link WebElement}.
   * The action is accompanied by a description for logging or reporting purposes.
   *
   * @param element     The {@link WebElement} to be clicked.
   * @param description A text description of the element to be clicked, used for logging.
   */
  public static void click(WebElement element, String description) {
    try {
      element.click();
      Allure.step("Click on " + description, Status.PASSED);
    } catch (WebDriverException e) {
      Allure.step("Click on " + description, Status.FAILED);
      throw new CustomException(NO_SUCH_ELEMENT_EXCEPTION);
    }
  }

  /**
   * Sends a sequence of keystrokes to the specified web element.
   * This method simulates typing into an element, which may set its value.
   * The action includes a description for the purpose of logging or reporting.
   *
   * @param element     The {@link WebElement} to which the keystrokes should be sent.
   * @param keys        The sequence of keys to send to the element.
   * @param description A text description of the action for logging or documentation purposes.
   */
  public static void sendKeys(WebElement element, String keys, String description) {
    try {
      element.sendKeys(keys);
      Allure.step(SEND_KEYS + keys + INTO + description, Status.PASSED);
    } catch (WebDriverException e) {
      Allure.step(SEND_KEYS + keys + INTO + description, Status.FAILED);
      throw new CustomException(NO_SUCH_ELEMENT_EXCEPTION);
    }
  }

  /**
   * Sets the text of a web element representing a text field to the specified value.
   *
   * @param element     The WebElement to which the text will be set.
   * @param text        The String value to set in the text field.
   * @param description A message describing the action or purpose of setting the text.
   */
  public static void setTextField(WebElement element, String text, String description) {
    try {
      element.click();
      sendKeys(element, text, description);
      hideKeyboard(null);
    } catch (WebDriverException e) {
      throw new CustomException(NO_SUCH_ELEMENT_EXCEPTION);
    }
  }

  /**
   * Sets the text of a web element representing a text field to the specified value.
   *
   * @param element     The WebElement to which the text will be set.
   * @param text        The String value to set in the text field.
   * @param description A message describing the action or purpose of setting the text.
   */
  public static void performText(WebElement element, String text, String description) {
    click(element, description);
    try {
      new Actions(getDriver()).sendKeys(text).perform();
      Allure.step(SEND_KEYS + text + INTO + description, Status.PASSED);
    } catch (WebDriverException e) {
      Allure.step(SEND_KEYS + text + INTO + description, Status.FAILED);
      throw new CustomException(NO_SUCH_ELEMENT_EXCEPTION);
    }
  }

  /**
   * Sets the text of a web element representing a text field to the specified value.
   * Special method as iOS TextFields are not interactable as they used to be.
   * Implementation to be able to continue with mission executions.
   *
   * @param element     The WebElement to which the text will be set.
   * @param text        The String value to set in the text field.
   * @param description A message describing the action or purpose of setting the text.
   *
   * @deprecated This method is deprecated because it's not the expected behavior
   */
  @Deprecated(since = "iOS")
  public static void specialIosSetText(WebElement element, String text, String description) {
    new Actions(getDriver())
      .moveToLocation(getCenter(element).getX(), getCenter(element).getY()).click().perform();
    try {
      new Actions(getDriver()).sendKeys(text).perform();
      Allure.step(SEND_KEYS + text + INTO + description, Status.PASSED);
    } catch (WebDriverException e) {
      Allure.step(SEND_KEYS + text + INTO + description, Status.FAILED);
      throw new CustomException(NO_SUCH_ELEMENT_EXCEPTION);
    }
  }

  /**
   * Clears the text content of the given web element and logs the action using Allure reporting.
   *
   * @param element The WebElement whose text content needs to be cleared.
   */
  public static void clear(WebElement element) {
    try {
      element.clear();
    } catch (WebDriverException e) {
      throw new CustomException(NO_SUCH_ELEMENT_EXCEPTION);
    }
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
   * Retrieves the web element based on the provided locator
   * Catches NoSuchElementException, StaleElementReferenceException, and other exceptions,
   * logging them with Allure
   */
  public static WebElement getElement(By by) {
    WebElement element = null;
    try {
      element = getDriver().findElement(by);
    } catch (NoSuchElementException var4) {
      Allure.issue("NoSuchElementException thrown", "");
    } catch (StaleElementReferenceException var5) {
      Allure.issue("StaleElementReferenceException thrown", "");
    } catch (Exception var6) {
      Allure.issue("Exception thrown", "");
    }
    return element;
  }
}
