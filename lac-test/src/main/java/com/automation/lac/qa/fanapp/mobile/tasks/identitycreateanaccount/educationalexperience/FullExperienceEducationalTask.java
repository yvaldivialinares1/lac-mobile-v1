package com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.educationalexperience;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.LIVE_THE_FULL_EXPERIENCE;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementAttributeContainsValue;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.educationalexperience.FullExperienceEducationalScreen;

public class FullExperienceEducationalTask extends FullExperienceEducationalScreen {

  /**
   * Start the Live Full Experience on FanApp
   */
  public CreateAccountEducationalTask liveTheFullExperience() {
    if (isTheElementVisible(getBtnLiveTheFullExperience(), 10)) {
      if (!isAndroid()) {
        waitForElementAttributeContainsValue(getBtnLiveTheFullExperience(),
          "accessible", "true", 10);
      }
      click(getBtnLiveTheFullExperience(), LIVE_THE_FULL_EXPERIENCE.getValue());
    }
    return new CreateAccountEducationalTask();
  }
}