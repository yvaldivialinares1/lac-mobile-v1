package com.automation.lac.qa.fanapp.mobile.tasks.modals;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ENABLE_PHONE_BIOMETRICS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.I_WILL_DO_LATER;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.modals.ModalFasterLogInScreen;

public class ModalFasterLogInTasks extends ModalFasterLogInScreen {
  public void clickOnAddPhoneBiometrics() {
    click(getBtnEnablePhoneBiometrics(), ENABLE_PHONE_BIOMETRICS.getValue());
  }

  public void clickOnIllDoItLater() {
    click(getBtnIWillDoItLater(), I_WILL_DO_LATER.getValue());
  }
}
