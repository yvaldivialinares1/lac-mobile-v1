package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.fanapp.mobile.screens.login.LoginScreen;

public class LoginScreenQuestions extends LoginScreen {

  /**
   * Question to check elements on screen
   * option 1 to assert all
   * SoftAssertManager.assertAll();
   * option 2 wait at the end of the test,assertAll() will be executed in the hook
   */
  public void checkElementsOnScreen() {
    getSoftAssert()
      .assertTrue(getInputEmailAddress().isDisplayed(), "Checking email field displayed");
    getSoftAssert()
      .assertTrue(getInputPassword().isDisplayed(), "Checking password field is displayed");
    getSoftAssert()
      .assertTrue(getBtnSignIn().isDisplayed(), "Checking sigIn button was  displayed");
    getSoftAssert().assertAll();
  }
}