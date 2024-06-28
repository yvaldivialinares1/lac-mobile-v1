package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.api.models.MissionsCredentialsFile.getValidCredentials;
import static com.automation.lac.qa.fanapp.api.tasks.identity.IdentityTask.deleteVehiclesAndTeammates;
import static com.automation.lac.qa.fanapp.api.tasks.payments.PaymentsMethodsTask.deleteAllPaymentsMethods;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.userinfo.AccountInfo;
import com.automation.lac.qa.fanapp.api.models.MissionsCredentialsFile.Account;
import com.automation.lac.qa.fanapp.mobile.questions.LoginScreenQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.home.WelcomeHomeTask;
import com.automation.lac.qa.fanapp.mobile.tasks.initialpermissions.TurnOnNotificationTask;
import com.automation.lac.qa.fanapp.mobile.tasks.login.LoginTask;
import com.automation.lac.qa.fanapp.mobile.tasks.modals.AddBiometricsModalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.modals.GoCashlessModalTask;
import com.automation.lac.qa.utils.CustomException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginStepsDefinitions {

  private final TurnOnNotificationTask turnOnNotificationTask = new TurnOnNotificationTask();
  private final WelcomeHomeTask welcomeHomeTask = new WelcomeHomeTask();
  private final LoginTask loginScreenTasks = new LoginTask();
  private final LoginScreenQuestions loginScreenQuestions = new LoginScreenQuestions();
  private final AddBiometricsModalTask addBiometricsModalTask = new AddBiometricsModalTask();
  private final GoCashlessModalTask goCashlessModalTask = new GoCashlessModalTask();

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
    AccountInfo accountInfo = AccountInfo.builder().email(user).password(password).build();
    UserInfo userInfo = UserInfo.builder().accountInfo(accountInfo).build();
    getTestContext().set(USER_INFO.name(), userInfo);
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
    AccountInfo accountInfo = ((UserInfo) getTestContext().get(USER_INFO.name())).getAccountInfo();
    loginScreenTasks.performLogin(accountInfo.getEmail(), accountInfo.getPassword());
  }

  /**
   * Logs the user in with NBA credentials depending on the specified flow.
   * This method determines whether to use the full login flow (email and password)
   * or the create an account flow (password only).
   *
   * @param flow The flow indicating which login method to use. It can be either " Login flow"
   *             or " Create an account flow".
   */
  @And("The user logs in with NBA credentials coming from( Login flow| Create an account flow)$")
  public void userEntersNbaCredentialsToLogIn(String flow) {
    AccountInfo accountInfo = ((UserInfo) getTestContext().get(USER_INFO.name())).getAccountInfo();
    welcomeHomeTask.goToLogin();
    switch (flow) {
      case " Login flow" ->
        loginScreenTasks.performLogin(accountInfo.getEmail(), accountInfo.getPassword());
      case " Create an account flow" -> loginScreenTasks.performLogin(accountInfo.getPassword());
      default -> throw new CustomException(String.format("Unexpected value '%s' ", flow));
    }
  }

  @Then("the banner Email Exist For NBA is showed")
  public void bannerEmailExistForNbaIsShowed() {
    loginScreenQuestions.isBannerEmailExistForNbaDisplayed();
    loginScreenQuestions.validateBannerEmailExistForNbaMessage();
  }

  @Then("the user is in the login screen")
  public void userIsInLoginScreen() {
    loginScreenQuestions.checkElementsOnScreen();
  }

  /**
   * Step definition: The user logs in with credentials for a specific scenario.
   *
   * @param scenarioNumber The scenario number for which credentials are retrieved.
   */
  @And("the user logs in with credentials for scenario {string}")
  public void logsInUsingFile(String scenarioNumber) {
    Account account = getValidCredentials(scenarioNumber);
    resetUserInformation(account);
    welcomeHomeTask.goToLogin();
    loginScreenTasks.performLogin(account.getUserEmail(), account.getUserPassword());

    //TODO Remove platform validation when biometrics modal is displayed on Android
    if (!isAndroid())
      addBiometricsModalTask.clickOnIllDoItLater();

    if (!account.getOnboardingSteps().isPaymentMethod())
      goCashlessModalTask.clickOnRemindMeLater();
  }

  @Step("reset user information")
  private void resetUserInformation(Account account) {
    String userId = account.getUserID();
    deleteVehiclesAndTeammates(userId);
    if (!account.getOnboardingSteps().isPaymentMethod())
      deleteAllPaymentsMethods(userId);
  }
}