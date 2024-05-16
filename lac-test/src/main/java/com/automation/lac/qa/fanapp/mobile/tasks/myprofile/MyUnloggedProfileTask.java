package com.automation.lac.qa.fanapp.mobile.tasks.myprofile;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.LOG_IN_CREATE_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_PROFILE;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.home.HomeScreen;
import com.automation.lac.qa.fanapp.mobile.screens.myprofile.MyUnloggedProfileScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.home.WelcomeHomeTask;

public class MyUnloggedProfileTask extends MyUnloggedProfileScreen {
  private final HomeScreen homeScreen = new HomeScreen();
  private final WelcomeHomeTask welcomeHomeTask = new WelcomeHomeTask();

  /**
   * Navigates from the Welcome home screen to the login screen.
   * It performs clicks on the profile button and the "LOG IN/CREATE ACCOUNT" button,
   * then proceeds to the login screen through the welcomeHomeTask.
   */
  public void goToLoginFromWelcomeHomeScreen() {
    click(homeScreen.getBtnProfile(), MY_PROFILE.getValue());
    click(getBtnLoginCreateAccount(), LOG_IN_CREATE_ACCOUNT.getValue());
    welcomeHomeTask.goToLogin();
  }

}