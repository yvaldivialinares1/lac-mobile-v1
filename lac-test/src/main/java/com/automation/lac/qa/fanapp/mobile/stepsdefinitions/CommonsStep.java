package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.faker.AleatoryData.createRandomInfo;
import static com.automation.lac.qa.fanapp.api.models.CredentialsFile.getValidCredentials;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.IS_CARD_ADDED;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.userinfo.AccountInfo;
import com.automation.lac.qa.fanapp.api.models.CredentialsFile;
import com.automation.lac.qa.fanapp.mobile.questions.HomeQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.home.HomeTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.CreateAccountEndingTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.modals.ModalGoCashlessTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyAccountSettingsTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyLoggedProfileTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonsStep {

  private final CreateAccountEndingTasks createAccountEndingTasks = new CreateAccountEndingTasks();
  private final MyAccountSettingsTasks myAccountSettingsTasks = new MyAccountSettingsTasks();
  private final MyLoggedProfileTask myLoggedProfileTasks = new MyLoggedProfileTask();
  private final ModalGoCashlessTasks modalGoCashlessTasks = new ModalGoCashlessTasks();
  private final HomeQuestions homeQuestions = new HomeQuestions();
  private final HomeTasks homeTasks = new HomeTasks();

  /**
   * These are the possible variations:
   * Given an adult who has personal information generated
   * Given a young_adult who has personal information generated
   * Given a minor_adult who has personal information generated
   */
  @Given("^(?:A|An) (minor_adult|young_adult|adult) who has personal information generated")
  public void theUserHasTheInformation(String userType) {
    UserInfo userInfo = createRandomInfo(FanType.fromString(userType));
    getTestContext().set(USER_INFO.name(), userInfo);
  }

  /**
   * These are the possible variations:
   * Given The user has a Clipper account
   * Given The user doesn’t have a Clipper account
   * Given The user doesn’t have a Clipper account but is registered on the NBA
   */
  @Given("^The user (has|doesn’t have) a Clipper account( but is registered on the NBA)?$")
  public void theUserHasOrDoesNotHaveAClipperAccount(String hasClipperAccount,
                                                     String isRegisteredOnNba) {
    boolean hasClipper = "has".equals(hasClipperAccount);
    boolean isRegisteredNba = " but is registered on the NBA".equals(isRegisteredOnNba);
    UserInfo userInfo = getTestContext().get(USER_INFO.name());
    AccountInfo accountInfo = userInfo.getAccountInfo();

    if (hasClipper) {
      CredentialsFile.Account account = getValidCredentials();
      accountInfo.setEmail(account.getMail());
      accountInfo.setPassword(account.getPassword());
      accountInfo.setConfirmPassword(account.getPassword());
      userInfo.setAccountInfo(accountInfo);
    }
    if (isRegisteredNba) {
      log.warn("Implementation to register in NBA is in progress");
      //TODO create an implementation to register in NBA
    }
    getTestContext().set(USER_INFO.name(), userInfo);
  }

  /**
   * Navigates the user to the My Payments screen.
   */
  @And("the user navigates to my payments")
  public void theUserNavigatesToMyPayments() {
    homeTasks.goToMyPaymentsFromHome();
  }

  /**
   * Navigates the user to the My Vehicle screen.
   */
  @And("the user navigates to My Vehicles")
  public void theUserNavigatesToMyVehicles() {
    homeTasks.goToMyVehiclesFromHome();
  }

  /**
   * Changes the user interface to the Concert Fan view.
   */
  @And("the user changes the view to Concert Fan")
  public void theUserChangesTheViewToConcertFan() {
    homeTasks.changeToTheConcertFanTheme();
  }

  /**
   * Complete account creation and goes Home Screen with all modals dismissed
   */
  @And("the user completes the registration process and is on the home screen")
  public void theUserCompletesTheRegistrationProcessAndIsOnTheHomeScreen() {
    createAccountEndingTasks.clickTakeMeToIntuitDome();
    if (!Boolean.parseBoolean(getTestContext().get(IS_CARD_ADDED.name())))
      modalGoCashlessTasks.clickOnRemindMeLater();
    homeQuestions.checkHomeScreenIsDisplayed();
  }

  @And("the user navigates to Mini Accounts")
  public void theUserNavigatesToMiniAccountsFromMyProfile() {
    myLoggedProfileTasks.goToMyAccountSettingsFromMyProfile();
    myAccountSettingsTasks.clickOnMiniAccounts();
  }

  @And("the user taps on back button to return to the previous screen")
  public void theUserClicksOnBackButton() {
    myAccountSettingsTasks.clickOnBackButton();
  }

  @And("the user navigates to Home from Mini Accounts")
  public void theUserNavigatesToHomeFromMiniAccounts() {
    myAccountSettingsTasks.clickOnBackButton(2);
    myLoggedProfileTasks.clickBackOnMyProfile();
  }
}