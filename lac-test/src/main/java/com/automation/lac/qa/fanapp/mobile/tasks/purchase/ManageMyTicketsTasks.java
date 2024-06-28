package com.automation.lac.qa.fanapp.mobile.tasks.purchase;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SKIP;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;

import com.automation.lac.qa.fanapp.mobile.screens.purchase.ManageMyTicketsScreen;

public class ManageMyTicketsTasks extends ManageMyTicketsScreen {

  /**
   * Click on Skip
   */
  public void clickOnSkip() {
    waitForElementToBeClickable(getBtnSkip(), 20);
    click(getBtnSkip(), SKIP.getValue());
  }
}