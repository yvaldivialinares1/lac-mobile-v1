package com.automation.lac.qa.fanapp.mobile.tasks.gamefaceid;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SKIP;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.educationals.GameFaceIdEducationalScreen;

public class GameFaceIdEducationalTask extends GameFaceIdEducationalScreen {

  /**
   * Skip Game Face Educational Screen
   */
  public void skipGameFaceEducationalScreen() {
    click(getBtnSkip(), SKIP.getValue());
  }

  public void clickBackOnGameFaceEducationalScreen() {
    click(getBtnBack(), BACK.getValue());
  }
}
