package com.automation.lac.qa.fanapp.mobile.utils;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.utils.CustomException;
import com.automation.lac.qa.utils.mobile.SwipeDirections;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import java.time.Duration;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

@Slf4j
@UtilityClass
public class DeviceActions {

  private static String previousPageSource = "default";
  private static final String INVALID_SCROLL_DIRECTION = "Invalid scroll direction: ";
  private static final String NOT_FOUND_DURING_SCROLLING
    = "Element was not found during screen scrolling in direction ";

  public static AppiumDriver getDriver() {
    return MobileBaseScreen.getDriver();
  }

  /**
   * Performs a vertical scroll in the specified direction (UP or DOWN).
   *
   * @param direction       direction to scroll
   * @param percentToScroll value in percent defining the distance to move
   * @param description     description, appear on allure report: Swipe over + description
   */
  @Deprecated(forRemoval = true)
  public static void performSwipe(SwipeDirections direction, int percentToScroll,
                                  String description) {
    Dimension size = getDriver().manage().window().getSize();
    int screenHeight = size.getHeight();
    int screenWidth = size.getWidth();
    // Define the start points for the scroll as the center of the screen.
    int startX = size.getWidth() / 2;
    int startY = size.getHeight() / 2;
    int endX = startX;
    int endY = startY;

    // Calculates the end point for the Y-axis to perform the scroll.
    switch (direction) {
      case DOWN_TO_UP:
        endY = startY - ((screenHeight / 100) * percentToScroll);
        break;
      case UP_TO_DOWN:
        endY = startY + ((screenHeight / 100) * percentToScroll);
        break;
      case LEFT_TO_RIGHT:
        endX = startX - ((screenWidth / 100) * percentToScroll);
        break;
      case RIGHT_TO_LEFT:
        endX = startX + ((screenWidth / 100) * percentToScroll);
        break;
      default:
        throw new CustomException(INVALID_SCROLL_DIRECTION + direction);
    }
    swipe(700, startX, startY, endX, endY);
    Allure.step("Swipe over " + description);
  }

  /**
   * Scroll the screen from one point to another
   *
   * @param moveTime duration of movement applied
   * @param startX   X-axis starting point
   * @param startY   Y-axis starting point
   * @param endX     X-axis end point
   * @param endY     Y-axis end point
   */
  @Deprecated(forRemoval = true)
  public static void swipe(int moveTime, int startX, int startY, int endX,
                           int endY) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    Sequence swipe = new Sequence(finger, 1)
      .addAction(
        finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
      .addAction(
        finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
      .addAction(
        finger.createPointerMove(Duration.ofMillis(moveTime), PointerInput.Origin.viewport(), endX,
          endY))
      .addAction(
        finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    getDriver().perform(List.of(swipe));

  }

  /**
   * This method swipes over an element, vertical or horizontal
   *
   * @param webElement     Web element to be swiped over
   * @param direction      VERTICAL OR HORIZONTAL, SwipeDirections enum
   * @param percentToStart Percent of width or height to start
   * @param percentToEnd   Percent of width or height to end
   * @param description    Description, it appears on allure report, Swipe until + description
   */
  @Deprecated(forRemoval = true)
  public static void performSwipeOverAnElement(WebElement webElement, SwipeDirections direction,
                                               float percentToStart, float percentToEnd,
                                               String description) {
    Rectangle webElementRect = webElement.getRect();
    int startX = 0;
    int startY = 0;
    int endX = 0;
    int endY = 0;
    float elementHeight = webElementRect.getHeight();
    float elementWidth = webElementRect.getWidth();
    switch (direction) {
      case VERTICAL:
        startY = Math.round(elementHeight * (percentToStart / 100) + webElementRect.getY());
        endY = Math.round(elementHeight * (percentToEnd / 100) + webElementRect.getY());
        startX = Math.round((elementWidth / 2) + webElementRect.getX());
        endX = startX;
        break;
      case HORIZONTAL:
        startX = Math.round(elementWidth * (percentToStart / 100) + webElementRect.getX());
        endX = Math.round(elementWidth * (percentToEnd / 100) + webElementRect.getX());
        startY = Math.round((elementHeight / 2) + webElementRect.getY());
        endY = startY;
        break;
      default:
        break;
    }
    swipe(700, startX, startY, endX, endY);
    Allure.step("Swipe over " + description);
  }

  /**
   * getFluentWait
   *
   * @return new FluentWait instance
   */
  @Deprecated(forRemoval = true)
  private static FluentWait<WebDriver> getCustomFluentWait(int seconds) {
    return new FluentWait<WebDriver>(MobileBaseScreen.getDriver())
      .withTimeout(Duration.ofSeconds(seconds))
      .pollingEvery(Duration.ofSeconds(1))
      .ignoring(StaleElementReferenceException.class)
      .ignoring(NoSuchElementException.class)
      .ignoring(Exception.class);
  }

  /**
   * Checks if the end of a scrollable section is reached
   * by comparing the current page source with the previous one.
   *
   * @param currentPageSource value of previous page source
   * @return boolean whether the page source is changed after scrolling
   */
  @Deprecated(forRemoval = true)
  public static boolean isScrolledToTheEndOfDirection(String currentPageSource) {
    if (currentPageSource.isEmpty()) {
      return false;
    }
    boolean isEquals = currentPageSource.equals(previousPageSource);
    previousPageSource = currentPageSource;
    return isEquals;
  }

  /**
   * Checks if the provided WebElement is visible.
   *
   * @param element The WebElement to check for visibility.
   * @return true if the element is visible, false otherwise.
   */
  @Deprecated(forRemoval = true)
  public static boolean quickIsDisplayed(WebElement element) {
    Duration originalImplicitWait = getDriver().manage().timeouts().getImplicitWaitTimeout();
    getDriver().manage().timeouts().implicitlyWait(Duration.ZERO);
    try {
      element.isDisplayed();
      return true;
    } catch (Exception e) {
      log.warn("Element is not displayed");
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(originalImplicitWait);
    }
  }

  /**
   * Wait for element to be visible, if element is not visible it returns false and continues
   *
   * @param element       element to wait for
   * @param timeInSeconds time to wait the element
   * @return boolean indicating if element is found or not
   */
  @Deprecated(forRemoval = true)
  public static boolean waitForElementVisibility(WebElement element, int timeInSeconds) {
    Duration originalImplicitWait = getDriver().manage().timeouts().getImplicitWaitTimeout();
    getDriver().manage().timeouts().implicitlyWait(Duration.ZERO);
    try {
      getCustomFluentWait(timeInSeconds).until(ExpectedConditions.visibilityOf(element));
      return element != null && element.isDisplayed();
    } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException var4) {
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(originalImplicitWait);
    }
  }

  /**
   * Scrolls vertically in the specified direction (UP or DOWN)
   * until it finds the element or reaches the end of the scrollable area.
   *
   * @param expected        expected webElement
   * @param direction       direction to scroll
   * @param secondsToWait   time in seconds wait element to be displayed after scrolling
   * @param percentToScroll value in percent defining the distance to move
   * @return WebElement found element
   */
  @Deprecated(forRemoval = true)
  public WebElement verticallyScrollToElement(WebElement expected, SwipeDirections direction,
                                              int secondsToWait,
                                              int percentToScroll) {
    String currentPageSource = "";
    while (Boolean.TRUE.equals(!isScrolledToTheEndOfDirection(currentPageSource))
      && !waitForElementVisibility(expected, secondsToWait)) {
      performSwipe(direction, percentToScroll, "scroll to element");
      currentPageSource = getDriver().getPageSource();
    }

    if (!quickIsDisplayed(expected)) {
      throw new NoSuchElementException(NOT_FOUND_DURING_SCROLLING + direction);
    }
    return expected;
  }
}
