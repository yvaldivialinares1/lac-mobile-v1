package com.automation.lac.qa.fanapp.mobile.tasks.identitypass;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_IDENTITY_PASS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SKIP;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;

import com.automation.lac.qa.fanapp.mobile.screens.educationals.MyIdentityPassEducationalScreen;
import io.qameta.allure.Step;

public class MyIdentityPassEducationalTask extends MyIdentityPassEducationalScreen {

  /**
   * Skip My Identity Pass Educational Screen
   */
  @Step("The user skips identity pass")
  public void skipMyIdentityPassEducationalScreen() {
    waitForElementToBeClickable(getBtnSkip(), 20);
    click(getBtnSkip(), SKIP.getValue());
  }

  public void clickOnBackButtonEducationalScreen() {
    click(getBtnBack(), BACK.getValue());
  }

  @Step("The user completes identity pass")
  public void clickOnAddIdentityPass() {
    click(getBtnAddIdentityPass(), ADD_IDENTITY_PASS.getValue());
  }
}