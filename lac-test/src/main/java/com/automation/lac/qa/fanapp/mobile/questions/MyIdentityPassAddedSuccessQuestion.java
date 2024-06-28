package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.MyIdentityPassAddedSuccessScreen;

public class MyIdentityPassAddedSuccessQuestion extends MyIdentityPassAddedSuccessScreen {

  /**
   * This method verifies that certain UI elements are displayed on the screen.
   * Specifically, it checks:
   * <ul>
   *   <li>Whether the title label is displayed</li>
   *   <li>Whether the subtitle (or success message) label is displayed</li>
   * </ul>
   * It uses soft assertions to perform these checks, allowing all checks to be executed
   * even if some fail. After performing the checks, it calls `assertAll` to aggregate
   * and throw any assertion errors that may have occurred.
   */
  public void checkElementsOnScreen() {
    getSoftAssert()
      .assertTrue(getLblTitle().isDisplayed(), "Checking Title displayed");
    getSoftAssert()
      .assertTrue(getLblSubTitle().isDisplayed(), "Checking success message displayed");
    getSoftAssert().assertAll();
  }
}