package com.automation.lac.qa.staffapp.mobile.tasks.welcome;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.staffapp.mobile.screens.welcome.WelcomeScreen;
import com.automation.lac.qa.staffapp.mobile.screens.welcome.components.WelcomePopupScreen;

public class WelcomeTask extends WelcomeScreen {

  protected WelcomePopupScreen welcomePopupScreen = new WelcomePopupScreen();

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
    if (waitForElementVisibility(welcomePopupScreen.getBtnAllow(), 5)) {
      click(welcomePopupScreen.getBtnAllow(), "Allow");
    }
    return this;
  }

  /**
   * tap 'Continue' button.
   */
  public void clickContinueButton() {
    click(welcomePopupScreen.getBtnContinue(), "Continue");
  }
}
