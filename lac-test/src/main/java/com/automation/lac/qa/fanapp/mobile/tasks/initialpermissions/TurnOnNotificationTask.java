package com.automation.lac.qa.fanapp.mobile.tasks.initialpermissions;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ALLOW;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ALLOW_WHILE_USING_APP;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONTINUE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.WHILE_USING_APP;
import static com.automation.lac.qa.utils.mobile.AlertActions.tapIosAlertOption;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.fanapp.mobile.screens.initialpermissions.TurnOnNotificationScreen;

public class TurnOnNotificationTask extends TurnOnNotificationScreen {

  /**
   * Perform all the necessary steps for granting of Permissions to FanApp
   */
  public void grantLacPermission() {
    waitForElementVisibility(getBtnContinue(), 20);
    click(getBtnContinue(), CONTINUE.getValue());
    turnOnNotifications();
    click(getBtnContinue(), CONTINUE.getValue());
    enableLocation();
  }

  /**
   * Turn on notifications
   */
  public void turnOnNotifications() {
    if (isAndroid()) {
      if (isTheElementVisible(getAllowNotificationsComponent().getBtnAllow(), 5))
        click(getAllowNotificationsComponent().getBtnAllow(), ALLOW.getValue());
    } else {
      tapIosAlertOption(ALLOW.getValue());
    }
  }

  /**
   * Enable location
   */
  public void enableLocation() {
    if (isAndroid()) {
      click(getEnableLocationComponent().getBtnWhileUsingTheApp(), WHILE_USING_APP.getValue());
    } else {
      tapIosAlertOption(ALLOW_WHILE_USING_APP.getValue());
    }
  }
}