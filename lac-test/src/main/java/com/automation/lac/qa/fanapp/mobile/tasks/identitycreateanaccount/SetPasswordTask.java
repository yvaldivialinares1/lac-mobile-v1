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
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.DOWN_TO_UP;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.userinfo.AccountInfo;
import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.SetPasswordScreen;
import io.qameta.allure.Step;

public class SetPasswordTask extends SetPasswordScreen {

  /**
   * Sets a new password by completing the set password and
   * confirm password fields, and then confirms the account
   * by clicking on the "Confirm My Account" button.
   */
  @Step("Complete the Password")
  public void setAPassword() {
    completeSetAPassword();
    completeConfirmAPassword();
    click(getBtnConfirmMyAccount(), CONFIRM_MY_ACCOUNT.getValue());
  }

  /**
   * Enters the new password into the 'set a password' input field.
   */
  public void completeSetAPassword() {
    AccountInfo accountInfo = ((UserInfo) getTestContext().get(USER_INFO.name())).getAccountInfo();
    click(getBtnShowSetAPassword(), SHOW_SET_PASSWORD.getValue());
    sendKeys(getInputSetAPassword(), accountInfo.getPassword(), SET_PASSWORD.getValue());
    hideKeyboard("done");
  }

  /**
   * Enters the new password into the 'confirm a password' input field.
   */
  public void completeConfirmAPassword() {
    AccountInfo accountInfo = ((UserInfo) getTestContext().get(USER_INFO.name())).getAccountInfo();
    click(getBtnShowConfirmPassword(), SHOW_CONFIRM_PASSWORD.getValue());
    sendKeys(getInputConfirmPassword(), accountInfo.getPassword(), CONFIRM_PASSWORD.getValue());
    hideKeyboard("done");
  }

  public void swipeToPasswordFields() {
    swipeUntilFindElement(getInputHiddenConfirmPassword(), DOWN_TO_UP);
  }
}