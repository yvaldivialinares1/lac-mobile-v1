package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.fanapp.mobile.screens.paymentmethods.CardDetailsScreen;

public class CardDetailsQuestions extends CardDetailsScreen {

  String txtAttribute = isAndroid() ? "text" : "label";

  /**
   * Verifies the displayed nickname matches with expected
   * @param nickname expected nickname string
   */
  public void isTheGivenNicknameDisplayed(String nickname) {
    getSoftAssert().assertTrue(getTxtNickname().isDisplayed(),
      "Nickname is displayed");
    if (getTxtNickname().isDisplayed()) {
      getSoftAssert().assertEquals(getTxtNickname().getAttribute(txtAttribute), nickname,
        String.format("Nickname matches %s", nickname));
    }
  }
}