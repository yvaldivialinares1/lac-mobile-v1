package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.assertAll;
import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.CARD_LAST_FOUR_DIGITS;
import static com.automation.lac.qa.fanapp.mobile.screens.paymentmethods.MyPaymentMethodsScreen.getCardNumber;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeElementToTheBorder;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.TOP_PAGE;
import static com.automation.lac.qa.utils.mobile.WaitActions.elementIsDisplayed;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.fanapp.api.models.PaymentMethodFile;
import com.automation.lac.qa.fanapp.mobile.screens.purchase.CheckoutScreen;
import com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils;
import com.automation.lac.qa.utils.CustomException;
import java.util.List;

public class CheckOutScreenQuestions extends CheckoutScreen {

  /**
   * Validates if the preferred card is selected as an existing payment method.
   * Throws a CustomException if no card is added to purchase the ticket.
   */
  public void validateExistingPaymentCard(String screen) {
    List<PaymentMethodFile.PaymentMethod> cards = PaymentUtils.getAllUsedCards();
    PaymentMethodFile.PaymentMethod preferredCard = cards.stream()
      .filter(PaymentMethodFile.PaymentMethod::isPreferred)
      .findFirst()
      .orElseThrow(() -> new CustomException("None of the Preferred card is available"));
    String lastFour = PaymentUtils.getCardLastFourDigits(preferredCard.getCardNumber());
    getTestContext().set(CARD_LAST_FOUR_DIGITS.name(), lastFour);
    switch (screen.toLowerCase()) {
      case "checkout":
        swipeElementToTheBorder(TOP_PAGE, getCheckoutSummaryOrderTotalValue());
        getSoftAssert().assertTrue(getPreferredCard().getText().contains(lastFour),
          "Check correct Preferred card is displayed");
        getSoftAssert().assertTrue(getRdoPreferredCard().isDisplayed(),
          "Check preferred card is selected");
        break;
      case "share payment method":
        waitForElementVisibility(getPreferredCard(), 10);
        getSoftAssert().assertTrue(getPreferredCard().getText().contains(lastFour),
          "Check correct Preferred card is displayed");
        break;
      default:
        throw new CustomException("Unsupported screen type: " + screen);
    }
    assertAll();
  }

  /**
   * Validates Payment Card In Checkout Screen
   */
  public void validatePaymentCardInCheckoutScreen(PaymentMethodFile.PaymentMethod card) {
    String lastFour = PaymentUtils.getCardLastFourDigits(card.getCardNumber());
    getSoftAssert().assertTrue(elementIsDisplayed(getCardNumber(lastFour)),
      "Check correct card is displayed");
    getSoftAssert().assertTrue(elementIsDisplayed(getRdoNonPreferredCard(lastFour)),
      "Check radio button for correctly selected");
    assertAll();
  }
}
