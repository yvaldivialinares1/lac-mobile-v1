package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.fanapp.mobile.screens.home.HomeScreen;

public class HomeQuestions extends HomeScreen {

  /**
   * Verify elements displayed on Home screen
   */
  public void checkElementsOnScreen() {
    getSoftAssert()
      .assertTrue(getBtnThemeSwitch().isDisplayed(),
        "Checking Theme switch button displayed");
    getSoftAssert()
      .assertTrue(getBtnMenu().isDisplayed(), "Checking Menu button is displayed");
    getSoftAssert()
      .assertTrue(getBtnProfile().isDisplayed(), "Checking Profile button was  displayed");
    getSoftAssert().assertAll();
  }

  public void checkHomeScreenIsDisplayed() {
    getSoftAssert().assertTrue(getBtnMenu().isDisplayed(),
      "The Home Screen is displayed");
  }
}