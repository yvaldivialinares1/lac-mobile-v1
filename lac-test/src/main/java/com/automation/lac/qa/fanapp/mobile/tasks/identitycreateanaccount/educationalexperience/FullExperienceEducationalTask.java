package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.educationalexperience;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.LIVE_THE_FULL_EXPERIENCE;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.educationalexperience.FullExperienceEducationalScreen;

public class FullExperienceEducationalTask extends FullExperienceEducationalScreen {

  /**
   * Start the Live Full Experience on FanApp
   */
  public CreateAccountEducationalTask liveTheFullExperience() {
    waitForElementToBeClickable(5, getBtnLiveTheFullExperience());
    click(getBtnLiveTheFullExperience(), LIVE_THE_FULL_EXPERIENCE.getValue());
    return new CreateAccountEducationalTask();
  }
}