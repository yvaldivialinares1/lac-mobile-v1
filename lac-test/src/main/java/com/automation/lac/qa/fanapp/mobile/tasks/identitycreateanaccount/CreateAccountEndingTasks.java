package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONTINUE_PURCHASE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.TAKE_ME_TO_ID;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementAttributeValue;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.CreateAccountEndingScreen;

public class CreateAccountEndingTasks extends CreateAccountEndingScreen {

  /**
   * Completes Account Creation process
   */
  public void clickTakeMeToIntuitDome() {
    waitForElementVisibility(getBtnTakeMeToIntuitDome(), 10);
    if (!isAndroid()) {
      waitForElementAttributeValue(getBtnTakeMeToIntuitDome(), "visible", "true", 10);
      waitForElementAttributeValue(getBtnTakeMeToIntuitDome(), "visible", "true", 10);
    }
    click(getBtnTakeMeToIntuitDome(), TAKE_ME_TO_ID.getValue());
  }

  /**
   * complete Account creation during ticket purchase
   */
  public void clickContinuePurchase() {
    waitForElementVisibility(getBtnContinuePurchase(), 20);
    click(getBtnContinuePurchase(), CONTINUE_PURCHASE.getValue());
  }
}