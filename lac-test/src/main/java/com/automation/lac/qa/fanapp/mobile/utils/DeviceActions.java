package com.automation.lac.qa.fanapp.mobile.utils;

import static com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections.DOWN_TO_UP;
import static com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections.LEFT_TO_RIGHT;
import static com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections.RIGHT_TO_LEFT;
import static com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections.UP_TO_DOWN;

import com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections;
import com.automation.lac.qa.fanapp.mobile.enums.TapCoordinates;
import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.utils.CustomException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.Widget;
import io.qameta.allure.Allure;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
   * Provides a way to tap an element based on its coordinates
   *
   * @param element        Element to receive the tap
   * @param tapCoordinates position of the element to click
   * @param addX           horizontal pixel count to tap from the specified tap coordinate
   * @param addY           vertical pixel count to tap from the specified tap coordinate
   */
  @Deprecated(forRemoval = true)
  public static void clickUsingPointerOptions(WebElement element, TapCoordinates tapCoordinates,
                                              int addX, int addY, String description) {
    Point location = element.getLocation();
    Dimension size = element.getSize();
    int leftX = location.getX();
    int rightX = leftX + size.getWidth();
    int middleX = (rightX + leftX) / 2;
    int upperY = location.getY();
    int lowerY = upperY + size.getHeight();
    int middleY = (upperY + lowerY) / 2;
    int x = addX;
    int y = addY;
    switch (tapCoordinates) {
      case TOP_LEFT:
        x += leftX;
        y += upperY;
        break;
      case TOP_MIDDLE:
        x += middleX;
        y += upperY;
        break;
      case TOP_RIGHT:
        x += rightX;
        y += upperY;
        break;
      case MIDDLE_LEFT:
        x += leftX;
        y += middleY;
        break;
      case MIDDLE:
        x += middleX;
        y += middleY;
        break;
      case MIDDLE_RIGHT:
        x += rightX;
        y += middleY;
        break;
      case BOTTOM_LEFT:
        x += leftX;
        y += lowerY;
        break;
      case BOTTOM_MIDDLE:
        x += middleX;
        y += lowerY;
        break;
      case BOTTOM_RIGHT:
        x += rightX;
        y += lowerY;
        break;
      default:
        x += middleX;
        y += middleY;
        break;
    }
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence tap = new Sequence(finger, 1);
    tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
      PointerInput.Origin.viewport(), x, y));
    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    tap.addAction(new Pause(finger, Duration.ofMillis(100)));
    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    getDriver().perform(List.of(tap));
    Allure.step("Click over " + description);
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

  @Deprecated(forRemoval = true)
  public static void swipe(SwipeDirections direction) {
    swipe(direction, 0.25, 0.25, 0.25, 0.25, false);
  }

  @Deprecated(forRemoval = true)
  public static void swipe(SwipeDirections direction, double leftRatio, double rightRatio,
                           double upRatio, double downRatio, boolean isFlexible) {
    swipe(direction, leftRatio, rightRatio, upRatio, downRatio, isFlexible, 1);
  }

  /**
   * It will swipe from the center position of the given webElement in the provided direction till
   * the ending ratio relative to screen.
   * <br><br>Below is an example to swipe from the center of an element in up direction in 1 second
   * and leave 10 percentage of the upper screen bound.
   * <pre>
   * swipe(DOWN_TO_UP, element, 0.1, 1);
   * </pre>
   *
   * @param direction    DOWN_TO_UP, UP_TO_DOWN, RIGHT_TO_LEFT, LEFT_TO_RIGHT
   * @param element      WebElement from which we need to swipe through
   * @param ratioToLeave percentage ratio(0 to 1) to leave(relative to screen)
   *                     from the ending side of the swipe
   * @param duration     seconds to be taken to execute the action
   */
  @Deprecated(forRemoval = true)
  public static void swipe(SwipeDirections direction, WebElement element, double ratioToLeave,
                           double duration) {
    int milliDuration = (int) (duration * 1000);
    Point elementCenterPoint = getCenter(element);
    int startX = elementCenterPoint.getX();
    int startY = elementCenterPoint.getY();
    Dimension dim = MobileBaseScreen.getDriver().manage().window().getSize();
    int x = dim.getWidth();
    int y = dim.getHeight();
    int horizontalScreenRatio = (int) (x * ratioToLeave);
    int verticalScreenRatio = (int) (y * ratioToLeave);
    int endX;
    if ((direction.equals(DOWN_TO_UP) || direction.equals(UP_TO_DOWN))) {
      endX = elementCenterPoint.getX();
    } else {
      if (direction.equals(LEFT_TO_RIGHT)) {
        int ratio = x - horizontalScreenRatio;
        endX = ratio <= startX ? x : ratio;
      } else
        endX = horizontalScreenRatio >= startX ? 0 : horizontalScreenRatio;
    }
    int endY;
    if ((direction.equals(LEFT_TO_RIGHT) || direction.equals(RIGHT_TO_LEFT))) {
      endY = elementCenterPoint.getY();
    } else {
      if (direction.equals(UP_TO_DOWN)) {
        int ratio = y - verticalScreenRatio;
        endY = ratio <= startY ? y : ratio;
      } else
        endY = verticalScreenRatio >= startY ? 0 : verticalScreenRatio;
    }
    swipe(milliDuration, startX, startY, endX, endY);
  }

  /**
   * Swipe the screen with given parameters
   *
   * @param direction  DOWN_TO_UP, UP_TO_DOWN, RIGHT_TO_LEFT, LEFT_TO_RIGHT
   * @param leftRatio  percentage to leave from left
   * @param rightRatio percentage to leave from right
   * @param upRatio    percentage to leave from up
   * @param downRatio  percentage to leave from down
   * @param isFlexible if true it will take all the ratios as is
   *                   otherwise it will take middle position to swipe the screen
   * @param duration   seconds to be taken to execute the action
   */
  @Deprecated(forRemoval = true)
  public static void swipe(SwipeDirections direction, double leftRatio, double rightRatio,
                           double upRatio, double downRatio, boolean isFlexible,
                           double duration) {
    int milliDuration = (int) (duration * 1000);
    Dimension dim = MobileBaseScreen.getDriver().manage().window().getSize();
    int x = dim.getWidth();
    int horizontalMid = x / 2;
    int left = (leftRatio == 0) ? x : (int) (x * leftRatio);
    int right = (rightRatio == 0) ? x : (int) (x * rightRatio);
    int y = dim.getHeight();
    int verticalMid = y / 2;
    int up = (upRatio == 0) ? y : (int) (y * upRatio);
    int down = (downRatio == 0) ? y : (int) (y * downRatio);
    switch (direction) {
      case DOWN_TO_UP -> {
        if (!isFlexible)
          swipe(milliDuration, horizontalMid, y - down, horizontalMid, up);
        else
          swipe(milliDuration, left, y - down, right, up);
      }
      case UP_TO_DOWN -> {
        if (!isFlexible)
          swipe(milliDuration, horizontalMid, up, horizontalMid, y - down);
        else
          swipe(milliDuration, left, up, right, y - down);
      }
      case RIGHT_TO_LEFT -> {
        if (!isFlexible)
          swipe(milliDuration, x - right, verticalMid, left, verticalMid);
        else
          swipe(milliDuration, x - right, up, left, down);
      }
      case LEFT_TO_RIGHT -> {
        if (!isFlexible)
          swipe(milliDuration, left, verticalMid, x - right, verticalMid);
        else
          swipe(milliDuration, left, up, x - right, down);
      }
      default -> throw new CustomException("swipe format is invalid");
    }
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
   * Returns the center point of the given webElement
   *
   * @param webElement element
   * @return point forming the center coordinate of element
   */
  @Deprecated(forRemoval = true)
  public Point getCenter(WebElement webElement) {
    Rectangle elementRectangle = webElement.getRect();
    return new Point(elementRectangle.getX() + elementRectangle.getWidth() / 2,
      elementRectangle.getY() + elementRectangle.getHeight() / 2);
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
   * Wait for element become not displayed and not enabled
   *
   * @return boolean indicating if the element is no longer displayed and not enabled
   */
  @Deprecated(forRemoval = true)
  public static Boolean waitForElementNotVisibleOrDisabled(int seconds, WebElement element) {
    return getCustomFluentWait(seconds).until(new ExpectedCondition<>() {
      @Override
      public Boolean apply(WebDriver driver) {
        try {
          // Check if the element is not displayed or not enabled
          return !element.isDisplayed() && !element.isEnabled();
        } catch (Exception e) {
          return true;
        }
      }

      @Override
      public String toString() {
        return "element to not be visible or not be enabled: " + element;
      }
    });
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
   * Wait for element become not displayed
   *
   * @return boolean indicating if the element is no longer displayed
   */
  @Deprecated(forRemoval = true)
  public static Boolean waitForElementInvisibility(int seconds, WebElement element) {
    return getCustomFluentWait(seconds).until(ExpectedConditions.invisibilityOf(element));
  }

  /**
   * Wait for element to become clickable
   *
   * @return WebElement the element when it is in clickable state
   */
  @Deprecated(forRemoval = true)
  public static WebElement waitForElementBeClickable(int seconds, WebElement element) {
    return getCustomFluentWait(seconds).until(ExpectedConditions.elementToBeClickable(element));
  }

  /**
   * Scrolls into list of elements in the specified direction (LEFT, RIGHT, UP, DOWN)
   * until it finds the element with specific condition or reaches the end of the scrollable area.
   *
   * @param visibleRelatives List WebElement to scroll into
   * @param direction        direction to scroll
   * @param percentToScroll  value in percent defining the distance to move
   * @param condition        condition expected element to have
   * @return WebElement found element
   */
  @Deprecated(forRemoval = true)
  public static WebElement scrollIntoListToElementWithCondition(List<WebElement> visibleRelatives,
                                                                SwipeDirections direction,
                                                                int percentToScroll,
                                                                Predicate<WebElement> condition) {

    if (Boolean.FALSE.equals(waitForListWebElementsIsNotEmpty(7, visibleRelatives))) {
      throw new CustomException("No displayed elements found in the list");
    }
    String previousPageSource = "";
    Supplier<List<WebElement>> currentCollection = () -> visibleRelatives.stream()
      .filter(WebElement::isDisplayed).toList();

    Optional<WebElement> expected =
      currentCollection.get().stream().filter(condition).findFirst();
    if (expected.isPresent() && (expected.get().isDisplayed())) {
      return expected.get();
    }

    while (Boolean.TRUE.equals(!isScrolledToTheEndOfDirection(previousPageSource))) {
      performSwipe(direction, percentToScroll, "Event List");
      expected = currentCollection.get().stream().filter(condition).findFirst();
      if (expected.isPresent() && (expected.get().isDisplayed())) {
        return expected.get();
      }
      previousPageSource = getDriver().getPageSource();
    }
    throw new CustomException(NOT_FOUND_DURING_SCROLLING + direction);
  }

  /**
   * Wait for list of webElements become not empty
   *
   * @return boolean indicating if list is not empty
   */
  @Deprecated(forRemoval = true)
  public static Boolean waitForListWebElementsIsNotEmpty(int seconds, List<WebElement> elements) {
    return getCustomFluentWait(seconds).until(driver -> !elements.isEmpty());
  }

  /**
   * Wait for list of webElements become not empty
   *
   * @return boolean indicating if list is not empty
   */
  @Deprecated(forRemoval = true)
  public static Boolean waitForListWidgetsIsNotEmpty(int seconds, List<? extends Widget> elements) {
    return getCustomFluentWait(seconds).until(driver -> !elements.isEmpty());
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
   * Checks if the provided WebElement is enabled.
   *
   * @param element The WebElement to check enabled property.
   * @return true if the element is enabled, false otherwise.
   */
  @Deprecated(forRemoval = true)
  public static boolean quickIsEnabled(WebElement element) {
    Duration originalImplicitWait = getDriver().manage().timeouts().getImplicitWaitTimeout();
    getDriver().manage().timeouts().implicitlyWait(Duration.ZERO);
    try {
      element.isEnabled();
      return true;
    } catch (Exception e) {
      log.warn("Element is not enabled");
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(originalImplicitWait);
    }
  }

  /**
   * Checks if the provided WebElement is selected.
   *
   * @param element The WebElement to check selected property.
   * @return true if the element is selected, false otherwise.
   */
  @Deprecated(forRemoval = true)
  public static boolean quickIsSelected(WebElement element) {
    Duration originalImplicitWait = getDriver().manage().timeouts().getImplicitWaitTimeout();
    getDriver().manage().timeouts().implicitlyWait(Duration.ZERO);
    try {
      element.isSelected();
      return true;
    } catch (Exception e) {
      log.warn("Element is not selected");
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
   * Wait for element to be visible, if element is not visible it returns false and continues
   *
   * @param by            locator to find element
   * @param timeInSeconds time to wait the element
   * @return boolean indicating if element is found or not
   */
  @Deprecated(forRemoval = true)
  public static boolean waitForElementVisibility(By by, int timeInSeconds) {
    Duration originalImplicitWait = getDriver().manage().timeouts().getImplicitWaitTimeout();
    getDriver().manage().timeouts().implicitlyWait(Duration.ZERO);
    try {
      WebElement element = getCustomFluentWait(timeInSeconds)
        .until(ExpectedConditions.visibilityOfElementLocated(by));
      return element.isDisplayed();
    } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException var4) {
      return false;
    } finally {
      getDriver().manage().timeouts().implicitlyWait(originalImplicitWait);
    }
  }

  /**
   * Wait for element to be enabled
   *
   * @return boolean indicating if the element is enabled
   */
  @Deprecated(forRemoval = true)
  public static Boolean waitForElementToBeEnabled(int seconds, WebElement element) {
    return getCustomFluentWait(seconds).until(
      ExpectedConditions.attributeToBe(element, "enabled", "true"));
  }

  /**
   * Retrieves the web element based on the provided locator
   * Catches NoSuchElementException, StaleElementReferenceException, and other exceptions,
   * logging them with Allure
   */
  @Deprecated(forRemoval = true)
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
