package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONFIRM_MY_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONFIRM_PASSWORD;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SET_PASSWORD;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SHOW_CONFIRM_PASSWORD;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SHOW_SET_PASSWORD;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.hideKeyboard;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.userinfo.AccountInfo;
import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.SetPasswordScreen;

public class SetPasswordTask extends SetPasswordScreen {

  private final UserInfo userInfo = getTestContext().get(USER_INFO.name());

  /**
   * Sets a new password by completing the set password and
   * confirm password fields, and then confirms the account
   * by clicking on the "Confirm My Account" button.
   */
  public void setAPassword() {
    completeConfirmAPassword();
    completeSetAPassword();
    click(getBtnConfirmMyAccount(), CONFIRM_MY_ACCOUNT.getValue());
  }

  /**
   * Enters the new password into the 'set a password' input field.
   */
  public void completeSetAPassword() {
    AccountInfo accountInfo = userInfo.getAccountInfo();
    click(getBtnShowSetAPassword(), SHOW_SET_PASSWORD.getValue());
    sendKeys(getInputSetAPassword(), accountInfo.getPassword(), SET_PASSWORD.getValue());
    hideKeyboard("done");
  }

  /**
   * Enters the new password into the 'confirm a password' input field.
   */
  public void completeConfirmAPassword() {
    AccountInfo accountInfo = userInfo.getAccountInfo();
    click(getBtnShowConfirmPassword(), SHOW_CONFIRM_PASSWORD.getValue());
    sendKeys(getInputConfirmPassword(), accountInfo.getConfirmPassword(),
      CONFIRM_PASSWORD.getValue());
    hideKeyboard("done");
  }
}