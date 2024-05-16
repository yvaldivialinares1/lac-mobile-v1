package com.automation.lac.qa.fanapp.mobile.tasks.modals;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CANCEL;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.YES_LOG_ME_OUT;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.modals.ModalLogOutScreen;

public class ModalLogOutTasks extends ModalLogOutScreen {
  public void clickOnLogOutButton() {
    click(getBtnYesLogMeOut(), YES_LOG_ME_OUT.getValue());
  }

  public void clickOnCancelButton() {
    click(getBtnCancel(), CANCEL.getValue());
  }
}
