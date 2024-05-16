package com.automation.lac.qa.fanapp.mobile.tasks.home;


import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CREATE_AN_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.LOGIN;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SKIP;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.home.WelcomeHomeScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.educationalexperience.FullExperienceEducationalTask;

public class WelcomeHomeTask extends WelcomeHomeScreen {

  /**|
   * Go to Create an account flow
   */
  public FullExperienceEducationalTask createAnAccount() {
    click(getBtnCreateAnAccount(), CREATE_AN_ACCOUNT.getValue());

    return new FullExperienceEducationalTask();
  }

  /**
   * Go to log in flow from Welcome Home Screen
   */
  public void goToLogin() {
    click(getBtnLogIn(), LOGIN.getValue());
  }

  /**
   * Skip the Welcome Home Screen
   */
  public void skipWelcomeHome() {
    click(getBtnSkip(), SKIP.getValue());
  }
}