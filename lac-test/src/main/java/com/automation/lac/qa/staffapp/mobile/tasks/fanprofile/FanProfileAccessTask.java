package com.automation.lac.qa.staffapp.mobile.tasks.fanprofile;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.staffapp.mobile.enums.ManualAgeVerificationOption;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.FanProfileAccessScreen;
import io.qameta.allure.Step;

public class FanProfileAccessTask extends FanProfileAccessScreen {

  public void removeAgeVerificationNote() {
    click(getFanProfileAccessIdentityComponent().getBtnRemoveAgeVerification(), "remove");
    click(getRemoveAgeVerificationAlertComponent().getBtnConfirmRemove(), "yes, remove");
  }

  @Step("tap the button to unlink clipperband")
  public FanProfileAccessTask tapUnlinkFanClipperBand() {
    click(getFanProfileClipperBandComponent().getBtnUnlink(), "unlink");
    return this;
  }

  public void confirmUnlinkFanClipperBand() {
    click(getUnlinkClipperBandAlertComponent().getBtnConfirmUnlink(), "yes, unlink");
  }

  @Step("tap the button to verify fan age manually")
  public FanProfileAccessTask tapVerifyAgeManuallyButton() {
    click(getFanProfileAccessIdentityComponent().getBtnVerifyManually(), "verify manually");
    return this;
  }

  @Step("tap back button")
  public void tapBackButton() {
    click(getFanProfileAccessIdentityComponent().getBtnBack(), "Back");
  }

  @Step("Select manual age verification option: {option}")
  public void selectManualAgeVerificationOption(ManualAgeVerificationOption option) {
    getManualAgeVerificationComponent().selectManualAgeVerificationOption(option);
    click(getManualAgeVerificationComponent().getBtnVerify(), "verify");
  }

  /**
   * open fan profile vehicles list.
   */
  @Step("open fan profile vehicle list")
  public void openFanProfileVehiclesList() {
    if (!isIpad()) {
      click(getFanProfileAccessVehiclesComponent().getLblFanVehicles(), "vehicles");
    } else click(getFanProfileAccessVehiclesComponent().getBtnViewAllVehicles(), "view all");
  }
}
