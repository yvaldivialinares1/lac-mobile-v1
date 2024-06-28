package com.automation.lac.qa.fanapp.mobile.tasks.gamefaceid;

import com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription;
import com.automation.lac.qa.fanapp.mobile.screens.gamefaceid.VerifyYourAgeScreen;
import com.automation.lac.qa.utils.mobile.DeviceActions;
import io.qameta.allure.Step;


public class VerifyYourAgeTask extends VerifyYourAgeScreen {

  @Step("The user does not need to do Age Verification")
  public void clickOnContinue() {
    DeviceActions.click(getBtnContinue(), ButtonsDescription.CONTINUE.getValue());
  }
}