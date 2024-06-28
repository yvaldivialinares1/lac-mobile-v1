package com.automation.lac.qa.staffapp.mobile.questions.payment;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.staffapp.mobile.screens.payment.PaymentsAndSharedPaymentsScreen;
import io.qameta.allure.Step;

public class PaymentsAndSharedPaymentsQuestions extends PaymentsAndSharedPaymentsScreen {

  /**
   * Validate payments screen
   */
  @Step("Validate payments screen")
  public void validatePaymentsScreen() {
    getSoftAssert().assertTrue(getBtnBack().isDisplayed(), "Back button");
    getSoftAssert().assertTrue(getLblTitle().isDisplayed(), "Payments title");
    getSoftAssert().assertFalse(getLstPaymentButtons().isEmpty(), "List of payment options");
  }
}
