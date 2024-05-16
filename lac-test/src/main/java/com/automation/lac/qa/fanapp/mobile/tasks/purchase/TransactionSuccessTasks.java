package com.automation.lac.qa.fanapp.mobile.tasks.purchase;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONTINUE;
import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.waitForElementBeClickable;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.purchase.TransactionSuccessScreen;

public class TransactionSuccessTasks extends TransactionSuccessScreen {

  /**
   * Click on Continue
   */
  public void clickOnContinue() {
    click(waitForElementBeClickable(20, getBtnContinue()), CONTINUE.getValue());
  }
}