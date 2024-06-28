package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.login;

import static com.automation.lac.qa.staffapp.api.models.files.CredentialsFile.StaffAccount;
import static com.automation.lac.qa.staffapp.api.models.files.CredentialsFile.getStaffValidCredentials;

import com.automation.lac.qa.staffapp.mobile.tasks.gameface.GameFaceIdEducationalTask;
import com.automation.lac.qa.staffapp.mobile.tasks.login.LoginTask;
import com.automation.lac.qa.staffapp.mobile.tasks.welcome.WelcomeTask;
import io.cucumber.java.en.And;

public class LoginStepDefinitions {

  private final WelcomeTask welcomeTask = new WelcomeTask();
  private final LoginTask loginTask = new LoginTask();
  private final GameFaceIdEducationalTask gameFaceIdEducationalTask =
    new GameFaceIdEducationalTask();

  /**
   * Perform Login
   *
   * @param userType String
   */
  @And("^an user logs in the application using (.*) credentials( and skip game face ID)?$")
  public void employeeLogsInTheApplicationUsingEmailAndPassword(String userType,
                                                                String skipGameFaceId) {
    StaffAccount staffAccount = getStaffValidCredentials(userType);
    welcomeTask.clickAllowButtonIfDisplayed().clickLoginButton().clickContinueButton();
    loginTask.performLogin(staffAccount.getMail(), staffAccount.getPassword());
    if (skipGameFaceId != null) {
      gameFaceIdEducationalTask.skipContinueGameFaceIfDisplayed();
    }
  }
}
