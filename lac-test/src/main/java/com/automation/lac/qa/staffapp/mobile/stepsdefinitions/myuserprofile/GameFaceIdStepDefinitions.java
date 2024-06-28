package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.myuserprofile;

import com.automation.lac.qa.staffapp.mobile.questions.home.HomeQuestions;
import com.automation.lac.qa.staffapp.mobile.tasks.gameface.GameFaceIdEducationalTask;
import com.automation.lac.qa.staffapp.mobile.tasks.welcome.WelcomeTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class GameFaceIdStepDefinitions {

  private final WelcomeTask welcomeTask = new WelcomeTask();
  private final GameFaceIdEducationalTask gameFaceIdEducationalTask =
    new GameFaceIdEducationalTask();
  private final HomeQuestions homeQuestions = new HomeQuestions();

  /**
   * User starts creating game face id
   */
  @And("the user starts creating game face id")
  public void userStartsCreatingGameFaceId() {
    gameFaceIdEducationalTask.continueWithGameFaceId();
    welcomeTask.clickAllowButtonIfDisplayed();
  }

  /**
   * User starts creating game face id
   */
  @And("the user selects 'DO IT LATER' option while creating game face id")
  public void userSelectsDoItLaterOption() {
    //TODO: This step will get removed after confirmation of new UI flow.
    //gameFaceIdEducationalTask.selectAcknowledgementCheckBox();
    gameFaceIdEducationalTask.clickOnDoItLater();
  }

  /**
   * User navigated to Home Screen
   */
  @Then("the user is navigated to a home screen")
  public void theUserIsOnTheWelcomeHomeScreen() {
    homeQuestions.checkWelcomeHomeScreenIsDisplayed();
  }
}
