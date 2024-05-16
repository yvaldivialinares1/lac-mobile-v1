package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount;

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

public class AddressInformationTask extends AddressInformationScreen {

  /**
   * Manages the address information by completing the address details
   * and clicking the continue button.
   */
  public void manageAddressInformation(AddressInfo addressInfo) {
    completeAddressInformation(addressInfo);
    click(getBtnContinue(), CONTINUE.getValue());
  }

  /**
   * Completes the address information using the provided PersonalInfo object
   */
  public void completeAddressInformation(AddressInfo addressInfo) {
    if (isAndroid()) {
      sendKeys(getInputAddressLineOne(), addressInfo.getPrimaryAddress(), ADDRESS.getValue());
      sendKeys(getInputAppFloor(), addressInfo.getSecondaryAddress(), APP_FLOOR.getValue());
    } else {
      specialIosSetText(getInputAddressLineOne(), addressInfo.getPrimaryAddress(),
        ADDRESS.getValue());
      specialIosSetText(getInputAppFloor(), addressInfo.getSecondaryAddress(),
        APP_FLOOR.getValue());
    }
    selectCountry(US.getValue());
    selectState(addressInfo.getState());
    sendKeys(getInputCity(), addressInfo.getCity(), CITY.getValue());
    sendKeys(getInputZipCode(), addressInfo.getZipCode(), ZIP_CODE.getValue());
  }

  private void selectCountry(String country) {
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
}