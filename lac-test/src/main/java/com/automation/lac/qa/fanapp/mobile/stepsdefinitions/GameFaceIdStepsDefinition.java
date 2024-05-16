package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import com.automation.lac.qa.fanapp.mobile.tasks.gamefaceid.GameFaceIdEducationalTask;
import io.cucumber.java.en.And;

public class GameFaceIdStepsDefinition {
  private GameFaceIdEducationalTask gameFaceIdEducationalTask = new GameFaceIdEducationalTask();

  @And("the user skip the game face registration")
  public void theUserSkipTheGameFaceRegistration() {
    gameFaceIdEducationalTask.skipGameFaceEducationalScreen();
  }
}