package com.automation.lac.qa.utils.mobile;

import static com.automation.lac.qa.pages.MobileBaseScreen.getDriver;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.DOWN_TO_UP;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.LEFT_TO_RIGHT;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.RIGHT_TO_LEFT;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.UP_TO_DOWN;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheListOfElementsNotEmpty;
import static com.automation.lac.qa.utils.mobile.WaitActions.quickIsDisplayed;
import static com.automation.lac.qa.utils.mobile.WaitActions.resetImplicitWait;
import static com.automation.lac.qa.utils.mobile.WaitActions.setImplicitWait;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForProcessToFinish;

import com.automation.lac.qa.utils.CustomException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

@Slf4j
@UtilityClass
public class SwipeActions {

  private final String limitOfDomExceptionMessage =
    "It is not possible to continue scrolling, it is the limit of the DOM";

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
    Dimension sizeScreen = getDriver().manage().window().getSize();
    int borderY = (int) Math.round(y * 0.2);
    int borderX = (int) Math.round(x * 0.2);

    int startXCord;
    int startYCord;
    int endXCord = 0;
    int endYCord = 0;

    switch (direction) {
      case DOWN_TO_UP -> {
        startXCord = startX + (x / 2);
        startYCord = startY + y - borderY;
        endXCord = startX + (x / 2);
        endYCord = startY + borderY;
      }
      case UP_TO_DOWN -> {
        startXCord = startX + (x / 2);
        startYCord = startY + borderY;
        endXCord = startX + (x / 2);
        endYCord = startY + y - borderY;
      }
      case RIGHT_TO_LEFT -> {
        startXCord = startX + x - borderX;
        startYCord = startY + (y / 2);
        endXCord = startX + borderX;
        endYCord = startY + (y / 2);
      }
      case LEFT_TO_RIGHT -> {
        startXCord = startX + borderX;
        startYCord = startY + (y / 2);
        endXCord = startX + x - borderX;
        endYCord = startY + (y / 2);
      }
      case TOP_PAGE -> {
        startXCord = elementCenterPoint.getX();
        startYCord = elementCenterPoint.getY();
        endXCord = elementCenterPoint.getX();
      }
      case BOTTOM_PAGE -> {
        startXCord = elementCenterPoint.getX();
        startYCord = elementCenterPoint.getY();
        endXCord = elementCenterPoint.getX();
        endYCord = sizeScreen.getHeight();
      }
      case RIGHT_PAGE -> {
        startXCord = elementCenterPoint.getX();
        startYCord = elementCenterPoint.getY();
        endXCord = sizeScreen.getWidth();
        endYCord = elementCenterPoint.getY();
      }
      case LEFT_PAGE -> {
        startXCord = elementCenterPoint.getX();
        startYCord = elementCenterPoint.getY();
        endYCord = elementCenterPoint.getY();
      }
      default -> throw new CustomException("swipe direction to " + direction + " is invalid");
    }

    swipe(startXCord, startYCord, endXCord, endYCord);
  }

  /**
   * Swipes on a given element in a specified direction until another element is found.
   * DOWN_TO_UP, UP_TO_DOWN, RIGHT_TO_LEFT or LEFT_TO_RIGHT
   *
   * @param elementToFind The WebElement to search for during the swipe actions.
   * @param direction     The direction in which to swipe, as represented by a SwipeDirections enum.
   * @param swipeElement  The WebElement on which the swipe action will be performed.
   */
  public static void swipeUntilFindElement(WebElement elementToFind, SwipeDirections direction,
                                           WebElement swipeElement) {
    if (elementToFind == null)
      throw new CustomException("Element to find is NULL");
    while (!WaitActions.elementIsDisplayed(elementToFind)) {
      var previousPageSource = getDriver().getPageSource();
      performSwipe(swipeElement, direction);
      waitForProcessToFinish(1);
      if (isTheSameDom(previousPageSource))
        throw new CustomException(limitOfDomExceptionMessage);
    }
    // Below code is to swipe the screen once in the same direction if the element is at the boarder
    if (WaitActions.elementIsDisplayed(elementToFind)
      && isElementAtBoarderWhileSwiping(elementToFind, direction, swipeElement)) {
      swipe(direction);
    }
  }

  /**
   * Swipes on a given element in a specified direction until an element,
   * identified by its XPath, is found.
   * DOWN_TO_UP, UP_TO_DOWN, RIGHT_TO_LEFT or LEFT_TO_RIGHT
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
      if (isTheSameDom(previousPageSource))
        throw new CustomException(limitOfDomExceptionMessage);
    }
  }

  /**
   * Swipes on the screen in a specified direction until another element is found.
   *
   * @param elementToFind The WebElement to search for during the swipe actions.
   * @param direction     The direction in which to swipe, as represented by a SwipeDirections enum.
   */
  public static void swipeUntilFindElement(WebElement elementToFind, SwipeDirections direction) {
    if (elementToFind == null)
      throw new CustomException("Element to find is NULL");
    while (!WaitActions.elementIsDisplayed(elementToFind)) {
      String previousPageSource = getDriver().getPageSource();
      swipe(direction);
      waitForProcessToFinish(1);
      if (isTheSameDom(previousPageSource))
        throw new CustomException(limitOfDomExceptionMessage);
    }
    // Below code is to swipe the screen once in the same direction if the element is at the boarder
    if (WaitActions.elementIsDisplayed(elementToFind)
      && isElementAtBoarderWhileSwiping(elementToFind, direction)) {
      swipe(direction);
    }
  }

  /**
   * It will check if the element is at the boarder while swiping through the screen
   * according to swipe direction
   *
   * @param element   element to check for the boarder
   * @param direction swipe direction in which element may end up at boarder
   * @return true if element is at the boarder otherwise false
   */
  public static boolean isElementAtBoarderWhileSwiping(WebElement element,
                                                       SwipeDirections direction) {
    Rectangle rectangle = element.getRect();
    Dimension dimension = getDriver().manage().window().getSize();
    return (direction.equals(DOWN_TO_UP) && ((rectangle.getY() + rectangle.getHeight())
      >= (dimension.getHeight() - dimension.getHeight() * 0.1)))
      || (direction.equals(UP_TO_DOWN) && (rectangle.getY() <= dimension.getHeight() * 0.1))
      || (direction.equals(RIGHT_TO_LEFT) && ((rectangle.getX() + rectangle.getWidth())
      >= (dimension.getWidth() - dimension.getWidth() * 0.1)))
      || (direction.equals(LEFT_TO_RIGHT) && (rectangle.getX() <= dimension.getWidth() * 0.1));
  }

  /**
   * It will check if the element is at the boarder while swiping over the swipeElement
   * according to swipe direction
   *
   * @param element      element to check for the boarder
   * @param direction    swipe direction in which element may end up at boarder
   * @param swipeElement The WebElement on which the swipe action will be performed.
   * @return true if element is at the boarder otherwise false
   */
  public static boolean isElementAtBoarderWhileSwiping(WebElement element,
                                                       SwipeDirections direction,
                                                       WebElement swipeElement) {
    Rectangle elementRect = element.getRect();
    Rectangle swipeElementRect = swipeElement.getRect();
    return switch (direction) {
      case DOWN_TO_UP -> ((elementRect.getY() + elementRect.getHeight())
        >= (swipeElementRect.getY() + swipeElementRect.getHeight()
        - swipeElementRect.getHeight() * 0.1));
      case UP_TO_DOWN ->
        (elementRect.getY() <= (swipeElementRect.getY() + swipeElementRect.getHeight() * 0.1));
      case RIGHT_TO_LEFT -> ((elementRect.getX() + elementRect.getWidth())
        >= (elementRect.getX() + elementRect.getWidth() - swipeElementRect.getWidth() * 0.1));
      case LEFT_TO_RIGHT ->
        (elementRect.getX() <= (swipeElementRect.getX() + swipeElementRect.getWidth() * 0.1));
      default -> false;
    };
  }

  /**
   * Swipe into list of elements in the specified direction (LEFT, RIGHT, UP, DOWN)
   * until it finds the element with specific condition or reaches the end of the scrollable area.
   *
   * @param visibleRelatives List WebElement to scroll into
   * @param direction        direction to scroll
   * @param condition        condition expected element to have
   * @return WebElement found element
   */
  public static WebElement swipeIntoListToElementWithCondition(List<WebElement> visibleRelatives,
                                                               SwipeDirections direction,
                                                               Predicate<WebElement> condition) {

    if (Boolean.FALSE.equals(isTheListOfElementsNotEmpty(visibleRelatives, 5))) {
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

    while (Boolean.TRUE.equals(!isTheSameDom(previousPageSource))) {
      previousPageSource = getDriver().getPageSource();

      List<WebElement> webElements = currentCollection.get();
      swipeIntoTheListOfElements(webElements, direction);
      expected = currentCollection.get().stream().filter(condition).findFirst();
      if (expected.isPresent() && (expected.get().isDisplayed())) {
        return expected.get();
      }
    }
    throw new CustomException("Cannot find the element with provided condition in the list");
  }

  /**
   * Swipe into list of elements based on a common size
   * of elements(element size * amount of elements).
   *
   * @param webElements List of webElements to swipe
   */
  private static void swipeIntoTheListOfElements(List<WebElement> webElements,
                                                 SwipeDirections direction) {
    WebElement element = webElements.get(0);
    int x = element.getLocation().getX();
    int y = element.getLocation().getY();
    int height = element.getRect().getHeight();

    switch (direction) {
      case DOWN_TO_UP -> swipe(x, y + (height * webElements.size()), x, y - height);
      case UP_TO_DOWN -> throw new CustomException("Method is not implemented for " + direction);
      //TODO: Add implementation for different directions
      default -> throw new CustomException("swipe direction '" + direction + "' is invalid");
    }
  }

  /**
   * Performs a swipe action on the specified element in the given direction until the element
   * reaches the border of the screen.
   * TOP_PAGE, BUTTON_PAGE, RIGHT_PAGE or LEFT_PAGE
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
    swipe(1500, startX, startY, endX, endY);
  }

  /**
   * Performs a swipe action from a start point (x,y) to an end point (x,y).
   *
   * @param duration duration of movement applied
   * @param startX   The starting x-coordinate for the swipe action.
   * @param startY   The starting y-coordinate for the swipe action.
   * @param endX     The ending x-coordinate for the swipe action.
   * @param endY     The ending y-coordinate for the swipe action.
   */
  private void swipe(int duration, int startX, int startY, int endX, int endY) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 1)
      .addAction(
        finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
      .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
      .addAction(
        finger.createPointerMove(Duration.ofMillis(duration), PointerInput.Origin.viewport(), endX,
          endY))
      .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    getDriver().perform(List.of(swipe));
  }

  /**
   * @param direction The direction in which to swipe the element.
   */
  public static void swipe(SwipeDirections direction) {
    swipe(direction, 0.25, 0.25, 0.25, 0.25, false);
  }

  /**
   * It will swipe in the provided direction till the ending ratio relative to screen.
   *
   * @param direction  The direction in which to swipe the screen.
   * @param leftRatio  ratio(0 to 1) to leave(relative to screen) from the ending side of the swipe
   * @param rightRatio ratio(0 to 1) to leave(relative to screen) from the ending side of the swipe
   * @param upRatio    ratio(0 to 1) to leave(relative to screen) from the ending side of the swipe
   * @param downRatio  ratio(0 to 1) to leave(relative to screen) from the ending side of the swipe
   * @param isFlexible if true it will take all the ratios as is
   *                   otherwise it will take middle position to swipe the screen
   */
  public static void swipe(SwipeDirections direction, double leftRatio, double rightRatio,
                           double upRatio, double downRatio, boolean isFlexible) {
    swipe(direction, leftRatio, rightRatio, upRatio, downRatio, isFlexible, 1);
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
  public static void swipe(SwipeDirections direction, double leftRatio, double rightRatio,
                           double upRatio, double downRatio, boolean isFlexible,
                           double duration) {
    validateRatios(leftRatio, rightRatio, upRatio, downRatio);
    int milliDuration = (int) (duration * 1000);
    Dimension dim = getDriver().manage().window().getSize();
    int x = dim.getWidth();
    int horizontalMid = x / 2;
    int left = (int) (x * leftRatio);
    int right = (int) (x - x * rightRatio);
    int y = dim.getHeight();
    int verticalMid = y / 2;
    int up = (int) (y * upRatio);
    int down = (int) (y - y * downRatio);
    switch (direction) {
      case DOWN_TO_UP -> {
        if (!isFlexible) swipe(milliDuration, horizontalMid, down, horizontalMid, up);
        else swipe(milliDuration, left, down, right, up);
      }
      case UP_TO_DOWN -> {
        if (!isFlexible) swipe(milliDuration, horizontalMid, up, horizontalMid, down);
        else swipe(milliDuration, left, up, right, down);
      }
      case RIGHT_TO_LEFT -> {
        if (!isFlexible) swipe(milliDuration, right, verticalMid, left, verticalMid);
        else swipe(milliDuration, right, up, left, down);
      }
      case LEFT_TO_RIGHT -> {
        if (!isFlexible) swipe(milliDuration, left, verticalMid, right, verticalMid);
        else swipe(milliDuration, left, up, right, down);
      }
      default -> throw new CustomException("swipe format is invalid");
    }
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
  public static void swipe(SwipeDirections direction, WebElement element, double ratioToLeave,
                           double duration) {
    validateRatios(ratioToLeave);
    int milliDuration = (int) (duration * 1000);
    Point elementCenterPoint = getCenter(element);
    int startX = elementCenterPoint.getX();
    int startY = elementCenterPoint.getY();
    Dimension dim = getDriver().manage().window().getSize();
    int x = dim.getWidth();
    int y = dim.getHeight();
    int horizontalScreenRatio = (int) (x * ratioToLeave);
    int verticalScreenRatio = (int) (y * ratioToLeave);

    int endX = startX;
    int endY = startY;

    switch (direction) {
      case LEFT_TO_RIGHT:
        endX = Math.min(x, x - horizontalScreenRatio);
        break;
      case RIGHT_TO_LEFT:
        endX = Math.max(0, horizontalScreenRatio);
        break;
      case UP_TO_DOWN:
        endY = Math.min(y, y - verticalScreenRatio);
        break;
      case DOWN_TO_UP:
        endY = Math.max(0, verticalScreenRatio);
        break;
      default:
        throw new CustomException("swipe direction is invalid");
    }
    swipe(milliDuration, startX, startY, endX, endY);
  }


  private void validateRatios(double... ratios) {
    for (double ratio : ratios) {
      if (ratio < 0 || ratio > 1) {
        throw new IllegalArgumentException("Ratios must be between 0 and 1");
      }
    }
  }

  /**
   * Compares the DOM of two pages sources to determine if they are the same.
   *
   * @param previousPageSource The page source before a certain action was performed.
   * @return {@code true} if the DOM is the same (the page sources match), {@code false} otherwise.
   */
  public boolean isTheSameDom(String previousPageSource) {
    return getDriver().getPageSource().equals(previousPageSource);
  }


  /**
   * store the attribute value from list of webElements based on index by
   * scrolling in given direction
   * until reach to end of screen or until given element is displayed,
   *
   * @param locator     dynamic locator
   * @param destination WebElement to which scroll is required (optional)
   * @param attribute   attribute value to be stored from list
   * @param direction   The direction in which to swipe the element.
   * @param duration    seconds to be taken to execute the swipe action
   * @param leftRatio   ratio(0 to 1) to leave(relative to screen) from the ending side of the swipe
   * @param rightRatio  ratio(0 to 1) to leave(relative to screen) from the ending side of the swipe
   * @param upRatio     ratio(0 to 1) to leave(relative to screen) from the ending side of the swipe
   * @param downRatio   ratio(0 to 1) to leave(relative to screen) from the ending side of the swipe
   * @param isFlexible  if true it will take all the ratios as is
   *                    otherwise it will take middle position to swipe the screen
   * @return list of attribute values in upper case
   */
  public static List<String> scrollIntoListToCollectAttributeValue(String locator,
                                                                   WebElement destination,
                                                                   String attribute,
                                                                   SwipeDirections direction,
                                                                   int duration, double leftRatio,
                                                                   double rightRatio,
                                                                   double upRatio, double downRatio,
                                                                   boolean isFlexible) {
    List<String> elementTexts = new ArrayList<>();
    List<WebElement> elements;
    int index = 0;
    var previousPageSource = "";
    while (!isTheSameDom(previousPageSource)) {
      if (quickIsDisplayed(String.format(locator, index), 3)) {
        elements = getDriver().findElements(By.xpath(String.format(locator, index)));
      } else {
        break;
      }

      if (!elements.isEmpty() && WaitActions.elementIsDisplayed(elements.get(0))) {
        elementTexts.add(elements.get(0).getAttribute(attribute).toUpperCase());
        index++;
      } else if (destination != null && WaitActions.elementIsDisplayed(destination))
        break;
      else {
        swipe(direction, leftRatio, rightRatio, upRatio, downRatio, isFlexible, duration);
        previousPageSource = getDriver().getPageSource();
      }
      setImplicitWait(Duration.ofSeconds(5));
    }
    resetImplicitWait();
    return elementTexts;
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
