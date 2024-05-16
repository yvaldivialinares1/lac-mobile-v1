package com.automation.lac.qa.utils.mobile;

import static com.automation.lac.qa.utils.mobile.WaitActions.quickIsDisplayed;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.utils.CustomException;
import io.appium.java_client.AppiumDriver;
import java.time.Duration;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

@Slf4j
@UtilityClass
public class SwipeActions {

  private static AppiumDriver getDriver() {
    return MobileBaseScreen.getDriver();
  }

  /**
   * Performs a swipe action on the specified element in the given direction.
   *
   * @param element   The WebElement on which to perform the swipe action.
   * @param direction The direction in which to swipe, as represented by a SwipeDirections enum.
   */
  private void performSwipe(WebElement element, SwipeDirections direction) {
    var startX = element.getLocation().getX();
    var startY = element.getLocation().getY();
    Dimension elementSize = element.getSize();
    var x = elementSize.getWidth();
    var y = elementSize.getHeight();
    Point elementCenterPoint = getCenter(element);
    Dimension sizeScreen = MobileBaseScreen.getDriver().manage().window().getSize();

    switch (direction) {
      case DOWN_TO_UP -> swipe(startX + (x / 2), startY + y - 10, startX + (x / 2), startY + 10);
      case UP_TO_DOWN -> swipe(startX + (x / 2), startY + 10, startX + (x / 2), startY + y - 10);
      case RIGHT_TO_LEFT -> swipe(startY - 10, startY + (y / 2), startX + 10, startY + (y / 2));
      case LEFT_TO_RIGHT -> swipe(startX + 10, startY + (y / 2), startY - 10, startY + (y / 2));
      case TOP_PAGE ->
        swipe(elementCenterPoint.getX(), elementCenterPoint.getY(), elementCenterPoint.getX(), 0);
      case BOTTOM_PAGE ->
        swipe(elementCenterPoint.getX(), elementCenterPoint.getY(), elementCenterPoint.getX(),
          sizeScreen.getHeight());
      case RIGHT_PAGE ->
        swipe(elementCenterPoint.getX(), elementCenterPoint.getY(), sizeScreen.getWidth(),
          elementCenterPoint.getY());
      case LEFT_PAGE ->
        swipe(elementCenterPoint.getX(), elementCenterPoint.getY(), 0, elementCenterPoint.getY());
      default -> throw new CustomException("swipe direction to " + direction + " is invalid");
    }

  }

  /**
   * Swipes on a given element in a specified direction until another element is found.
   *
   * @param elementToFind The WebElement to search for during the swipe actions.
   * @param direction     The direction in which to swipe, as represented by a SwipeDirections enum.
   * @param swipeElement  The WebElement on which the swipe action will be performed.
   */
  public static void swipeUntilFindElement(WebElement elementToFind, SwipeDirections direction,
                                           WebElement swipeElement) {
    if (elementToFind == null)
      throw new CustomException("Element to find is NULL");
    while (!quickIsDisplayed(elementToFind)) {
      var previousPageSource = getDriver().getPageSource();
      performSwipe(swipeElement, direction);
      if (isTheSameDom(previousPageSource, getDriver().getPageSource()))
        throw new CustomException(
          "It is not possible to continue scrolling, it is the limit of the DOM");
    }
  }

  /**
   * Swipes on a given element in a specified direction until an element,
   * identified by its XPath, is found.
   *
   * @param xpathElementToFind The XPath of the element to search for during the swipe actions.
   * @param direction          The direction in which to swipe, as represented by a SwipeDirections.
   * @param swipeElement       The WebElement on which the swipe action will be performed.
   */
  public static void swipeUntilFindElement(String xpathElementToFind, SwipeDirections direction,
                                           WebElement swipeElement) {
    while (!quickIsDisplayed(xpathElementToFind, 1)) {
      var previousPageSource = getDriver().getPageSource();
      performSwipe(swipeElement, direction);
      if (isTheSameDom(previousPageSource, getDriver().getPageSource()))
        throw new CustomException(
          "It is not possible to continue scrolling, it is the limit of the DOM");
    }
  }

  /**
   * Performs a swipe action on the specified element in the given direction until the element
   * reaches the border of the screen.
   *
   * @param direction    The direction in which to swipe the element.
   * @param swipeElement The WebElement to be swiped towards the border of the screen.
   */
  public static void swipeElementToTheBorder(SwipeDirections direction, WebElement swipeElement) {
    performSwipe(swipeElement, direction);
  }

  /**
   * Performs a swipe action from a start point (x,y) to an end point (x,y).
   *
   * @param startX The starting x-coordinate for the swipe action.
   * @param startY The starting y-coordinate for the swipe action.
   * @param endX   The ending x-coordinate for the swipe action.
   * @param endY   The ending y-coordinate for the swipe action.
   */
  private void swipe(int startX, int startY, int endX, int endY) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 1)
      .addAction(
        finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
      .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
      .addAction(
        finger.createPointerMove(Duration.ofMillis(1500), PointerInput.Origin.viewport(), endX,
          endY))
      .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    getDriver().perform(List.of(swipe));
  }

  /**
   * Compares the DOM of two pages sources to determine if they are the same.
   *
   * @param previousPageSource The page source before a certain action was performed.
   * @param currentPageSource  The page source after the action was performed.
   * @return {@code true} if the DOM is the same (the page sources match), {@code false} otherwise.
   */
  private boolean isTheSameDom(String previousPageSource, String currentPageSource) {
    if (currentPageSource.isEmpty())
      return false;
    return currentPageSource.equals(previousPageSource);
  }

  /**
   * Calculates and returns the center point of the given web element.
   *
   * @param webElement The WebElement for which to find the center point.
   * @return The center point of the web element as a {@link Point} object.
   */
  public static Point getCenter(WebElement webElement) {
    Rectangle elementRectangle = webElement.getRect();
    return new Point(elementRectangle.getX() + elementRectangle.getWidth() / 2,
      elementRectangle.getY() + elementRectangle.getHeight() / 2);
  }
}
