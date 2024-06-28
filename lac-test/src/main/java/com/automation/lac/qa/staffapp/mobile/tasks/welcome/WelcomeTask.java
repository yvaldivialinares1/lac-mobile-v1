package com.automation.lac.qa.staffapp.mobile.tasks.welcome;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitUntilAlertIsVisible;

import com.automation.lac.qa.staffapp.mobile.screens.welcome.WelcomeScreen;

public class WelcomeTask extends WelcomeScreen {

  /**
   * tap 'login' button.
   */
  public WelcomeTask clickLoginButton() {
    click(getBtnLogIn(), "Login");
    return this;
  }

  /**
   * tap 'Allow' button if displayed.
   */
  public WelcomeTask clickAllowButtonIfDisplayed() {
    waitUntilAlertIsVisible(5).accept();
    return this;
  }

  /**
   * tap 'Continue' button.
   */
  public void clickContinueButton() {
    waitUntilAlertIsVisible(5).accept();
  }
}
