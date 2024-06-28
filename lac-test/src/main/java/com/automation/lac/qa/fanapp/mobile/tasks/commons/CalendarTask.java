package com.automation.lac.qa.fanapp.mobile.tasks.commons;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ACTIVATE_CALENDAR;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CALENDAR_DISMISS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONFIRM;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.HIDE_YEAR_PICKER;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.NEXT_MONTH;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PREVIOUS_MONTH;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SWITCH_YEAR_SELECTION;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.TODAY;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.MONTH;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.YEAR;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;
import static com.automation.lac.qa.utils.mobile.DeviceActions.getTextFromElement;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.WaitActions.quickIsDisplayed;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForProcessToFinish;

import com.automation.lac.qa.fanapp.mobile.screens.commons.CalendarScreen;
import com.automation.lac.qa.utils.mobile.SwipeDirections;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

@Slf4j
public class CalendarTask extends CalendarScreen {

  private final LocalDate currentDate = LocalDate.now();
  private final String currentYear = String.valueOf(currentDate.getYear());
  private final String currentMonth =
    StringUtils.capitalize(currentDate.getMonth().name().toLowerCase());

  /**
   * This function should be called after selecting date input that triggers the calendar
   * Process LocalDate object to string
   * Begins setting date process for Android and iOS
   *
   * @param dateToSelect - expected date to select as LocalDate object
   */
  public void selectDate(LocalDate dateToSelect) {
    log.info("Date to select: {}", dateToSelect);
    String todayValue = getTextFromElement(getLblToday(), "label");
    getTestContext().set(TODAY.name(), todayValue);
    log.info("Mobile format {}", todayValue);

    String day = String.valueOf(dateToSelect.getDayOfMonth());
    String month = StringUtils.capitalize(dateToSelect.getMonth().toString().toLowerCase());
    String year = String.valueOf(dateToSelect.getYear());
    if (isAndroid()) {
      selectDateAndroid(day, month, year);
    } else {
      selectDateIos(day, month, year);
    }
  }

  /**
   * Android date selection process:
   * Calculation is done to know the shortest path to expected month and proceeds accordingly
   * Once in expected month, switch to year selection view
   * Select expected year or scroll until find it and select it
   * After selecting year, month and day selection view will display automatically
   * Once in expected month, expected day is selected
   * Finally clicks on Confirm to close calendar
   *
   * @param day   Expected day to select
   * @param month Expected month to select
   * @param year  Expected year to select
   */
  private void selectDateAndroid(String day, String month, String year) {
    String monthDirection = calculateMonthDistance(month);
    click(getLblSelectDate(), ACTIVATE_CALENDAR.getValue());

    String monthViewXpath = String.format(getAndroidMonthViewXpath(), month);
    int numberOfGivenClicks = 0;
    while (!quickIsDisplayed(monthViewXpath, 1) && numberOfGivenClicks <= 7) {
      if (monthDirection.equals(NEXT_MONTH.getValue())) {
        click(getBtnNextMonth(), NEXT_MONTH.getValue());
      } else if (monthDirection.equals(PREVIOUS_MONTH.getValue())) {
        click(getBtnPreviousMonth(), PREVIOUS_MONTH.getValue());
      }
      waitForProcessToFinish(1);
      numberOfGivenClicks++;
    }

    click(getBtnSwitchToYearSelection(), SWITCH_YEAR_SELECTION.getValue());
    waitForElementVisibility(getBtnSwitchToDaySelection(), 3);
    String yearXpath = String.format(getAndroidYearXpath(), year);
    swipeUntilFindElement(yearXpath, SwipeDirections.UP_TO_DOWN, getScrollableCalendar());
    click(getElement(By.xpath(yearXpath)), year);

    waitForElementVisibility(getBtnSwitchToYearSelection(), 3);
    String monthYear = String.format("%s %s", month, year);
    monthViewXpath = String.format(getAndroidMonthViewXpath(), monthYear);
    waitForElementVisibility(getElement(By.xpath(monthViewXpath)), 3);

    selectDay(day, month);

    click(getBtnCloseCalendar(), CONFIRM.getValue());
  }

  /**
   * iOS date selection process:
   * Switch to month and year selection view
   * Send expected year keys to year wheel
   * Send expected month keys to month wheel
   * After selecting month and year, go back to day selection view
   * Looks for expected day and selects it
   * Finally clicks on Calendar Dismiss Region to close calendar
   *
   * @param day   Expected day to select
   * @param month Expected month to select
   * @param year  Expected year to select
   */
  private void selectDateIos(String day, String month, String year) {
    click(getBtnSwitchToYearSelection(), SWITCH_YEAR_SELECTION.getValue());

    String yearWheel = String.format(getIosWheelXpath(), currentYear);
    sendKeys(getElement(By.xpath(yearWheel)), year, YEAR.getValue());

    String monthWheel = String.format(getIosWheelXpath(), currentMonth);
    sendKeys(getElement(By.xpath(monthWheel)), month, MONTH.getValue());

    click(getBtnSwitchToDaySelection(), HIDE_YEAR_PICKER.getValue());

    selectDay(day, month);

    click(getBtnCloseCalendar(), CALENDAR_DISMISS.getValue());
  }

  private void selectDay(String day, String month) {
    String platformXpath = isAndroid() ? getAndroidDayXpath() : getIosDayXpath();
    String expectedFormat = getCalendarDayFormat();
    String dayToFind = "";

    if (expectedFormat.equals("monthFirst")) {
      dayToFind = month + " " + day;
    } else if (expectedFormat.equals("dayFirst")) {
      dayToFind = day + " " + month;
    }

    String dayXpath = String.format(platformXpath, dayToFind);
    log.info("Day to locate {}", dayXpath);
    waitForElementVisibility(getElement(By.xpath(dayXpath)), 5);
    click(getElement(By.xpath(dayXpath)), dayToFind);
  }

  /**
   * Decides which is the shortest path to expected month based on actual month
   *
   * @param expectedMonth Month to select in calendar
   * @return String to determine whether to click on Next or Previous month btn
   */
  private String calculateMonthDistance(String expectedMonth) {
    List<String> months = Arrays.stream(Month.values())
      .map(month -> month.toString().toLowerCase())
      .map(StringUtils::capitalize).toList();

    if (expectedMonth.equals(currentMonth)) {
      return "Actual";
    } else {
      int startIndex = months.indexOf(currentMonth);
      int targetIndex = months.indexOf(expectedMonth);
      int counterNext = (startIndex - targetIndex + 12) % 12;
      int countPrevious = (targetIndex - startIndex + 12) % 12;

      if (counterNext > countPrevious) {
        return NEXT_MONTH.getValue();
      } else {
        return PREVIOUS_MONTH.getValue();
      }
    }
  }

  /**
   * Returns correct date format in String so day can be clicked in calendar
   *
   * @return String with correct date format
   */
  private String getCalendarDayFormat() {
    String todayDate = getTestContext().get(TODAY.name());
    int firstCommaIndex = todayDate.indexOf(',');
    int secondCommaIndex = todayDate.indexOf(',', firstCommaIndex + 1);
    String strDate = todayDate.substring(secondCommaIndex + 1).trim();
    char firstChar = strDate.charAt(0);
    if (Character.isDigit(firstChar)) {
      return "dayFirst";
    } else if (Character.isLetter(firstChar)) {
      return "monthFirst";
    } else {
      return "invalid format";
    }
  }
}