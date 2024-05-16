package com.automation.lac.qa.staffapp.mobile.tasks.gameface;

import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.waitForElementVisibility;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.staffapp.mobile.screens.gameface.GameFaceIdEducationalScreen;

public class GameFaceIdEducationalTask extends GameFaceIdEducationalScreen {

  /**
   * skip ContinueGameFaceIdScreen if displayed
   */
  public void skipContinueGameFaceIfDisplayed() {
    if (waitForElementVisibility(getBtnContinueWithGameFaceId(), 4)) {
      click(getBtnSkip(), "SKIP");
    }
  }
}
