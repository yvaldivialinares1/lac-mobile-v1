package com.automation.lac.qa.fanapp.mobile.tasks.purchase;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PLACE_ORDER;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.waitForElementBeClickable;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections;
import com.automation.lac.qa.fanapp.mobile.screens.purchase.CheckoutScreen;
import com.automation.lac.qa.fanapp.mobile.utils.DeviceActions;

public class CheckoutTasks extends CheckoutScreen {

  /**
   * Click on Place Order
   */
  public void clickOnPlaceOrder() {
    DeviceActions.verticallyScrollToElement(
      getBtnPlaceOrder(), SwipeDirections.DOWN_TO_UP,1, 50);
    click(waitForElementBeClickable(20, getBtnPlaceOrder()), PLACE_ORDER.getValue());
  }
}