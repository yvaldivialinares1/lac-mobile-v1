package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections.UP_TO_DOWN;

import com.automation.lac.qa.fanapp.api.models.PaymentMethodFile;
import com.automation.lac.qa.fanapp.mobile.screens.paymentmethods.MyPaymentMethodsScreen;
import com.automation.lac.qa.fanapp.mobile.utils.DeviceActions;
import com.automation.lac.qa.fanapp.mobile.utils.PaymentUtils;
import java.util.List;

public class MyPaymentsMethodScreenQuestion extends MyPaymentMethodsScreen {
  public static String txtAttribute = !isAndroid()  ? "label" : "text";

  /**
   * check and validate the updated list of Payment
   */
  public void validatePaymentList() {
    List<PaymentMethodFile.PaymentMethod> cards = PaymentUtils.getAllUsedCards();
    for (PaymentMethodFile.PaymentMethod card : cards) {
      getSoftAssert().assertTrue(isCardOnList(card),
        String.format("card %s is displayed",
          PaymentUtils.getCardLastFourDigits(card.getCardNumber())));
    }
  }

  /**
   * @param card card object to be asserted it exist in the card list
   */
  public void isCardAdded(PaymentMethodFile.PaymentMethod card) {
    DeviceActions.waitForElementVisibility(getLblPaymentMethods(), 5);
    boolean isFound = isCardOnList(card);
    getSoftAssert().assertTrue(isFound,
      String.format("card %s is displayed", card.getCardNumber()));
  }

  /**
   * @param card card object to be asserted it does not exist in the card list
   */
  public void isCardDeleted(PaymentMethodFile.PaymentMethod card) {
    boolean isFound = isCardOnList(card);
    getSoftAssert().assertFalse(isFound,
      String.format("card %s is not displayed", card.getCardNumber()));
  }

  /**
   * Scrolls on the card list to find a card match the last four card digits
   *
   * @param card payment method object
   * @return true/false if the card is found and displayed
   */
  private boolean isCardOnList(PaymentMethodFile.PaymentMethod card) {
    return DeviceActions.scrollIntoListToElementWithCondition(getCardsList(), UP_TO_DOWN, 40,
      webElement -> webElement.getAttribute(txtAttribute)
        .contains(PaymentUtils.getCardLastFourDigits(card.getCardNumber()))).isDisplayed();
  }
}
