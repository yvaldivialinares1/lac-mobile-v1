package com.automation.lac.qa.fanapp.mobile.tasks.login;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.LOG_IN_WITH_ANOTHER_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SING_IN;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.PASSWORD;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.USER_NAME;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;

import com.automation.lac.qa.fanapp.mobile.screens.login.LoginScreen;
import com.automation.lac.qa.utils.mobile.WaitActions;
import io.qameta.allure.Step;

public class LoginTask extends LoginScreen {

  /**
   * Perform Login
   *
   * @param username String
   * @param password String
   */
  public void performLogin(String username, String password) {
    if (WaitActions.elementIsDisplayed(getBtnLogInWithAnotherAccount())) {
      click(getBtnLogInWithAnotherAccount(), LOG_IN_WITH_ANOTHER_ACCOUNT.getValue());
    }
    fillOutLoginForm(username, password);
    click(getBtnSignIn(), SING_IN.getValue());
  }

  /**
   * Perform Login using only the password.
   * This method is used when the username is not required for the login process.
   *
   * @param password The password to enter, must not be null or empty.
   */
  @Step("Perform login using only the {password}")
  public void performLogin(String password) {
    sendKeys(getInputPassword(), password, PASSWORD.getValue());
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