package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.userinfo.AccountInfo;
import com.automation.lac.qa.fanapp.mobile.tasks.home.WelcomeHomeTask;
import com.automation.lac.qa.fanapp.mobile.tasks.initialpermissions.TurnOnNotificationTask;
import com.automation.lac.qa.fanapp.mobile.tasks.login.LoginTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginStepsDefinitions {

  private final TurnOnNotificationTask turnOnNotificationTask = new TurnOnNotificationTask();
  private final WelcomeHomeTask welcomeHomeTask = new WelcomeHomeTask();
  private final LoginTask loginScreenTasks = new LoginTask();
  private final UserInfo userInfo = getTestContext().get(USER_INFO.name());

  /**
   *
   */
  @Given("A user opens the Los Angeles Clippers application")
  public void chuckOpensHisLosAngelesClippersApplication() {
    turnOnNotificationTask.grantLacPermission();
  }

  /**
   * Login
   *
   * @param user     username
   * @param password password
   */
  @When("A user logs in using the user {string} and password {string}")
  public void chuckLogsIn(String user, String password) {
    welcomeHomeTask.goToLogin();
    loginScreenTasks.performLogin(user, password);
  }

  @Then("A user should be able to see the home screen")
  public void chuckShouldBeAbleToSeeTheHomeScreen() {
    // TODO document why this method is empty
  }

  /**
   * Facilitates the user login by entering credentials retrieved from the test context.
   * This method navigates through the login process and inputs the user's email and password.
   */
  @And("user enters credentials to log in")
  public void userEntersCredentialsToLogIn() {
    AccountInfo accountInfo = userInfo.getAccountInfo();
    loginScreenTasks.performLogin(accountInfo.getEmail(), accountInfo.getPassword());
  }
}