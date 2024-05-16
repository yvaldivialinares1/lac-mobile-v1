package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.fanapp.mobile.screens.home.WelcomeHomeScreen;

public class WelcomeHomeQuestion extends WelcomeHomeScreen {

  public void checkWelcomeHomeScreenIsDisplayed() {
    getSoftAssert().assertTrue(getImgIntuitDomeLogo().isDisplayed(),
      "The Welcome Home Screen is displayed");
  }
}