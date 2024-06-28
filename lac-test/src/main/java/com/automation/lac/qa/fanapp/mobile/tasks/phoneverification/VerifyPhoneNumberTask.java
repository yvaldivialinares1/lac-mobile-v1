package com.automation.lac.qa.fanapp.mobile.tasks.phoneverification;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.VERIFY_MY_NUMBER;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.COUNTRY;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.PHONE_NUMBER;
import static com.automation.lac.qa.utils.mobile.DeviceActions.clear;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.fanapp.mobile.screens.phonenumbervalidation.VerifyPhoneNumberScreen;
import io.qameta.allure.Step;

public class VerifyPhoneNumberTask extends VerifyPhoneNumberScreen {

  public boolean isVerifyPhoneNumberScreenDisplayed() {
    return isTheElementVisible(getLblVerifyYourPhone(), 10);
  }

  /**
   * Set country, phone number and clicks on validate button
   * @param userInfo user info to validate
   */
  public void validatePhoneNumber(UserInfo userInfo) {
    setCountry(userInfo.getPersonalInfo().getPhoneOtpCountry());
    setPhoneNumber(userInfo.getPersonalInfo().getPhoneNumber());
    clickVerifyNumber();
  }

  private void setCountry(String country) {
    click(getDdlCountry(), COUNTRY.getValue());
    getSelectCountryWidget().selectCountry(country);
  }

  private void setPhoneNumber(String phoneNumber) {
    clear(getInputPhoneNumber());
    sendKeys(getInputPhoneNumber(), phoneNumber, PHONE_NUMBER.getValue());
  }

  public void clickVerifyNumber() {
    click(getBtnVerifyMyNumber(), VERIFY_MY_NUMBER.getValue());
  }

  /**
   * Verify if screen is displayed and confirm phone number
   */
  @Step("Handle phone number verification after completing onboarding")
  public void handlePhoneNumberVerificationAfterOnboarding() {
    if (isVerifyPhoneNumberScreenDisplayed()) {
      clickVerifyNumber();
    }
  }
}