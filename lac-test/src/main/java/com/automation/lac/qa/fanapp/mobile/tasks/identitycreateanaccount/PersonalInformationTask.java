package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONTINUE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.DATE_OF_BIRTH;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PERSONAL_INFORMATION;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.COUNTRY_CODE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.FIRST_NAME;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.LAST_NAME;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.PHONE_NUMBER;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.SEARCH;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.getTextFromElement;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.DeviceActions.specialIosSetText;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitElementWillBeAvailable;

import com.automation.lac.qa.faker.models.userinfo.PersonalInfo;
import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.PersonalInformationScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.commons.CalendarTask;
import io.qameta.allure.Step;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class PersonalInformationTask extends PersonalInformationScreen {

  private final CalendarTask calendarTask = new CalendarTask();

  /**
   * Complete all the Personal Information
   *
   * @param personalInfo user personal info
   */
  @Step("Complete the personal information")
  public void completePersonalInformation(PersonalInfo personalInfo) {
    enterMandatoryFields(personalInfo);
    click(getBtnContinue(), CONTINUE.getValue());
  }


  /**
   * Enters mandatory fields for personal information
   */
  public void enterMandatoryFields(PersonalInfo personalInfo) {
    completeFullName(personalInfo.getFirstName(), personalInfo.getLastName());
    completeDateOfBirth(personalInfo.getBirthDate());
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
      specialIosSetText(getInputFirstName(), firstName, FIRST_NAME.getValue(), false);
      specialIosSetText(getInputLastName(), lastName, LAST_NAME.getValue(), false);
    }
  }

  /**
   * Completes the date of birth field with the provided birthdate
   * Hides the calendar and dismisses the keyboard after selecting the date
   * On Android platform, clicks on the Confirm button; on iOS platform,
   * clicks on the Hide Calendar button
   */
  @Step("Select the date of birth {birthDate}")
  public void completeDateOfBirth(LocalDate birthDate) {
    String currentSelectedDate = getTextFromElement(getInputDateOfBirth(), "value");

    while (currentSelectedDate.isEmpty() || currentSelectedDate.contains("2024")) {
      click(getInputDateOfBirth(), DATE_OF_BIRTH.getValue());
      calendarTask.selectDate(birthDate);
      currentSelectedDate = getTextFromElement(getInputDateOfBirth(), "value");
    }
  }

  /**
   * Completes the country code dropdown with the provided country code
   */
  public void completeCountryCode(String countryCode) {
    click(getInputCountryCode(), COUNTRY_CODE.getValue());
    waitElementWillBeAvailable(getInputSearch(), 10);
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
      specialIosSetText(getInputPhoneNumber(), phoneNumber, PHONE_NUMBER.getValue(), true);
      click(getLblPersonalInformation(), PERSONAL_INFORMATION.getValue());
    }
  }

  public void clickContinue() {
    click(getBtnContinue(), CONTINUE.getValue());
  }

  /**
   * Manages the personal information section during the NBA account registration process.
   * This method fills in the phone number using the provided personal information
   * and clicks the "Continue" button to proceed to the address information step.
   *
   * @param personalInfo PersonalInfo object containing the user's personal details,
   *                     including the phone number to be completed in the form.
   */
  public void managePersonalInformationNbaAccount(PersonalInfo personalInfo) {
    completePhoneNumber(personalInfo.getPhoneNumber());
    clickContinue();
  }
}