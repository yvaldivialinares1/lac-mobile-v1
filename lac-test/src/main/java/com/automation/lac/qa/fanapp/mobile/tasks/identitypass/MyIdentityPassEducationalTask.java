package com.automation.lac.qa.fanapp.mobile.tasks.identitypass;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SKIP;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.educationals.MyIdentityPassEducationalScreen;

public class MyIdentityPassEducationalTask extends MyIdentityPassEducationalScreen {

  /**
   * Skip My Identity Pass Educational Screen
   */
  public void skipMyIdentityPassEducationalScreen() {
    click(getBtnSkip(), SKIP.getValue());
  }

  public void clickOnBackButtonEducationalScreen() {
    click(getBtnBack(), BACK.getValue());
  }
}
