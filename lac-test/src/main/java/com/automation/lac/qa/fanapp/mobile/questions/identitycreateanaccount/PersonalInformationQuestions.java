package com.automation.lac.qa.fanapp.mobile.questions.identitycreateanaccount;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.PLUS;
import static com.automation.lac.qa.utils.DateTimeUtils.formatLocalDate;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.userinfo.PersonalInfo;
import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.PersonalInformationScreen;


public class PersonalInformationQuestions extends PersonalInformationScreen {

  /**
   * Asserts that specific fields are preloaded with values.
   * This method verifies that the FIRST NAME, LAST NAME, and DATE OF BIRTH fields are preloaded
   * and match the expected values from the provided UserInfo.
   * Additionally, it checks that the COUNTRY CODE field's content description matches the expected
   * country code.
   * If the nbaAccountData flag is true, it also verifies that the PHONE NUMBER and PREFERRED NAME
   * fields are preloaded and match the expected values.
   *
   * @param nbaAccountData flag indicating whether the phone number and preferred name fields
   *                       should be checked.
   * @param userInfo       UserInfo object containing the expected preloaded values.
   */
  public void arePreloadedFields(boolean nbaAccountData, UserInfo userInfo) {
    PersonalInfo personalInfo = userInfo.getPersonalInfo();

    getSoftAssert().assertEquals(getInputFirstName().getText().trim(),
      personalInfo.getFirstName().trim(), "FIRST NAME* is preloaded");

    getSoftAssert().assertEquals(getInputLastName().getText().trim(),
      personalInfo.getLastName().trim(), "LAST NAME* is preloaded");

    String expectedDateFormat = isAndroid() ? "MM/dd/yyyy" : "d MMM yyyy";
    getSoftAssert().assertEquals(getInputDateOfBirth().getText(),
      formatLocalDate(personalInfo.getBirthDate(), expectedDateFormat),
      "DATE OF BIRTH* is preloaded");

    String actualCountryCode =
      isAndroid() ? extractCountryCode(getInputCountryCode().getAttribute("content-desc")) :
        extractCountryCode(getInputCountryCode().getText());
    String bugMessage = isAndroid() ? "" : "'iOS bug: CA-60294'";

    String expectedCountryCode =
      String.format("%s%s", PLUS.getText(), userInfo.getPhoneInfo().getPhoneOtpCountryCode());
    getSoftAssert().assertEquals(actualCountryCode, expectedCountryCode,
      String.format("COUNTRY CODE* is preloaded %s", bugMessage));

    if (nbaAccountData) {
      getSoftAssert().assertEquals(getInputPreferredName().getText().trim(),
        personalInfo.getLastName().trim(), "PREFERRED NAME is preloaded");
      getSoftAssert().assertEquals(getInputPhoneNumber().getText().replaceAll("\\s+", ""),
        personalInfo.getPhoneNumber().replaceAll("\\s+", ""), "PHONE NUMBER* is preloaded");
    }
  }

  /**
   * Extracts the country code from the given text.
   * Assumes the country code starts with a '+' and is followed by either a space or a slash
   * and additional text.
   *
   * @param text The text containing the country code.
   * @return The extracted country code.
   */
  public static String extractCountryCode(String text) {
    if (text == null || text.isEmpty()) {
      return "";
    }
    int plusIndex = text.indexOf('+');
    if (plusIndex == -1) {
      return "";
    }

    StringBuilder countryCode = new StringBuilder();
    countryCode.append('+');
    for (int i = plusIndex + 1; i < text.length(); i++) {
      char c = text.charAt(i);
      if (Character.isDigit(c)) {
        countryCode.append(c);
      } else {
        break;
      }
    }
    return countryCode.toString();
  }
}
