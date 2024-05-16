package com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_CARD;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CHANGE;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.quickIsDisplayed;

import com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections;
import com.automation.lac.qa.fanapp.mobile.screens.modals.ModalPayWithScreen;
import com.automation.lac.qa.fanapp.mobile.screens.purchase.CheckoutScreen;
import com.automation.lac.qa.fanapp.mobile.utils.DeviceActions;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;

public class AddPaymentPurchaseTask extends CheckoutScreen {

  protected ModalPayWithScreen modalPayWithScreen =
    new ModalPayWithScreen();

  /**
   * adds a new payment card for purchase the ticket
   */
  public void addNewCardForPurchase() {
    DeviceActions.verticallyScrollToElement(
      getBtnPlaceOrder(), SwipeDirections.DOWN_TO_UP,1, 50);
    if (quickIsDisplayed(getBtnCheckoutChange())) {
      click(getBtnCheckoutChange(), CHANGE.getValue());
      click(modalPayWithScreen.getBtnPayWithAddCard(), ADD_CARD.getValue());
    } else {
      click(getBtnCheckoutAddCard(), ADD_CARD.getValue());
    }
  }

  /**
   * retrieve List Of Elements From DataTable
   */
  public List<Map<String, String>> retrieveListOfElementsFromDataTable(DataTable dataTable) {
    return dataTable.asMaps(String.class, String.class);
  }
}
