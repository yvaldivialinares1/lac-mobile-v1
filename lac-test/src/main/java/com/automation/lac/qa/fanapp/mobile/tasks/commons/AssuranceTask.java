package com.automation.lac.qa.fanapp.mobile.tasks.commons;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CANCEL;

import com.automation.lac.qa.fanapp.mobile.screens.commons.AssuranceScreen;
import com.automation.lac.qa.utils.mobile.DeviceActions;

public class AssuranceTask extends AssuranceScreen {

  public void clickOnCancelButton() {
    DeviceActions.click(getBtnCancel(), CANCEL.getValue());
  }

}