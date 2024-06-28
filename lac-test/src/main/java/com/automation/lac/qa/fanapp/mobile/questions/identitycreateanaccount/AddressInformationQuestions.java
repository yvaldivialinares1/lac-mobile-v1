package com.automation.lac.qa.fanapp.mobile.questions.identitycreateanaccount;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.COUNTRY_UI;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.STATE_UI;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.faker.models.userinfo.AddressInfo;
import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.AddressInformationScreen;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressInformationQuestions extends AddressInformationScreen {

  /**
   * Asserts that certain fields are preloaded with values.
   * This method checks that the COUNTRY, STATE/PROVINCE/REGION, and ZIPCODE fields are not empty.
   * If the nbaAccountData flag is true, it also checks that the ADDRESS LINE 1, APP, SUITE, UNIT,
   * BUILDING, FLOOR, ETC, and CITY fields are not empty.
   *
   * @param nbaAccountData flag indicating whether additional address fields should be checked.
   * @param addressInfo    AddressInfo object containing expected values for address fields.
   */
  public void arePreloadedFields(boolean nbaAccountData, AddressInfo addressInfo) {
    waitForElementVisibility(getLblAddressInformation(), 2);

    if (isAndroid())
      waitForElementToBeClickable(getInputCountry(), 4);

    String countryText =
      isAndroid() ? getInputCountry().getAttribute("content-desc") : getInputCountry().getText();
    String stateText =
      isAndroid() ? getInputState().getAttribute("content-desc") : getInputState().getText();

    String extractedCountry = extractCountryStateName(countryText);
    String extractedState = extractCountryStateName(stateText);

    getSoftAssert().assertEquals(extractedCountry, COUNTRY_UI.getText(), "COUNTRY* is preloaded");
    getSoftAssert().assertEquals(extractedState, STATE_UI.getText(),
      "STATE/PROVINCE/REGION is preloaded");

    getSoftAssert().assertEquals(getInputZipCode().getText().trim(),
      addressInfo.getZipCode().trim(), "ZIPCODE* is preloaded");

    if (nbaAccountData) {
      getSoftAssert().assertEquals(getInputAddressLineOne().getText().trim(),
        addressInfo.getPrimaryAddress().trim(), "ADDRESS LINE 1 is preloaded");

      getSoftAssert().assertEquals(getInputAppFloor().getText().trim(),
        addressInfo.getSecondaryAddress().trim(), "APP,SUITE,UNIT,BUILDING,FLOOR,ETC is preloaded");

      getSoftAssert().assertEquals(getInputCity().getText().trim(), addressInfo.getCity().trim(),
        "CITY is preloaded");
    }
  }

  /**
   * Extracts the country/state name from the given text.
   * Handles cases where the country/state name is followed by
   * either a double space or a comma and additional instructions.
   *
   * @param text The text containing the country/state name.
   * @return The extracted country/state name.
   */
  public static String extractCountryStateName(String text) {
    if (text == null || text.isEmpty()) {
      return "";
    }
    String pattern = "^(.*?)(?:\\s{2}|,)";
    Pattern regex = Pattern.compile(pattern);
    Matcher matcher = regex.matcher(text);

    return matcher.find() ? matcher.group(1) : text;
  }
}
