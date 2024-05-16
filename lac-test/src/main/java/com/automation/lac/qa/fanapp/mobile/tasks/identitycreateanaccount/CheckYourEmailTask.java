package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONTINUE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.OTP_CODE;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.hideKeyboard;
import static com.automation.lac.qa.utils.mobile.DeviceActions.performText;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.CheckYourEmailScreen;

public class CheckYourEmailTask extends CheckYourEmailScreen {

  /**
   * Manages the email OTP code input process by completing the OTP code field
   * with the provided OTP code and clicking the continue button.
   *
   * @param otpCode The OTP code to be entered.
   */
  public void manageEmailOtpCode(String otpCode) {
    completeEmailOtpCode(otpCode);
    click(getBtnContinue(), CONTINUE.getValue());
  }

  /**
   * Completes the email OTP code input process by waiting for the banner message to disappear,
   * clicking on the OTP code input field, and entering the provided OTP code.
   *
   * @param otpCode The OTP code to be entered.
   */
  public void completeEmailOtpCode(String otpCode) {
    performText(getInputNumberCode(), otpCode, OTP_CODE.getValue());
    hideKeyboard(null);
  }
}