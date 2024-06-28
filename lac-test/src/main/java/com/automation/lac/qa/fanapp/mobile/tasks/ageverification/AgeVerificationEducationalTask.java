package com.automation.lac.qa.fanapp.mobile.tasks.ageverification;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SKIP;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;

import com.automation.lac.qa.fanapp.mobile.screens.educationals.AgeVerificationEducationalScreen;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgeVerificationEducationalTask extends AgeVerificationEducationalScreen {

  public void skipAgeVerificationEducationalScreen() {
    click(getBtnSkip(), SKIP.getValue());
  }

  public void clickBackOnAgeVerificationEducationalScreen() {
    click(getBtnBack(), BACK.getValue());
  }


  /**
   * Skip age verification for adult, if adult is verified by Game Face there is no action, if
   * it is an adult not verified by game face it skips the age verification screen.
   */
  @Step("The user does skips Age Verification")
  public void skipAgeVerificationRegistration() {
    if (isAndroid()) {
      if (isTheElementVisible(getBtnVerifyMyAge(), 10)) {
        skipAgeVerificationEducationalScreen();
      }
    } else {
      log.info("iOS mock is not supported for now, therefore skipping this step.");
    }
  }
}