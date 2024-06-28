package com.automation.lac.qa.staffapp.mobile.questions.fanprofile;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.mobile.enums.SuccessMessage.SUCCESS_ADDED_PAYMENT_METHOD;

import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.FanProfilePaymentsScreen;
import io.qameta.allure.Step;

public class FanProfilePaymentsQuestions extends FanProfilePaymentsScreen {

  /**
   * Validate payments screen
   */
  @Step("Validate payment screen info payment methods {paymentMethods}")
  public void validatePaymentsScreen(int paymentMethods) {
    getSoftAssert().assertTrue(getLblTitle().isDisplayed(), "Payments title is visible");
    if (paymentMethods > 0) {
      getSoftAssert().assertTrue(getBtnViewAll().isDisplayed(), "View all button is visible");
      getSoftAssert().assertFalse(getLstPaymentMethods().isEmpty(), "Payment methods not empty");
    }
    getSoftAssert().assertTrue(getBtnAddCard().isDisplayed(), "Add card button is visible");
  }

  /**
   * Validate default payments methods
   *
   * @param paymentMethods number of payment methods of the user, default is 3
   */
  @Step("Validate default payment methods {paymentMethods}")
  public void validateDefaultPaymentMethods(int paymentMethods) {
    if (paymentMethods > 0) {
      getSoftAssert().assertEquals(getLstPaymentMethods().size(), paymentMethods,
        "Number of payment methods are equal");
    } else {
      getSoftAssert().assertTrue(getEmptyState().isDisplayed(), "Empty state is visible");
    }
  }

  /**
   * Validate all payments method modal
   */
  @Step("Validate modal info")
  public void validateAllPaymentsModal() {
    getSoftAssert().assertTrue(getPaymentModalComponent().getLblTitle().isDisplayed(),
      "Payments modal title is visible");
    getSoftAssert().assertTrue(getPaymentModalComponent().getBtnClose().isDisplayed(),
      "Close button is visible");
    getSoftAssert().assertTrue(getPaymentModalComponent().getBtnAddCard().isDisplayed(),
      "Add card button is visible");
    getSoftAssert().assertFalse(getPaymentModalComponent().getLstPaymentMethods().isEmpty(),
      "Payment methods not empty");
  }

  /**
   * Validate card info
   *
   * @param isPreferredCard true if is the preferred card
   */
  @Step("Validate card general info")
  public void validateCard(boolean isPreferredCard) {
    getSoftAssert().assertTrue(getCardDetailsModalComponent().getBtnBack().isDisplayed(),
      "Back button is visible");
    getSoftAssert().assertTrue(getCardDetailsModalComponent().getBtnClose().isDisplayed(),
      "Close button is visible");
    getSoftAssert().assertTrue(getCardDetailsModalComponent().getImgCard().isDisplayed(),
      "Image card is visible");
    getSoftAssert().assertTrue(getCardDetailsModalComponent().getLblCardNumber().isDisplayed(),
      "Card number is visible");
    getSoftAssert().assertTrue(getCardDetailsModalComponent().getLblExpiryDate().isDisplayed(),
      "Expiry date is visible");
    if (isPreferredCard) {
      getSoftAssert().assertFalse(
        getCardDetailsModalComponent().getCheckboxPreferredCard().isEnabled(),
        "Preferred checkbox is disabled");
    } else {
      getSoftAssert().assertTrue(
        getCardDetailsModalComponent().getCheckboxPreferredCard().isEnabled(),
        "Preferred checkbox is enabled");
    }
    getSoftAssert().assertTrue(
      getCardDetailsModalComponent().getBtnDeletePaymentMethod().isDisplayed(),
      "Delete payment button is visible");
  }

  /**
   * Validate preferred card checkbox empty
   *
   * @param isEmpty if true, the card is not the preferred payment method
   */
  @Step("Validate if the preferred checkbox is marked as empty or filled")
  public void isPreferredCheckBoxEmpty(boolean isEmpty) {
    getSoftAssert().assertEquals(
      getCardDetailsModalComponent().getCheckboxPreferredCard().getAttribute("label"),
      isEmpty ? "unselected" : "checkbox", "Card is not the preferred payment method");
  }

  /**
   * Validate add payment method modal
   */
  @Step("Validate add payment method modal")
  public void validateAddPaymentModal() {
    getSoftAssert().assertTrue(getAddPaymentMethodModalComponent().getLblTitle().isDisplayed(),
      "Add credit card title visible");
    getSoftAssert().assertTrue(getAddPaymentMethodModalComponent().getBtnDiscard().isDisplayed(),
      "Discard button visible");
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
      "Add card button is disabled");
  }

  public void validateAddCardEnabled() {
    getSoftAssert().assertTrue(getAddPaymentMethodModalComponent().getBtnAddCard().isEnabled(),
      "Add card button is enabled");
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
    getSoftAssert().assertFalse(getLstPaymentMethods().isEmpty(), "Payment methods not empty");
  }
}
