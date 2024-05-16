package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONFIRM;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONTINUE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CURRENT_SELECTION_DATE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.DATE_OF_BIRTH;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.DATE_PICKER;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.HIDE_YEAR_PICKER;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.NEXT_MONTH;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PERSONAL_INFORMATION;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.POPOVER_DISMISS_CALENDAR;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PREVIOUS_MONTH;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.COUNTRY_CODE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.DAY;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.FIRST_NAME;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.LAST_NAME;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.MONTH;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.PHONE_NUMBER;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.SEARCH;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.YEAR;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.APRIL;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.AUGUST;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.DECEMBER;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.FEBRUARY;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.JANUARY;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.JULY;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.JUNE;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.MARCH;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.MAY;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.NOVEMBER;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.OCTOBER;
import static com.automation.lac.qa.fanapp.mobile.enums.Month.SEPTEMBER;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.getElement;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.hideKeyboard;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.DeviceActions.specialIosSetText;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementAvailability;

import com.automation.lac.qa.faker.models.userinfo.PersonalInfo;
import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.PersonalInformationScreen;
import com.automation.lac.qa.utils.mobile.SwipeDirections;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

@Slf4j
public class PersonalInformationTask extends PersonalInformationScreen {

  /**
   * Complete all the Personal Information
   */
  public void completePersonalInformation(PersonalInfo personalInfo) {
    enterMandatoryFields(personalInfo);
    if (!isAndroid()) {
      hideKeyboard(null);
    }
    click(getBtnContinue(), CONTINUE.getValue());
  }

  /**
   * Enters mandatory fields for personal information
   */
  public void enterMandatoryFields(PersonalInfo personalInfo) {
    completeFullName(personalInfo.getFirstName(), personalInfo.getLastName());
    completeDateOfBirth(personalInfo);
    completeCountryCode(personalInfo.getPhoneOtpCountry());
    completePhoneNumber(personalInfo.getPhoneNumber());
  }

  /**
   * Workaround for iOS as text fields are not working as expected
   */
  public void completeFullName(String firstName, String lastName) {
    if (isAndroid()) {
      sendKeys(getInputFirstName(), firstName, FIRST_NAME.getValue());
      sendKeys(getInputLastName(), lastName, LAST_NAME.getValue());
    } else {
      specialIosSetText(getInputFirstName(), firstName, FIRST_NAME.getValue());
      specialIosSetText(getInputLastName(), lastName, LAST_NAME.getValue());
    }
  }

  /**
   * Completes the date of birth field with the provided birthdate
   * Hides the calendar and dismisses the keyboard after selecting the date
   * On Android platform, clicks on the Confirm button; on iOS platform,
   * clicks on the Hide Calendar button
   */
  public void completeDateOfBirth(PersonalInfo personalInfo) {
    log.info("Date of Birth: {}", personalInfo.getBirthDate());
    String month = personalInfo.getBirthDate().getMonth().toString();
    String day = String.valueOf(personalInfo.getBirthDate().getDayOfMonth());
    String year = String.valueOf(personalInfo.getBirthDate().getYear());
    selectDateOfBirth(month, day, year);

    if (isAndroid()) {
      click(getBtnConfirm(), CONFIRM.getValue());
    } else {
      click(getBtnPopoverDismissCalendar(), POPOVER_DISMISS_CALENDAR.getValue());
    }
    hideKeyboard(null);
  }

  /**
   * Completes the country code dropdown with the provided country code
   */
  public void completeCountryCode(String countryCode) {
    click(getInputCountryCode(), COUNTRY_CODE.getValue());
    waitForElementAvailability(getInputSearch(), 10);
    click(getInputSearch(), SEARCH.getValue());
    String formattedCountry = StringUtils.capitalize(countryCode);
    sendKeys(getInputSearch(), formattedCountry, SEARCH.getValue());
    click(listElementToSelect(formattedCountry), formattedCountry);
  }

  /**
   * Completes the phone number field with the provided phone number
   * For iOS platform, clicks on the Personal Information label to dismiss keyboard
   */
  public void completePhoneNumber(String phoneNumber) {
    if (isAndroid()) {
      sendKeys(getInputPhoneNumber(), phoneNumber, PHONE_NUMBER.getValue());
    } else {
      specialIosSetText(getInputPhoneNumber(), phoneNumber, PHONE_NUMBER.getValue());
      click(getLblPersonalInformation(), PERSONAL_INFORMATION.getValue());
    }
  }

  /**
   * Selects the date of birth with the provided month, day, and year
   */
  public void selectDateOfBirth(String month, String day, String year) {
    click(getInputDateOfBirth(), DATE_OF_BIRTH.getValue());
    month = StringUtils.capitalize(month.toLowerCase());
    if (isAndroid()) {
      setDateOfBirthAndroid(month, day, year);
    } else {
      setDateOfBirthIos(month, day, year);
    }
  }

  /**
   * Sets the date of birth on an Android device by selecting the month, day, and year.
   *
   * @param month The month to set for the date of birth.
   * @param day   The day to set for the date of birth.
   * @param year  The year to set for the date of birth.
   */
  public void setDateOfBirthAndroid(String month, String day, String year) {
    int i = 0;
    String monthToSelect = calculateMonthDistance(month);
    while (getTxtCurrentSelectionDate().isDisplayed()
      && !getTxtCurrentSelectionDate().getText().contains(month) && i < 12) {
      if (monthToSelect.equals("Next")) {
        click(getBtnNextMonth(), NEXT_MONTH.getValue());
      } else if (monthToSelect.equals("Previous")) {
        click(getBtnPreviousMonth(), PREVIOUS_MONTH.getValue());
      }
      i++;
    }
    click(getDatePickerYear(), DATE_PICKER.getValue());
    String yearXpath = String.format(YEAR_XPATH_ANDROID, year);
    swipeUntilFindElement(yearXpath, SwipeDirections.UP_TO_DOWN, getNavigateToYearArea());
    click(getDriver().findElement(By.xpath(yearXpath)), year);

    String dayToSearch = month.concat(" ").concat(day).concat(", ").concat(year);
    String dayXpathToSearch = String.format(DAY_XPATH_ANDROID, dayToSearch);
    click(getElement(By.xpath(dayXpathToSearch)), dayToSearch);
  }

  /**
   * Set date of birth for iOS
   *
   * @param month month of birth
   * @param day   day of birth
   * @param year  year of birth
   */
  public void setDateOfBirthIos(String month, String day, String year) {
    String defaultDate = getTxtCurrentSelectionDate().getAttribute("value");
    click(getTxtCurrentSelectionDate(), CURRENT_SELECTION_DATE.getValue());
    sendKeys(getYearIos(defaultDate), year, YEAR.getValue());
    sendKeys(getMonthIos(defaultDate), month, MONTH.getValue());
    click(getHideYearPicker(), HIDE_YEAR_PICKER.getValue());
    click(getDayIos(String.format("%s %s", month, day)), DAY.getValue());
  }

  /**
   * Decides which is the shortest path to expected month based on actual month
   *
   * @param month String
   * @return path
   */
  private String calculateMonthDistance(String month) {
    LocalDate currentDate = LocalDate.now();
    List<String> months = List.of(
      JANUARY.getDescription(), FEBRUARY.getDescription(), MARCH.getDescription(),
      APRIL.getDescription(), MAY.getDescription(), JUNE.getDescription(),
      JULY.getDescription(), AUGUST.getDescription(), SEPTEMBER.getDescription(),
      OCTOBER.getDescription(), NOVEMBER.getDescription(), DECEMBER.getDescription()
    );

    String currentMonth = StringUtils.capitalize(currentDate.getMonth().name().toLowerCase());

    if (month.equals(currentMonth)) {
      return "Actual";
    } else {
      int startIndex = months.indexOf(currentMonth);
      int targetIndex = months.indexOf(month);
      int counterNext = (startIndex - targetIndex + 12) % 12;
      int countPrevious = (targetIndex - startIndex + 12) % 12;

      if (counterNext > countPrevious) {
        return "Next";
      } else {
        return "Previous";
      }
    }
  }
}