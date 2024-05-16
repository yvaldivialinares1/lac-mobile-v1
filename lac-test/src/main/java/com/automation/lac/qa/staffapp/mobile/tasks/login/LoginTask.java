package com.automation.lac.qa.staffapp.mobile.tasks.login;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.staffapp.mobile.screens.login.LoginScreen;

public class LoginTask extends LoginScreen {

  /**
   * Perform Login
   *
   * @param username String
   * @param password String
   */
  public void performLogin(String username, String password) {
    if (waitForElementVisibility(getFrmInputEmail(), 5)) {
      click(getFrmInputEmail(), "activate email input field");
      sendKeys(getFrmInputEmail(), username, "email");
      click(getBtnSignInNext(), "Next");
      click(getFrmInputPassword(), "activate password input field");
      sendKeys(getFrmInputPassword(), password, "password");
      click(getBtnSignIn(), "Sign in");
    } else if (waitForElementVisibility(getBtnPreselectedAccountRow(), 10)) {
      click(getBtnPreselectedAccountRow(), "Preselected account");
      click(waitForElementToBeClickable(3, getBtnContinue()), "Continue");
    }
  }
}
