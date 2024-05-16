package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.login;

import com.automation.lac.qa.staffapp.mobile.tasks.gameface.GameFaceIdEducationalTask;
import com.automation.lac.qa.staffapp.mobile.tasks.login.LoginTask;
import com.automation.lac.qa.staffapp.mobile.tasks.welcome.WelcomeTask;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {

  private final WelcomeTask welcomeTask = new WelcomeTask();
  private final LoginTask loginTask = new LoginTask();
  private final GameFaceIdEducationalTask gameFaceIdEducationalTask =
    new GameFaceIdEducationalTask();

  /**
   * Perform Login
   *
   * @param email    String
   * @param password String
   */
  @When("employee logs in the application using email {string} and password {string}")
  public void employeeLogsInTheApplicationUsingEmailAndPassword(String email, String password) {
    welcomeTask
      .clickAllowButtonIfDisplayed()
      .clickLoginButton()
      .clickContinueButton();
    loginTask.performLogin(email, password);
    gameFaceIdEducationalTask.skipContinueGameFaceIfDisplayed();
  }
}
