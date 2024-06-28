package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.assertAll;
import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;

import com.automation.lac.qa.fanapp.mobile.screens.home.HomeScreen;
import io.qameta.allure.Step;

public class HomeQuestions extends HomeScreen {

  /**
   * Verify elements displayed on Home screen
   */
  public void checkElementsOnScreen() {
    getSoftAssert().assertTrue(getBtnThemeSwitch().isDisplayed(),
      "Checking Theme switch button displayed");
    getSoftAssert().assertTrue(getBtnProfile().isDisplayed(),
      "Checking Profile button was  displayed");
    getSoftAssert().assertTrue(getBtnMenu().isDisplayed(), "Checking Menu button is displayed");
    assertAll();
  }

  /**
   * Validate if home screen is displayed
   */
  @Step("Validate Home screen is displayed")
  public void checkHomeScreenIsDisplayed() {
    getSoftAssert().assertTrue(isTheElementVisible(getBtnMenu(), 10),
      "Home Screen is displayed");
  }
}