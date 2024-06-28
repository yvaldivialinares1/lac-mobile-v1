package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.faker.AleatoryData.createRandomInfo;
import static com.automation.lac.qa.fanapp.api.models.CredentialsFile.Account;
import static com.automation.lac.qa.fanapp.api.models.CredentialsFile.getValidCredentials;
import static com.automation.lac.qa.fanapp.api.tasks.identity.IdentityTask.deleteVehiclesAndTeammates;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONCERT_FAN;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.IS_CARD_ADDED;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.SELECTED_THEME;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.allure.AllureLogger;
import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.userinfo.AccountInfo;
import com.automation.lac.qa.fanapp.api.tasks.identity.CreateAnAccountTask;
import com.automation.lac.qa.fanapp.mobile.questions.HomeQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.commons.AssuranceTask;
import com.automation.lac.qa.fanapp.mobile.tasks.commons.CommonTask;
import com.automation.lac.qa.fanapp.mobile.tasks.developersettings.DeveloperSettingsTask;
import com.automation.lac.qa.fanapp.mobile.tasks.gamefaceid.GameFaceIdEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.home.HomeTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.home.WelcomeHomeTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.CreateAccountEndingTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.identitypass.MyIdentityPassEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.modals.GoCashlessModalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyAccountSettingsTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyLoggedProfileTask;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentmethods.AddPaymentMethodTask;
import com.automation.lac.qa.fanapp.mobile.tasks.paymentsmethod.AddPaymentMethodEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.phoneverification.PhoneVerificationTask;
import com.automation.lac.qa.fanapp.mobile.tasks.phoneverification.VerifyPhoneNumberTask;
import com.automation.lac.qa.fanapp.mobile.utils.NavigationUtils;
import com.automation.lac.qa.utils.CustomException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.qameta.allure.Step;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonsStep {

  private final CreateAccountEndingTasks createAccountEndingTasks = new CreateAccountEndingTasks();
  private final MyAccountSettingsTasks myAccountSettingsTasks = new MyAccountSettingsTasks();
  private final MyLoggedProfileTask myLoggedProfileTasks = new MyLoggedProfileTask();
  private final GoCashlessModalTask goCashlessModalTask = new GoCashlessModalTask();
  private final HomeQuestions homeQuestions = new HomeQuestions();
  private final HomeTasks homeTasks = new HomeTasks();
  private final WelcomeHomeTask welcomeTasks = new WelcomeHomeTask();
  private final CommonTask commonTask = new CommonTask();
  private final AssuranceTask assuranceTask = new AssuranceTask();
  private final DeveloperSettingsTask developerSettingsTask = new DeveloperSettingsTask();
  private final VerifyPhoneNumberTask verifyPhoneNumberTask = new VerifyPhoneNumberTask();
  private final PhoneVerificationTask phoneVerificationTask = new PhoneVerificationTask();
  private final GameFaceIdEducationalTask gameFaceIdEduTask = new GameFaceIdEducationalTask();
  private final AddPaymentMethodEducationalTask payEduTask = new AddPaymentMethodEducationalTask();
  private final MyIdentityPassEducationalTask idPassEduTask = new MyIdentityPassEducationalTask();
  private final AddPaymentMethodTask addPaymentMethodTask = new AddPaymentMethodTask();
  private final PhoneVerificationStepsDefinition phoneVerificationStepsDefinition =
    new PhoneVerificationStepsDefinition();

  /**
   * These are the possible variations:
   * Given an adult who has personal information generated
   * Given a young_adult who has personal information generated
   * Given a minor_adult who has personal information generated
   */
  @Given("^the user is (?:a|an) (minor_adult|young_adult|adult) who has "
    + "personal information generated( and is located in (Texas|Illinois))?$")
  public void theUserHasTheInformation(String userType, String location) {
    UserInfo userInfo = createRandomInfo(FanType.fromString(userType));
    getTestContext().set(USER_INFO.name(), userInfo);
    getTestContext().set("userType", userType);
    if (AllureLogger.ENV.equalsIgnoreCase("qa")) {
      assuranceTask.clickOnCancelButton();
    }
  }

  /**
   * Sets up the user's account status and credentials based on the provided parameters.
   * These are the possible variations:
   * Given the user has a Clipper account
   * Given the user doesn't have a Clipper account
   * Given the user doesn't have a Clipper account but is registered on the NBA
   * (use credentials.json)
   * Given the user doesn't have a Clipper account but is registered on the NBA(full data)
   * Given the user doesn't have a Clipper account but is registered on the NBA(partial data)
   *
   * @param hasClipperAccount Indicates whether the user has a Clippers account.
   * @param isRegisteredOnNba Indicates whether the user is registered on the NBA.
   * @param nbaData           Indicates the completeness of the NBA account data.
   */
  @Given("^the user (has|doesn't have) a Clippers account( but is registered on the NBA)?"
    + "( with full data| with partial data)?$")
  public void theUserHasOrDoesNotHaveAClipperAccount(String hasClipperAccount,
                                                     String isRegisteredOnNba, String nbaData) {
    boolean hasClipper = "has".equals(hasClipperAccount);
    boolean isRegisteredNba = " but is registered on the NBA".equals(isRegisteredOnNba);
    boolean nbaAccountData = " with full data".equals(nbaData);
    boolean partialNbaData = " with partial data".equals(nbaData);
    UserInfo userInfo = getTestContext().get(USER_INFO.name());
    AccountInfo accountInfo = userInfo.getAccountInfo();

    if (hasClipper) {
      Account account = getValidCredentials("clipper");
      setAccountInfo(accountInfo, account.getMail(), account.getPassword());
      userInfo.setAccountInfo(accountInfo);
    }
    if (isRegisteredNba && (nbaAccountData || partialNbaData)) {
      CreateAnAccountTask.registerNbaAccount(userInfo, nbaAccountData);
    } else if (!nbaAccountData && isRegisteredNba) {
      Account account = getValidCredentials("nba");
      setAccountInfo(accountInfo, account.getMail(), account.getPassword());
      userInfo.setAccountInfo(accountInfo);
    }
    getTestContext().set(USER_INFO.name(), userInfo);
  }

  /**
   * Navigates the user to the My Payments screen.
   */
  @And("the user navigates to my payments")
  public void theUserNavigatesToMyPayments() {
    homeTasks.goToMyProfileFromHome();
    myLoggedProfileTasks.goToMyPayments();
  }

  /**
   * Navigates the user to the My Vehicle screen.
   */
  @And("the user navigates to My Vehicles")
  public void theUserNavigatesToMyVehicles() {
    homeTasks.goToMyProfileFromHome();
    myLoggedProfileTasks.goToMyVehicles();
  }

  /**
   * Changes the user interface to the Concert Fan view.
   */
  @And("the user changes the view to Concert Fan")
  public void theUserChangesTheViewToConcertFan() {
    homeTasks.changeToTheConcertFanTheme();
    getTestContext().set(SELECTED_THEME.name(), CONCERT_FAN.name());
  }

  /**
   * Complete account creation and goes Home Screen with all modals dismissed
   */
  @And("the user completes the registration process and is on the home screen")
  public void theUserCompletesTheRegistrationProcessAndIsOnTheHomeScreen() {
    createAccountEndingTasks.clickTakeMeToIntuitDome();
    verifyPhoneNumberTask.handlePhoneNumberVerificationAfterOnboarding();
    handlingPhoneOtpValidationAndOnboardingWhenRequested();

    if (verifyPhoneNumberTask.isVerifyPhoneNumberScreenDisplayed()
      || phoneVerificationTask.isPhoneNumberValidationScreenDisplayed(5)) {
      throw new CustomException("Phone OTP was requested for a 3rd time");
    } else {
      if (!(boolean) getTestContext().get(IS_CARD_ADDED.name()))
        goCashlessModalTask.clickOnRemindMeLater();
      homeQuestions.checkHomeScreenIsDisplayed();
    }
  }

  @And("the user navigates to Mini Accounts")
  public void theUserNavigatesToMiniAccountsFromMyProfile() {
    myLoggedProfileTasks.goToMyAccountSettings();
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

  @And("the user navigates to the home screen from any part of the profile screen")
  public void theUserNavigatesToHomeFromAnyPartOfTheProfileScreen() {
    homeTasks.goBackUntilHomeScreenIsDisplayed();
  }

  /**
   * Navigates back until reach the expected screen
   *
   * @param screen screen to reach
   */
  @And("^the user navigates back to "
    + "(Home|My Profile|My Account Settings|My Identity|My Payments)?$")
  public void theUserNavigatesBackHome(String screen) {
    commonTask.navigateBack(screen);
  }

  @And("the user skip account login")
  public void theUserSkipLogin() {
    welcomeTasks.skipWelcomeHome();
  }

  @And("the user continue with purchase after account creation")
  public void theUserContinueWithTicketPurchase() {
    createAccountEndingTasks.clickContinuePurchase();
  }

  /**
   * Sets the email, password, and confirms password in the provided AccountInfo object.
   *
   * @param accountInfo The AccountInfo object to update.
   * @param email       The email to set.
   * @param password    The password to set (also sets confirm password).
   */
  private void setAccountInfo(AccountInfo accountInfo, String email, String password) {
    accountInfo.setEmail(email);
    accountInfo.setPassword(password);
    accountInfo.setConfirmPassword(password);
  }

  @And("the user navigates to {string} from {string}")
  public void theUserNavigatesToFrom(String to, String from) {
    NavigationUtils.navigate(from, to);
  }

  /**
   * Set Phone OTP Code and complete all onboarding steps as required
   */
  @Step("Handle OTP validation and onboarding if required after account creation")
  public void handlingPhoneOtpValidationAndOnboardingWhenRequested() {
    if (phoneVerificationTask.isPhoneNumberValidationScreenDisplayed(5)) {
      phoneVerificationStepsDefinition.theUserCompletesThePhoneVerification();
      gameFaceIdEduTask.skipGameFaceEducationalScreen();
      idPassEduTask.skipMyIdentityPassEducationalScreen();
      if ((boolean) getTestContext().get(IS_CARD_ADDED.name())) {
        payEduTask.clickAddPaymentMethod();
        addPaymentMethodTask.addValidCard();
      } else {
        payEduTask.skipAddPaymentMethodEducationalScreen();
      }
      createAccountEndingTasks.clickTakeMeToIntuitDome();
    }
  }

  /**
   * Finalizes the registration process by completing the specified stages.
   * Activates developer settings for the provided stages and then transitions through
   * the onboarding stages. Ensures that the stages list is not empty.
   *
   * @param stages A list of strings representing the stages to complete.
   * @throws IllegalArgumentException if the stages list is null.
   */
  @And("the user finalizes the registration process by completing the following stages")
  public void finalizeTheRegistrationProcessByCompletingTheFollowingSteps(List<String> stages) {
    if (stages.stream().allMatch(Objects::nonNull)) {
      developerSettingsTask.activateMocks(stages);
      NavigationUtils.onboardingStagesTransition(stages);
    } else {
      NavigationUtils.onboardingStagesTransition(stages);
    }
  }

  @And("the user basic information is removed")
  public void removeUserInformation() {
    deleteVehiclesAndTeammates("20dc9360-063f-4f34-b6b4-69c70327852b");
  }
}