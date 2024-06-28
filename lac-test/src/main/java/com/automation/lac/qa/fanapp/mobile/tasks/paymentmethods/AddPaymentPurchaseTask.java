package com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_CARD;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.TOP_PAGE;

import com.automation.lac.qa.fanapp.mobile.screens.modals.ModalPayWithScreen;
import com.automation.lac.qa.fanapp.mobile.screens.purchase.CheckoutScreen;
import com.automation.lac.qa.utils.mobile.SwipeActions;
import com.automation.lac.qa.utils.mobile.WaitActions;

public class AddPaymentPurchaseTask extends CheckoutScreen {

  protected ModalPayWithScreen modalPayWithScreen =
    new ModalPayWithScreen();

  /**
   * adds a new payment card for purchase the ticket
   */
  public void addNewCardForPurchase() {
    SwipeActions.swipeElementToTheBorder(TOP_PAGE, getCheckoutSummaryOrderTotalValue());
    click(getBtnCheckoutAddCard(), ADD_CARD.getValue());
    if (WaitActions.isTheElementVisible(modalPayWithScreen.getBtnPayWithAddCard(), 2))
      click(modalPayWithScreen.getBtnPayWithAddCard(), ADD_CARD.getValue());
  }

}
