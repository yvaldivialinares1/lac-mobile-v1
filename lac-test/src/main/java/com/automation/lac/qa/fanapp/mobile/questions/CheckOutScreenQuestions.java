package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.fanapp.api.models.PaymentMethodFile;
import com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections;
import com.automation.lac.qa.fanapp.mobile.screens.paymentmethods.MyPaymentMethodsScreen;
import com.automation.lac.qa.fanapp.mobile.screens.purchase.CheckoutScreen;
import com.automation.lac.qa.fanapp.mobile.utils.DeviceActions;
import com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils;
import com.automation.lac.qa.utils.CustomException;
import java.util.List;

public class CheckOutScreenQuestions extends CheckoutScreen {

  protected final MyPaymentMethodsScreen myPaymentMethodsScreen =
    new MyPaymentMethodsScreen();

  protected final CheckoutScreen checkoutScreen =
    new CheckoutScreen();

  /**
   * Validates if the preferred card is selected as an existing payment method.
   * Throws a CustomException if no card is added to purchase the ticket.
   */
  public void validateExistingPaymentCardInCheckoutScreen() {
    List<PaymentMethodFile.PaymentMethod> cards = PaymentUtils.getAllUsedCards();
    PaymentMethodFile.PaymentMethod preferredCard = cards.stream()
      .filter(card -> card.isPreferred() == true)
      .findFirst()
      .orElseThrow(() -> new CustomException("None of the Preferred card is available"));
    String lastFour = PaymentUtils.getCardLastFourDigits(preferredCard.getCardNumber());
    DeviceActions.verticallyScrollToElement(getBtnPlaceOrder(), SwipeDirections.DOWN_TO_UP,
      1, 50);
    getSoftAssert().assertTrue(getPreferredCard().getText().contains(lastFour),
      "Check correct Preferred card is displayed");
    getSoftAssert().assertTrue(getRdoPreferredCard().isDisplayed(),
      "Check preferred card is selected");
    getSoftAssert().assertAll();
  }

  /**
   * Validates Payment Card In Checkout Screen
   */
  public void validatePaymentCardInCheckoutScreen(PaymentMethodFile.PaymentMethod card) {
    String lastFour = PaymentUtils.getCardLastFourDigits(card.getCardNumber());
    DeviceActions.verticallyScrollToElement(getBtnPlaceOrder(), SwipeDirections.DOWN_TO_UP,
      1, 50);
    getSoftAssert().assertTrue(myPaymentMethodsScreen.getCardNumber(lastFour).isDisplayed(),
      "Check correct card is displayed");
    getSoftAssert().assertTrue(checkoutScreen.rdoNonPreferredCard(lastFour).isDisplayed(),
      "Check radio button for correctly selected");
    getSoftAssert().assertAll();
  }
}
