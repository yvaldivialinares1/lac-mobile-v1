package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.fanapp.api.models.PaymentMethodFile.PaymentMethod;
import static com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils.getAllUsedCards;
import static com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils.getCardLastFourDigits;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.DOWN_TO_UP;

import com.automation.lac.qa.fanapp.mobile.screens.paymentmethods.MyPaymentMethodsScreen;
import com.automation.lac.qa.fanapp.mobile.utils.DeviceActions;
import com.automation.lac.qa.utils.mobile.WaitActions;
import java.util.List;

public class MyPaymentsMethodScreenQuestion extends MyPaymentMethodsScreen {

  /**
   * check and validate the updated list of Payment
   */
  public void validatePaymentList() {
    List<PaymentMethod> cards = getAllUsedCards();
    for (PaymentMethod card : cards) {
      getSoftAssert().assertTrue(isCardOnList(card),
        String.format("card %s is displayed", getCardLastFourDigits(card.getCardNumber())));
    }
  }

  /**
   * Scrolls on the card list to find a card match the last four card digits
   *
   * @param card payment method object
   * @return true/false if the card is found and displayed
   */
  private boolean isCardOnList(PaymentMethod card) {
    String lastFour = getCardLastFourDigits(card.getCardNumber());
    if (isAndroid()) {
      swipeUntilFindElement(getCardNumber(lastFour), DOWN_TO_UP, getScrollableView());
    } else {
      DeviceActions.verticallyScrollToElement(getCardNumber(lastFour), DOWN_TO_UP, 5, 30);
    }
    return WaitActions.isTheElementVisible(
      getCardNumber(getCardLastFourDigits(card.getCardNumber())), 1);
  }
}
