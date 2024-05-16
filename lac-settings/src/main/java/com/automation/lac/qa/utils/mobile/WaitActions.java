package com.automation.lac.qa.utils.mobile;

import static com.automation.lac.qa.driver.AppiumConstants.NEW_COMMAND_TIMEOUT;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.Widget;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
@UtilityClass
public class WaitActions {

  private AppiumDriver getDriver() {
    return MobileBaseScreen.getDriver();
  }

  private WebDriverWait driverWait() {
    return new WebDriverWait(getDriver(), Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
  }

  /**
   * Waits for an element to either become not visible or disabled within a specified time frame.
   *
   * @param timeInSeconds The time in seconds to wait for the element to become invisible.
   * @param element       The WebElement to check for visibility or enabled status.
   * @return element
   */
  public static WebElement waitForElementNotVisibleOrDisabled(int timeInSeconds,
                                                              WebElement element) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    driverWait().withTimeout(Duration.ofSeconds(timeInSeconds))
      .pollingEvery(Duration.ofSeconds(1))
      .until(ExpectedConditions.invisibilityOf(element));
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    return element;
  }

  /**
   * Wait for element to be enabled, if not enabled it returns false and continues.
   *
   * @param element       element to wait for
   * @param timeInSeconds time to wait the element
   * @return boolean indicating if element is isEnabled or not
   */
  public static boolean waitForElementAvailability(WebElement element, int timeInSeconds) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    try {
      return driverWait().withTimeout(Duration.ofSeconds(timeInSeconds))
        .pollingEvery(Duration.ofSeconds(1))
        .until(not(ExpectedConditions.stalenessOf(element)));
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    }
  }

  /**
   * Wait for element to be is not enabled and not visible,
   * if not enabled it returns false and continues.
   *
   * @param element       element to wait for
   * @param timeInSeconds time to wait the element
   * @return boolean indicating if element is isEnabled or not
   */
  public static boolean waitForElementUnavailability(WebElement element, int timeInSeconds) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    try {
      return driverWait().withTimeout(Duration.ofSeconds(timeInSeconds))
        .pollingEvery(Duration.ofSeconds(1))
        .until(new ExpectedCondition<>() {
          @Override
          public Boolean apply(WebDriver driver) {
            try {
              return (!element.isDisplayed() && !element.isEnabled());
            } catch (Exception e) {
              return true;
            }
          }

          @Override
          public String toString() {
            return "element to not be visible or not be enabled: " + element;
          }
        });
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    }
  }

  /**
   * Waits for an element to be clickable within a specified time frame.
   *
   * @param timeInSeconds The time in seconds to wait for the element to become clickable.
   * @param element       The WebElement to wait for to be clickable.
   * @return element
   */
  public static WebElement waitForElementToBeClickable(int timeInSeconds, WebElement element) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    driverWait().withTimeout(Duration.ofSeconds(timeInSeconds))
      .pollingEvery(Duration.ofSeconds(1))
      .until(ExpectedConditions.elementToBeClickable(element));
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    return element;
  }

  /**
   * Waits for an element to be selected within a specified time frame.
   *
   * @param timeInSeconds The time in seconds to wait for the element to become selected.
   * @param element       The WebElement to wait for to be selected.
   * @return element
   */
  public static WebElement waitForElementToBeSelected(int timeInSeconds, WebElement element) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    driverWait().withTimeout(Duration.ofSeconds(timeInSeconds))
      .pollingEvery(Duration.ofSeconds(1))
      .until(ExpectedConditions.elementToBeSelected(element));
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    return element;
  }

  /**
   * Waits for a list of widgets to be non-empty within a specified time frame.
   *
   * @param timeInSeconds The time in seconds to wait for the list of widgets to become non-empty.
   * @param elements      The list of widgets to check for non-emptiness.
   */
  public static void waitForListWidgetsIsNotEmpty(int timeInSeconds,
                                                  List<? extends Widget> elements) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    driverWait().withTimeout(Duration.ofSeconds(timeInSeconds))
      .pollingEvery(Duration.ofSeconds(1))
      .until(driver -> !elements.isEmpty());
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
  }

  /**
   * Waits for a list of WebElement to be non-empty within a specified time frame.
   *
   * @param timeInSeconds Time in seconds to wait for the list of WebElement to become non-empty.
   * @param elements      List of WebElement to check for non-emptiness.
   */
  public static void waitForListWebElementsIsNotEmpty(int timeInSeconds,
                                                      List<WebElement> elements) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    driverWait().withTimeout(Duration.ofSeconds(timeInSeconds))
      .pollingEvery(Duration.ofSeconds(1))
      .until(driver -> !elements.isEmpty());
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
  }

  /**
   * Checks if the provided WebElement is visible.
   *
   * @param element The WebElement to check for visibility.
   * @return true if the element is visible, false otherwise.
   */
  public static boolean quickIsDisplayed(WebElement element) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    try {
      element.isDisplayed();
      log.info("Element is displayed");
      return true;
    } catch (Exception e) {
      log.warn("Element is not displayed");
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    }
  }

  /**
   * Quickly checks if an element, identified by its XPath, is displayed on the page.
   *
   * @param xpathElement  The XPath expression of the element to check for display status.
   * @param timeInSeconds The time in seconds to wait for the element to be displayed.
   * @return {@code true} if the element is displayed, {@code false} otherwise.
   */
  public static boolean quickIsDisplayed(String xpathElement, int timeInSeconds) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
    try {
      getDriver().findElement(By.xpath(xpathElement)).isDisplayed();
      log.info("Element {} is displayed", xpathElement);
      return true;
    } catch (Exception e) {
      log.warn("Element {} is not displayed", xpathElement);
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    }
  }

  /**
   * Checks if the provided WebElement is enabled.
   *
   * @param element The WebElement to check enabled property.
   * @return true if the element is enabled, false otherwise.
   */
  public static boolean quickIsEnabled(WebElement element) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    try {
      element.isEnabled();
      return true;
    } catch (Exception e) {
      log.warn("Element is not enabled");
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    }
  }

  /**
   * Checks if the provided WebElement is selected.
   *
   * @param element The WebElement to check selected property.
   * @return true if the element is selected, false otherwise.
   */
  public static boolean quickIsSelected(WebElement element) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    try {
      element.isSelected();
      return true;
    } catch (Exception e) {
      log.warn("Element is not selected");
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    }
  }

  /**
   * Wait for element to be visible, if element is not visible it returns false and continues
   *
   * @param element       element to wait for
   * @param timeInSeconds time to wait the element
   * @return boolean indicating if element is found or not
   */
  public static boolean waitForElementVisibility(WebElement element, int timeInSeconds) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    try {
      driverWait().withTimeout(Duration.ofSeconds(timeInSeconds))
        .pollingEvery(Duration.ofSeconds(1))
        .until(visibilityOf(element));
      return element != null && element.isDisplayed();
    } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException var4) {
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    }
  }

  /**
   * Wait for element to be visible, if element is not visible it returns false and continues
   *
   * @param by            locator to find element
   * @param timeInSeconds time to wait the element
   * @return boolean indicating if element is found or not
   */
  public static boolean waitForElementVisibility(By by, int timeInSeconds) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    try {
      WebElement element = driverWait().withTimeout(Duration.ofSeconds(timeInSeconds))
        .pollingEvery(Duration.ofSeconds(1))
        .until(ExpectedConditions.visibilityOfElementLocated(by));
      return element.isDisplayed();
    } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException var4) {
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
    }
  }

  /**
   * Wait for reminder card to set in screen
   * @param seconds to wait for reminder card
   */
  public static void waitForReminderCardToAppear(int seconds) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    StopWatch stopWatch = StopWatch.createStarted();
    boolean firstTime = true;
    while (stopWatch.getTime(TimeUnit.SECONDS) < seconds) {
      if (firstTime) {
        log.info("Waiting for reminder to appear");
        firstTime = false;
      }
    }
    stopWatch.stop();
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(NEW_COMMAND_TIMEOUT));
  }
}
