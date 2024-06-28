package com.automation.lac.qa.staffapp.mobile.tasks.login;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;

import com.automation.lac.qa.staffapp.mobile.screens.login.LoginScreen;
import io.qameta.allure.Step;

public class LoginTask extends LoginScreen {

  /**
   * Perform Login
   *
   * @param username String
   * @param password String
   */
  @Step("Log in using username '{0}' and password '{1}'")
  public void performLogin(String username, String password) {
    if (isTheElementVisible(getFrmInputEmail(), 7)) {
      click(getFrmInputEmail(), "activate email input field");
      sendKeys(getFrmInputEmail(), username, "email");
      click(getBtnSignInNext(), "Next");
      click(getFrmInputPassword(), "activate password input field");
      sendKeys(getFrmInputPassword(), password, "password");
      click(getBtnSignIn(), "Sign in");
    } else if (isTheElementVisible(getBtnPreselectedAccountRow(), 10)) {
      click(getBtnPreselectedAccountRow(), "Preselected account");
      waitForElementToBeClickable(getBtnContinue(), 5);
      click(getBtnContinue(), "Continue");
    }
  }
}
