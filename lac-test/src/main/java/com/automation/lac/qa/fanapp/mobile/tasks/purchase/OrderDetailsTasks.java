package com.automation.lac.qa.fanapp.mobile.tasks.purchase;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.GO_TO_PAY;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.waitForElementBeClickable;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.purchase.OrderDetailsScreen;

public class OrderDetailsTasks extends OrderDetailsScreen {

  /**
   * Click on Go To Pay
   */
  public void clickOnGoToPay() {
    click(waitForElementBeClickable(20, getBtnGoToPay()), GO_TO_PAY.getValue());
  }
}