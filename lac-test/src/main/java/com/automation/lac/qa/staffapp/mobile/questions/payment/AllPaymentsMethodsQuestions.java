package com.automation.lac.qa.staffapp.mobile.questions.payment;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.mobile.enums.SuccessMessage.SUCCESS_ADDED_PAYMENT_METHOD;

import com.automation.lac.qa.staffapp.mobile.screens.payment.AllPaymentMethodsScreen;
import io.qameta.allure.Step;

public class AllPaymentsMethodsQuestions extends AllPaymentMethodsScreen {

  /**
   * Validate all payment methods screen
   */
  @Step("Validate all payment methods")
  public void validateAllPaymentMethods(int paymentMethods) {
    getSoftAssert().assertTrue(getBtnBack().isDisplayed(), "Back option");
    getSoftAssert().assertTrue(getLblTitle().isDisplayed(), "Title payment methods");
    getSoftAssert().assertTrue(getBtnAddCard().isDisplayed(), "Add card option");
    if (paymentMethods > 0) {
      getSoftAssert().assertFalse(getLstPaymentMethods().isEmpty(), "Payment options");
    }
  }

  /**
   * Validate add payment method modal
   */
  @Step("Validate add payment method modal")
  public void validateAddPaymentModal() {
    getSoftAssert().assertTrue(getAddPaymentMethodModalComponent().getLblTitle().isDisplayed(),
      "Add credit card title visible");
    getSoftAssert().assertTrue(getAddPaymentMethodModalComponent().getBtnDiscard().isDisplayed(),
      "Discard option visible");
    getSoftAssert().assertTrue(
      getAddPaymentMethodModalComponent().getFrmInputCardNumber().isDisplayed(),
      "Card input number visible");
    getSoftAssert().assertTrue(
      getAddPaymentMethodModalComponent().getFrmInputExpiryDate().isDisplayed(),
      "Card input expiry date visible");
    getSoftAssert().assertTrue(
      getAddPaymentMethodModalComponent().getFrmInputNameOnCard().isDisplayed(),
      "Card input name displayed");
    getSoftAssert().assertFalse(getAddPaymentMethodModalComponent().getBtnAddCard().isEnabled(),
      "Add card option disabled");
  }

  public void validateAddCardEnabled() {
    getSoftAssert().assertTrue(getAddPaymentMethodModalComponent().getBtnAddCard().isEnabled(),
      "Add card option enabled");
  }

  /**
   * Validate payment method added successfully
   */
  @Step("Validate banner message")
  public void validateSuccessMessage() {
    getSoftAssert().assertTrue(getLblBannerMessage().isDisplayed(), "Banner message is visible");
    getSoftAssert().assertEquals(getLblBannerMessage().getText(),
      SUCCESS_ADDED_PAYMENT_METHOD.getMessage(), "Success message");
  }

  public void validateNewPaymentMethod() {
    //TODO under development in iPhone
    //getSoftAssert().assertFalse(getLstPaymentMethods().isEmpty(), "Payment methods not empty");
  }
}
