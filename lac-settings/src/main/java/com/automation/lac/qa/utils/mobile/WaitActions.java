package com.automation.lac.qa.utils.mobile;

import static com.automation.lac.qa.driver.AppiumConstants.WAIT_TIMEOUT;
import static com.automation.lac.qa.pages.MobileBaseScreen.getDriver;
import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;
import static java.util.Arrays.asList;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

@Slf4j
@UtilityClass
public class WaitActions {

  private static final String MESSAGE = "The element '";

  public static void setImplicitWait(Duration duration) {
    getDriver().manage().timeouts().implicitlyWait(duration);
  }

  public static void resetImplicitWait() {
    setImplicitWait(Duration.ofSeconds(WAIT_TIMEOUT));
  }

  /**
   * Creates a configurable FluentWait instance.
   *
   * @param <T>                the type of the input object
   * @param object             the object to wait on
   * @param timeoutDuration    the maximum time to wait
   * @param pollingDuration    the interval to check the condition
   * @param timeoutMessage     the message for timeout exception
   * @param exceptionsToIgnore the exceptions to ignore during polling
   * @return a configured FluentWait instance
   */
  @SafeVarargs
  public static <T> FluentWait<T> createFluentWait(
    T object,
    Duration timeoutDuration,
    Duration pollingDuration,
    String timeoutMessage,
    Class<? extends Throwable>... exceptionsToIgnore) {
    return new FluentWait<>(object)
      .withTimeout(timeoutDuration)
      .pollingEvery(pollingDuration)
      .withMessage(timeoutMessage)
      .ignoreAll(asList(exceptionsToIgnore));
  }

  /**
   * Checks if the specified element becomes invisible within a given time frame.
   *
   * @param timeInSeconds The time in seconds to wait for the element to become invisible.
   * @param element       The {@link WebElement} to check for invisibility.
   * @return {@code true} if the element becomes invisible within the specified time.
   */
  public static boolean isTheElementInvisible(WebElement element, int timeInSeconds) {
    setImplicitWait(Duration.ofSeconds(0));
    try {
      waitElementDisappear(element, timeInSeconds);
      return true;
    } catch (NoSuchElementException | TimeoutException exception) {
      return false;
    } finally {
      resetImplicitWait();
    }
  }

  /**
   * Waits for the specified element to become invisible within a given time frame.
   *
   * @param element       The {@link WebElement} to wait for to become invisible.
   * @param timeInSeconds The time in seconds to wait for the element to become invisible.
   */
  public static void waitElementDisappear(WebElement element, int timeInSeconds) {
    createFluentWait(getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofSeconds(1),
      MESSAGE + element + "' is still visible", NoSuchElementException.class).until(
      ExpectedConditions.invisibilityOf(element));
  }

  /**
   * Checks if the specified element becomes available (visible and present)
   * within a given time frame.
   *
   * @param element       The {@link WebElement} to check for availability.
   * @param timeInSeconds The time in seconds to wait for the element to become available.
   * @return {@code true} if the element becomes available within the specified time.
   */
  public static boolean isTheElementAvailable(WebElement element, int timeInSeconds) {
    setImplicitWait(Duration.ofSeconds(0));
    try {
      waitElementWillBeAvailable(element, timeInSeconds);
      return true;
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    } finally {
      resetImplicitWait();
    }
  }

  /**
   * Waits for the specified element to become available (visible and present)
   * within a given time frame.
   *
   * @param element       The {@link WebElement} to wait for to become available.
   * @param timeInSeconds The time in seconds to wait for the element to become available.
   */
  public static void waitElementWillBeAvailable(WebElement element, int timeInSeconds) {
    createFluentWait(getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofSeconds(1),
      MESSAGE + element + "' is not available", NoSuchElementException.class)
      .until(not(ExpectedConditions.stalenessOf(element)));
  }

  /**
   * Waits for the specified element to become unavailable (either invisible or not present)
   * within a given time frame.
   *
   * @param element       The {@link WebElement} to wait for to become unavailable.
   * @param timeInSeconds The time in seconds to wait for the element to become unavailable.
   */
  public static void waitElementWillBeUnavailable(WebElement element, int timeInSeconds) {
    createFluentWait(getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofSeconds(1),
      MESSAGE + element + "' is available", NoSuchElementException.class)
      .until(ExpectedConditions.stalenessOf(element));
  }

  /**
   * Waits for the specified element to become clickable within a given time frame.
   *
   * @param timeInSeconds The time in seconds to wait for the element to become clickable.
   * @param element       The {@link WebElement} to wait for to become clickable.
   */
  public static void waitForElementToBeClickable(WebElement element, int timeInSeconds) {
    createFluentWait(getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofSeconds(1),
      MESSAGE + element + "' is not clickable", NoSuchElementException.class).until(
      ExpectedConditions.elementToBeClickable(element));
  }

  /**
   * Waits for the specified element to become selected within a given time frame.
   *
   * @param timeInSeconds The time in seconds to wait for the element to become selected.
   * @param element       The {@link WebElement} to wait for to become selected.
   */
  public static void waitForElementToBeSelected(int timeInSeconds, WebElement element) {
    createFluentWait(getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofSeconds(1),
      MESSAGE + element + "' is not selected", NoSuchElementException.class).until(
      ExpectedConditions.elementToBeSelected(element));
  }

  /**
   * Checks if the specified list of web elements is not empty within a given time frame.
   *
   * @param elements      The list of {@link WebElement} to check.
   * @param timeInSeconds The time in seconds to wait for the list to become non-empty.
   * @return {@code true} if the list is not empty within the specified time.
   */
  public static boolean isTheListOfElementsNotEmpty(List<WebElement> elements, int timeInSeconds) {
    setImplicitWait(Duration.ofSeconds(0));
    try {
      waitForListOfElementsIsNotEmpty(elements, timeInSeconds);
      return true;
    } catch (NoSuchElementException | TimeoutException e) {
      return false;
    } finally {
      resetImplicitWait();
    }
  }

  /**
   * Waits for the specified list of web elements to become non-empty within a given time frame.
   *
   * @param elements      The list of {@link WebElement} to wait for.
   * @param timeInSeconds The time in seconds to wait for the list to become non-empty.
   */
  public static void waitForListOfElementsIsNotEmpty(List<WebElement> elements,
                                                     int timeInSeconds) {
    createFluentWait(getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofSeconds(1),
      elements + " is empty", NoSuchElementException.class, TimeoutException.class)
      .until(driver -> !elements.isEmpty());
  }

  /**
   * Checks if the provided WebElement is visible.
   *
   * @param element The WebElement to check for visibility.
   * @return true if the element is visible, false otherwise.
   */
  public static boolean elementIsDisplayed(WebElement element) {
    setImplicitWait(Duration.ofSeconds(0));
    try {
      return element.isDisplayed();
    } catch (Exception e) {
      log.warn("Element does not exist");
      return false;
    } finally {
      resetImplicitWait();
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
    setImplicitWait(Duration.ofSeconds(timeInSeconds));
    try {
      return getDriver().findElement(By.xpath(xpathElement)).isDisplayed();
    } catch (Exception e) {
      log.warn("Element {} is not displayed", xpathElement);
      return false;
    } finally {
      resetImplicitWait();
    }
  }

  /**
   * Checks if the provided WebElement is enabled.
   *
   * @param element The WebElement to check enabled property.
   * @return true if the element is enabled, false otherwise.
   */
  public static boolean elementIsEnabled(WebElement element) {
    setImplicitWait(Duration.ofSeconds(0));
    try {
      return element.isEnabled();
    } catch (Exception e) {
      log.warn("Element is not enabled");
      return false;
    } finally {
      resetImplicitWait();
    }
  }

  /**
   * Checks if the provided WebElement is selected.
   *
   * @param element The WebElement to check selected property.
   * @return true if the element is selected, false otherwise.
   */
  public static boolean elementIsSelected(WebElement element) {
    setImplicitWait(Duration.ofSeconds(0));
    try {
      return element.isSelected();
    } catch (Exception e) {
      log.warn("Element is not selected");
      return false;
    } finally {
      resetImplicitWait();
    }
  }

  /**
   * Wait for element to be visible, if element is not visible it returns false and continues
   *
   * @param element       element to wait for
   * @param timeInSeconds time to wait the element
   * @return boolean indicating if element is found or not
   */
  public static boolean isTheElementVisible(WebElement element, int timeInSeconds) {
    setImplicitWait(Duration.ofSeconds(0));
    try {
      waitForElementVisibility(element, timeInSeconds);
      return element.isDisplayed();
    } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException e) {
      return false;
    } finally {
      resetImplicitWait();
    }
  }

  /**
   * Wait for element to be visible, if element is not visible it returns false and continues
   *
   * @param by            locator to find element
   * @param timeInSeconds time to wait the element
   * @return boolean indicating if element is found or not
   */
  public static boolean isTheElementVisible(By by, int timeInSeconds) {
    setImplicitWait(Duration.ofSeconds(0));
    try {
      return waitForElementVisibility(by, timeInSeconds).isDisplayed();
    } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException e) {
      return false;
    } finally {
      resetImplicitWait();
    }
  }

  /**
   * Waits for the specified element to become visible within a given time frame.
   *
   * @param element       The {@link WebElement} to wait for to become visible.
   * @param timeInSeconds The time in seconds to wait for the element to become visible.
   */
  public static void waitForElementVisibility(WebElement element, int timeInSeconds) {
    createFluentWait(getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofSeconds(1),
      MESSAGE + element + "' is not visible", NoSuchElementException.class).until(
      visibilityOf(element));
  }

  /**
   * Waits for the element located by the specified {@link By} locator to become
   * visible within a given time frame.
   *
   * @param by            The {@link By} locator used to find the element.
   * @param timeInSeconds The time in seconds to wait for the element to become visible.
   * @return The {@link WebElement} if it becomes visible within the specified time.
   */
  public static WebElement waitForElementVisibility(By by, int timeInSeconds) {
    createFluentWait(getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofSeconds(1),
      MESSAGE + by + "' is not visible", NoSuchElementException.class).until(
      ExpectedConditions.visibilityOfElementLocated(by));
    return getElement(by);
  }


  /**
   * Waits for a specified amount of time, allowing a process to finish.
   *
   * @param timeInSeconds The time in seconds to wait for the process to complete.
   */
  public static void waitForProcessToFinish(int timeInSeconds) {
    StopWatch stopWatch = StopWatch.createStarted();
    boolean firstTime = true;
    while (stopWatch.getTime(TimeUnit.SECONDS) < timeInSeconds) {
      if (firstTime) {
        log.info("Waiting for process to finish");
        firstTime = false;
      }
    }
    stopWatch.stop();
  }

  /**
   * Waits for the specified element's attribute to contain a specified value
   * within a given time frame.
   *
   * @param element       The {@link WebElement} whose attribute is to be checked.
   * @param attribute     The name of the attribute to check.
   * @param value         The value that the attribute should contain.
   * @param timeInSeconds Time in seconds to wait for the attribute to contain the specified value.
   * @return {@code true} if the attribute contains the specified value within the given time.
   */
  public static boolean isTheElementAttributeContainsTheValue(
    WebElement element, String attribute, String value, int timeInSeconds) {
    setImplicitWait(Duration.ofSeconds(0));
    try {
      waitForElementAttributeContainsValue(element, attribute, value, timeInSeconds);
      return true;
    } catch (Exception e) {
      return false;
    } finally {
      resetImplicitWait();
    }
  }

  /**
   * Waits for the specified element's attribute to contain a specified value
   * within a given time frame.
   *
   * @param element       The {@link WebElement} whose attribute is to be checked.
   * @param attribute     The name of the attribute to check.
   * @param value         The value that the attribute should contain.
   * @param timeInSeconds Time in seconds to wait for the attribute to contain the specified value.
   */
  public static void waitForElementAttributeContainsValue(
    WebElement element, String attribute, String value, int timeInSeconds) {
    createFluentWait(getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofSeconds(1),
      MESSAGE + element + "' with the attribute '" + attribute
        + "' not contains expected value " + value, NoSuchElementException.class)
      .until(attributeContains(element, attribute, value));
  }

  /**
   * Checks if the specified element's attribute does not contain a specified value
   * within a given time frame.
   *
   * @param element       The {@link WebElement} whose attribute is to be checked.
   * @param attribute     The name of the attribute to check.
   * @param value         The value that the attribute should not contain.
   * @param timeInSeconds Time in seconds to wait for the attribute not contain the specified value.
   * @return {@code true} if the attribute does not contain the specified value within that time.
   */
  public static boolean isTheElementAttributeNotContainsTheValue(
    WebElement element, String attribute, String value, int timeInSeconds) {
    setImplicitWait(Duration.ofSeconds(0));
    try {
      waitForElementAttributeNotContainsValue(element, attribute, value, timeInSeconds);
      return true;
    } catch (Exception e) {
      return false;
    } finally {
      resetImplicitWait();
    }
  }

  /**
   * Waits for the element's attribute to not contain a specified value within a given time frame.
   *
   * @param element       The {@link WebElement} whose attribute is to be checked.
   * @param attribute     The name of the attribute to check.
   * @param value         The value that the attribute should not contain.
   * @param timeInSeconds The time in seconds to wait for the attribute to not contain the value.
   */
  public static void waitForElementAttributeNotContainsValue(
    WebElement element, String attribute, String value, int timeInSeconds) {
    createFluentWait(getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofSeconds(1),
      MESSAGE + element + "' with attribute '" + attribute
        + "' contains expected value " + value, NoSuchElementException.class)
      .until(not(attributeContains(element, attribute, value)));
  }

  /**
   * Waits until an alert becomes visible within a given time frame and returns it.
   *
   * @param timeInSeconds The time in seconds to wait for the alert to become visible.
   * @return The {@link Alert} object if it becomes visible within the specified time.
   */
  public static Alert waitUntilAlertIsVisible(int timeInSeconds) {
    return createFluentWait(getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofSeconds(1),
      "Alert is not visible", NoSuchElementException.class).until(alertIsPresent());
  }

  /**
   * Checks if the element's attribute matches a specified value within a given time frame.
   *
   * @param element       The {@link WebElement} whose attribute is to be checked.
   * @param timeInSeconds The time in seconds to wait for the attribute to match the value.
   * @param attribute     The name of the attribute to check.
   * @param value         The value that the attribute should match.
   * @return {@code true} if the attribute matches the value within the time.
   */
  public static boolean isTheElementAttributeToBeValue(
    WebElement element, int timeInSeconds, String attribute, String value) {
    setImplicitWait(Duration.ofSeconds(0));
    try {
      waitForElementAttributeValue(element, attribute, value, timeInSeconds);
      return true;
    } catch (TimeoutException | NoSuchElementException e) {
      return false;
    } finally {
      resetImplicitWait();
    }
  }

  /**
   * Waits for the element's attribute to match a specified value within a given time frame.
   *
   * @param element       The {@link WebElement} whose attribute is to be checked.
   * @param attribute     The name of the attribute to check.
   * @param value         The value that the attribute should match.
   * @param timeInSeconds The time in seconds to wait for the attribute to match the value.
   */
  public static void waitForElementAttributeValue(
    WebElement element, String attribute, String value, int timeInSeconds) {
    createFluentWait(getDriver(), Duration.ofSeconds(timeInSeconds), Duration.ofSeconds(1),
      MESSAGE + element + "' with attribute '" + attribute
        + "' does not have the expected value " + value, NoSuchElementException.class)
      .until(ExpectedConditions.attributeToBe(element, attribute, value));
  }
}
