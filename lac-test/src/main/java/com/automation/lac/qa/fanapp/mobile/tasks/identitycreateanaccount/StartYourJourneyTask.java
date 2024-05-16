package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CREATE_ACCOUNT;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.EMAIL_ADDRESS;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.StartYourJourneyScreen;

public class StartYourJourneyTask extends StartYourJourneyScreen {

  /**
   * Enter the Email Address to use in the account creation flow
   */
  public void enterEmailAddress(String emailAddress) {
    sendKeys(getTxtEmailAddress(), emailAddress, EMAIL_ADDRESS.getValue());
    click(getBtnCreateAccount(), CREATE_ACCOUNT.getValue());
  }
}