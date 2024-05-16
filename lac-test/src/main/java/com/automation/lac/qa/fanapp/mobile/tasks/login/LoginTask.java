package com.automation.lac.qa.fanapp.mobile.tasks.login;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.LOG_IN_WITH_ANOTHER_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SING_IN;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.PASSWORD;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.USER_NAME;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.WaitActions.quickIsDisplayed;

import com.automation.lac.qa.fanapp.mobile.screens.login.LoginScreen;

public class LoginTask extends LoginScreen {

  /**
   * Perform Login
   *
   * @param username String
   * @param password String
   */
  public void performLogin(String username, String password) {
    if (quickIsDisplayed(getBtnLogInWithAnotherAccount())) {
      click(getBtnLogInWithAnotherAccount(), LOG_IN_WITH_ANOTHER_ACCOUNT.getValue());
    }
    fillOutLoginForm(username, password);
    click(getBtnSignIn(), SING_IN.getValue());
  }

  /**
   * Fills out the login form with the provided username and password.
   *
   * @param username the username to be entered in the login form.
   * @param password the password to be entered in the login form.
   */
  public void fillOutLoginForm(String username, String password) {
    sendKeys(getInputEmailAddress(), username, USER_NAME.getValue());
    sendKeys(getInputPassword(), password, PASSWORD.getValue());
  }
}