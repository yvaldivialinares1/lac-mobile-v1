package com.automation.lac.qa.staffapp.mobile.questions.home;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementAvailable;

import com.automation.lac.qa.staffapp.mobile.screens.home.HomeScreen;

public class HomeQuestions extends HomeScreen {

  /**
   * Validate if the header logo is displayed on Welcome Home Screen
   */
  public void checkWelcomeHomeScreenIsDisplayed() {
    getSoftAssert().assertTrue(isTheElementAvailable(getHeaderLogo(), 5));
  }
}
