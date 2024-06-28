package com.automation.lac.qa.staffapp.mobile.questions.payment;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.staffapp.mobile.screens.payment.CardDetailsScreen;
import io.qameta.allure.Step;

public class CardDetailsQuestions extends CardDetailsScreen {

  /**
   * Validate card info
   *
   * @param isPreferredCard true if is the preferred card
   */
  @Step("Validate the card info if isPreferredCard is {isPreferredCard}")
  public void validateCard(boolean isPreferredCard) {
    getSoftAssert().assertTrue(getBtnBack().isDisplayed(), "Button back is visible");
    getSoftAssert().assertTrue(getImgCard().isDisplayed(), "Image card is visible");
    getSoftAssert().assertTrue(getLblCardNumber().isDisplayed(), "Card number is visible");
    getSoftAssert().assertTrue(getLblExpiryDate().isDisplayed(), "Expiry date is visible");
    if (isPreferredCard) {
      getSoftAssert().assertFalse(getCheckboxPreferredCard().isEnabled(),
        "Preferred checkbox is disabled");
    } else {
      getSoftAssert().assertTrue(getCheckboxPreferredCard().isEnabled(),
        "Preferred checkbox is enabled");
    }
    getSoftAssert().assertTrue(getBtnDeletePaymentMethod().isDisplayed(),
      "Delete payment is visible");
  }

  /**
   * Validate preferred card checkbox empty
   *
   * @param isEmpty if true, the card is not the preferred payment method
   */
  @Step("Validate if the preferred card checkbox is marked")
  public void isPreferredCheckBoxEmpty(boolean isEmpty) {
    getSoftAssert().assertEquals(getCheckboxPreferredCard().getAttribute("label"),
      isEmpty ? "unselected" : "checkbox", "Card is not the preferred payment method");
  }
}
