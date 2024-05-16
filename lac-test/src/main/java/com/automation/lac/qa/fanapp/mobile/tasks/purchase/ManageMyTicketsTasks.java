package com.automation.lac.qa.fanapp.mobile.tasks.purchase;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SKIP;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.waitForElementBeClickable;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.purchase.ManageMyTicketsScreen;

public class ManageMyTicketsTasks extends ManageMyTicketsScreen {

  /**
   * Click on Skip
   */
  public void clickOnSkip() {
    click(waitForElementBeClickable(20, getBtnSkip()), SKIP.getValue());
  }
}