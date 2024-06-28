package com.automation.lac.qa.fanapp.mobile.tasks.purchase;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PLACE_ORDER;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.DOWN_TO_UP;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.fanapp.mobile.screens.purchase.CheckoutScreen;

public class CheckoutTasks extends CheckoutScreen {

  /**
   * Click on Place Order
   */
  public void clickOnPlaceOrder() {
    waitForElementVisibility(getCheckoutSummaryEventName(), 60);
    swipeUntilFindElement(getBtnCheckoutChange(), DOWN_TO_UP);
    waitForElementVisibility(getBtnCheckoutChange(), 20);
    click(getBtnPlaceOrder(), PLACE_ORDER.getValue());
  }
}