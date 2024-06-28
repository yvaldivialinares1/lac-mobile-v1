package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONFIRM_MY_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONTINUE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.ADDRESS;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.APP_FLOOR;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.CITY;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.COUNTRY;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.SEARCH;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.STATE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.US;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.ZIP_CODE;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.DeviceActions.specialIosSetText;

import com.automation.lac.qa.faker.models.userinfo.AddressInfo;
import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.AddressInformationScreen;
import io.qameta.allure.Step;

public class AddressInformationTask extends AddressInformationScreen {

  /**
   * Manages the address information by completing the address details
   * and clicking the continue button.
   */
  @Step("Complete the address information")
  public void manageAddressInformation(AddressInfo addressInfo) {
    completeAddressInformation(addressInfo);
    click(getBtnContinue(), CONTINUE.getValue());
  }

  /**
   * Completes the address information using the provided PersonalInfo object
   */
  public void completeAddressInformation(AddressInfo addressInfo) {
    selectCountry(US.getValue());
    selectState(addressInfo.getState());
    if (isAndroid()) {
      sendKeys(getInputAddressLineOne(), addressInfo.getPrimaryAddress(), ADDRESS.getValue());
      sendKeys(getInputAppFloor(), addressInfo.getSecondaryAddress(), APP_FLOOR.getValue());
      sendKeys(getInputCity(), addressInfo.getCity(), CITY.getValue());
      sendKeys(getInputZipCode(), addressInfo.getZipCode(), ZIP_CODE.getValue());
    } else {
      specialIosSetText(getInputAddressLineOne(), addressInfo.getPrimaryAddress(),
        ADDRESS.getValue(), false);
      specialIosSetText(getInputAppFloor(), addressInfo.getSecondaryAddress(),
        APP_FLOOR.getValue(), false);
      specialIosSetText(getInputCity(), addressInfo.getCity(), CITY.getValue(), false);
      specialIosSetText(getInputZipCode(), addressInfo.getZipCode(), ZIP_CODE.getValue(), false);
    }
  }

  /**
   * Select the country from dropdown
   *
   * @param country countryName
   */
  public void selectCountry(String country) {
    click(getInputCountry(), COUNTRY.getValue());
    sendKeys(getInputSearch(), country, SEARCH.getValue());
    if (isAndroid()) {
      click(selectOption(country), country);
    } else {
      click(getLabelCountryList().stream().findFirst().orElseThrow(), country);
    }
  }

  private void selectState(String state) {
    click(getInputState(), STATE.getValue());
    sendKeys(getInputSearch(), state, SEARCH.getValue());
    if (isAndroid()) {
      click(selectOption(state), state);
    } else {
      click(getLabelStatesList().stream().findFirst().orElseThrow(), state);
    }
  }

  /**
   * Provize zip code value from address info object
   *
   * @param addressInfo address info
   */
  public void enterZipCode(AddressInfo addressInfo) {
    if (isAndroid()) {
      sendKeys(getInputZipCode(), addressInfo.getZipCode(), ZIP_CODE.getValue());
    } else {
      specialIosSetText(getInputZipCode(), addressInfo.getZipCode(), ZIP_CODE.getValue(), false);
    }
  }

  /**
   * This button exists in AddressInformationScreen only for the creation of an account with NBA
   * credentials.
   */
  public void clickConfirmMyAccount() {
    click(getBtnConfirmMyAccount(), CONFIRM_MY_ACCOUNT.getValue());
  }

  /**
   * Manages the address information section during the NBA account registration process.
   * This method fills in the address details using the provided address information and
   * clicks the "Confirm My Account" button to complete the registration.
   * It handles platform-specific input methods for Android and iOS.
   *
   * @param addressInfo AddressInfo object containing the user's address details,
   *                    including primary address, secondary address, and city
   *                    to be completed in the form.
   */
  public void manageAddressInformationNbaAccount(AddressInfo addressInfo) {
    if (isAndroid()) {
      sendKeys(getInputAddressLineOne(), addressInfo.getPrimaryAddress(), ADDRESS.getValue());
      sendKeys(getInputAppFloor(), addressInfo.getSecondaryAddress(), APP_FLOOR.getValue());
      sendKeys(getInputCity(), addressInfo.getCity(), CITY.getValue());
    } else {
      specialIosSetText(getInputAddressLineOne(), addressInfo.getPrimaryAddress(),
        ADDRESS.getValue(), false);
      specialIosSetText(getInputAppFloor(), addressInfo.getSecondaryAddress(),
        APP_FLOOR.getValue(), false);
      specialIosSetText(getInputCity(), addressInfo.getCity(), CITY.getValue(), false);
    }
    clickConfirmMyAccount();
  }
}