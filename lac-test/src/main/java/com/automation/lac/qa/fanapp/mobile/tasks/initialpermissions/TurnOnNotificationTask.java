package com.automation.lac.qa.fanapp.mobile.tasks.initialpermissions;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ALLOW;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ALLOW_WHILE_USING_APP;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONTINUE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.WHILE_USING_APP;
import static com.automation.lac.qa.utils.mobile.AlertActions.tapIosAlertOption;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.initialpermissions.TurnOnNotificationScreen;
import com.automation.lac.qa.utils.mobile.WaitActions;

public class TurnOnNotificationTask extends TurnOnNotificationScreen {

  /**
   * Perform all the necessary steps for granting of Permissions to FanApp
   */
  public void grantLacPermission() {
    click(getBtnContinue(), CONTINUE.getValue());
    if (isAndroid()) {
      if (WaitActions.waitForElementVisibility(getAllowNotificationComponent().getBtnAllow(), 5))
        click(getAllowNotificationComponent().getBtnAllow(), ALLOW.getValue());
    } else {
      tapIosAlertOption(ALLOW.getValue());
    }
    click(getBtnContinue(), CONTINUE.getValue());
    if (isAndroid()) {
      click(getEnableLocationComponent().getBtnWhileUsingTheApp(), WHILE_USING_APP.getValue());
    } else {
      tapIosAlertOption(ALLOW_WHILE_USING_APP.getValue());
    }
  }
}