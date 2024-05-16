package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

public class LandingScreenQuestions {

  /**
   * checkElementTesting
   */
  public void checkElementTesting() {
    getSoftAssert().assertTrue(false, "checking soft asserts works between steps");
    //option 1 to assert all
    // SoftAssertManager.assertAll();
    // option 2 wait at the end of the test,assertAll() will be executed in the hook
  }
}
