package com.automation.lac.qa.fanapp.mobile.tasks.ageverification;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SKIP;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.educationals.AgeVerificationEducationalScreen;

public class AgeVerificationEducationalTask extends AgeVerificationEducationalScreen {

  public void skipAgeVerificationEducationalScreen() {
    click(getBtnSkip(), SKIP.getValue());
  }

  public void clickBackOnAgeVerificationEducationalScreen() {
    click(getBtnBack(), BACK.getValue());
  }
}