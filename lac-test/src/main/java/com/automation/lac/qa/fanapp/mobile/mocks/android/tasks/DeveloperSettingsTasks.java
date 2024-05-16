package com.automation.lac.qa.fanapp.mobile.mocks.android.tasks;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.mocks.android.screens.DeveloperSettingsScreen;

public class DeveloperSettingsTasks extends DeveloperSettingsScreen {
  /**
   * Activates the "Some other flag" if the activate parameter is true.
   *
   * @param activate A boolean flag indicating whether to activate "Some other flag".
   */
  public void activateMockSomeOtherFlag(boolean activate) {
    if (activate) {
      click(getSwitchSomeOtherFlag(), "Some other flag");
    }
  }

  /**
   * Activates the "Mock Game Face Skip Camera" feature if the skip parameter is true.
   *
   * @param skip A boolean flag indicating whether to skip the Game Face camera step.
   */
  public void activateMockGameFaceSkipCamera(boolean skip) {
    if (skip) {
      click(getSwitchGameFaceSkipCamera(), "Mock Game Face Skip Camera");
    }
  }

  /**
   * Activates the "Mock Identity Pass skip" feature if the skip parameter is true.
   *
   * @param skip A boolean flag indicating whether to skip the Identity Pass step.
   */
  public void activateMockIdentityPassSkip(boolean skip) {
    if (skip) {
      click(getSwitchIdentityPassSkip(), "Mock Identity Pass skip");
    }
  }

  /**
   * Activates the "Mock Phone OTP skip" feature if the skip parameter is true.
   *
   * @param skip A boolean flag indicating whether to skip the Phone OTP step.
   */
  public void activateMockPhoneOtpSkip(boolean skip) {
    if (skip) {
      click(getSwitchPhoneOtpSkip(), "Mock Phone OTP skip");
    }
  }

}