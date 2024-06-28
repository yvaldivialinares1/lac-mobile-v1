package com.automation.lac.qa.fanapp.mobile.tasks.modals;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ENABLE_PHONE_BIOMETRICS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.I_WILL_DO_LATER;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.fanapp.mobile.screens.modals.AddBiometricsModal;

public class AddBiometricsModalTask extends AddBiometricsModal {

  public void clickOnAddPhoneBiometrics() {
    waitForBiometricsModalToAppear();
    click(getBtnEnablePhoneBiometrics(), ENABLE_PHONE_BIOMETRICS.getValue());
  }

  public void clickOnIllDoItLater() {
    waitForBiometricsModalToAppear();
    click(getBtnIWillDoItLater(), I_WILL_DO_LATER.getValue());
  }

  private void waitForBiometricsModalToAppear() {
    waitForElementVisibility(getLblBiometricsModal(), 10);
  }
}
