package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;
import static com.automation.lac.qa.utils.Constants.APP_MOCKS;

import com.automation.lac.qa.fanapp.mobile.tasks.commons.CommonTask;
import com.automation.lac.qa.fanapp.mobile.tasks.gamefaceid.GameFaceIdEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.gamefaceid.VerifyYourAgeTask;
import io.cucumber.java.en.And;

public class GameFaceIdStepsDefinition {
  private final GameFaceIdEducationalTask gameFaceIdEducationalTask =
    new GameFaceIdEducationalTask();
  private final VerifyYourAgeTask verifyYourAgeTask = new VerifyYourAgeTask();
  private final CommonTask commonTask = new CommonTask();

  @And("the user skip the game face registration")
  public void theUserSkipTheGameFaceRegistration() {
    gameFaceIdEducationalTask.skipGameFaceEducationalScreen();
  }

  /**
   * This method simulates the completion of the game face registration by a user.
   */
  @And("the user completes the game face registration( from reminder)?$")
  public void theUserCompletesTheGameFaceRegistration(String from) {
    //TODO: remove conditional and else condition upon the mock support is in place for iOS.
    if (isAndroid() && APP_MOCKS) {
      gameFaceIdEducationalTask.setMyGameFaceId();
    } else {
      if (from != null && from.contains("from reminder")) {
        commonTask.navigateBack("My Profile");
      } else {
        gameFaceIdEducationalTask.skipGameFaceEducationalScreen();
      }
    }
  }

  /**
   * This method handles age verification based on the platform and app mock settings.
   * <p>
   * If the application is running on an Android device and the application mocks are enabled,
   * it will simulate a click on the "Continue" button in the age verification task.
   * </p>
   */
  @And("the user does not need to do Age Verification")
  public void theUserDoesNotNeedToDoAgeVerification() {
    if (isAndroid() && APP_MOCKS) {
      verifyYourAgeTask.clickOnContinue();
    }

  }
}