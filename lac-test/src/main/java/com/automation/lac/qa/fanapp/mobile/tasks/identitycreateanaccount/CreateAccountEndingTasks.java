package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.TAKE_ME_TO_ID;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.CreateAccountEndingScreen;

public class CreateAccountEndingTasks extends CreateAccountEndingScreen {

  public void clickTakeMeToIntuitDome() {
    click(getBtnTakeMeToIntuitDome(), TAKE_ME_TO_ID.getValue());
  }
}