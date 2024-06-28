package com.automation.lac.qa.staffapp.mobile.tasks.gameface;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;

import com.automation.lac.qa.staffapp.mobile.screens.gameface.GameFaceIdEducationalScreen;
import io.qameta.allure.Step;

public class GameFaceIdEducationalTask extends GameFaceIdEducationalScreen {

  /**
   * skip ContinueGameFaceIdScreen if displayed
   */
  @Step("Skip game face ID")
  public void skipContinueGameFaceIfDisplayed() {
    if (isTheElementVisible(getBtnContinueWithGameFaceId(), 4)) {
      click(getBtnSkip(), "SKIP");
    }
  }

  /**
   * Continue with Game Face Id Screen
   */
  public void continueWithGameFaceId() {
    if (isTheElementVisible(getBtnContinueWithGameFaceId(), 4)) {
      click(getBtnContinueWithGameFaceId(), "Continue with Game Face ID");
    }
  }

  /**
   * Select Acknowledge check box
   */
  public void selectAcknowledgementCheckBox() {
    click(getCheckBoxAcknowledgement(), "Acknowledgement checkbox");
  }

  /**
   * Click on Do It Later
   */
  public void clickOnDoItLater() {
    if (isTheElementVisible(getBtnDoItLater(), 4)) {
      click(getBtnDoItLater(), "Do It Later");
    }
  }
}
