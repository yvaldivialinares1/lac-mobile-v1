package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.US;
import static com.automation.lac.qa.putsbox.PutsBoxApi.getOtpCodeFromEmails;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.fanapp.mobile.questions.WelcomeHomeQuestion;
import com.automation.lac.qa.fanapp.mobile.questions.identitycreateanaccount.AddressInformationQuestions;
import com.automation.lac.qa.fanapp.mobile.questions.identitycreateanaccount.PersonalInformationQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.home.WelcomeHomeTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.AddressInformationTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.CheckYourEmailTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.PersonalInformationTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.SetPasswordTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.StartYourJourneyTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.educationalexperience.CreateAccountEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.educationalexperience.FullExperienceEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.initialpermissions.TurnOnNotificationTask;
import com.automation.lac.qa.utils.CustomException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateAnAccountStepsDefinition {

  private final TurnOnNotificationTask turnOnNotificationTask = new TurnOnNotificationTask();
  private final WelcomeHomeTask welcomeHomeTask = new WelcomeHomeTask();
  private final WelcomeHomeQuestion welcomeHomeQuestion = new WelcomeHomeQuestion();
  private final StartYourJourneyTask startYourJourneyTask = new StartYourJourneyTask();
  private final CheckYourEmailTask checkYourEmailTask = new CheckYourEmailTask();
  private final PersonalInformationTask personalInformationTask = new PersonalInformationTask();
  private final AddressInformationTask addressInformationTask = new AddressInformationTask();
  private final SetPasswordTask setPasswordTask = new SetPasswordTask();
  private final FullExperienceEducationalTask fullExperienceEducationalTask =
    new FullExperienceEducationalTask();
  private final CreateAccountEducationalTask createAnAccountTask =
    new CreateAccountEducationalTask();
  private final PersonalInformationQuestions personalInformationQuestions =
    new PersonalInformationQuestions();
  private final AddressInformationQuestions addressInformationQuestions =
    new AddressInformationQuestions();

  /**
   * Sets the user to the Welcome Home screen after starting the app
   */
  @When("the user is on the Welcome Home screen")
  public void theUserIsOnTheWelcomeHomeScreen() {
    turnOnNotificationTask.grantLacPermission();
    welcomeHomeQuestion.checkWelcomeHomeScreenIsDisplayed();
  }

  /**
   * Begins account registrations process until CheckYourEmailScreen
   */
  @And("the user starts the account registration process")
  public void theUserStartsTheAccountRegistrationProcess() {
    UserInfo userInfo = getTestContext().get(USER_INFO.name());
    welcomeHomeTask.createAnAccount().liveTheFullExperience().completeFullEducationalExperience();
    startYourJourneyTask.enterEmailAddress(userInfo.getAccountInfo().getEmail());
  }

  /**
   * Completes account registration process until completing address information
   */
  @And("the user completes the account registration data with his"
    + " email address( during ticket purchase)?$")
  public void theUserCompletesTheAccountRegistrationDataWithHisEmailAddress(
    String ongoingTicketPurchase) {

    UserInfo userInfo = getTestContext().get(USER_INFO.name());
    var getOtp = getOtpCodeFromEmails(userInfo.getAccountInfo().getEmailLocalPart());
    checkYourEmailTask.manageEmailOtpCode(getOtp);
    boolean isOngoingTicketPurchase = " during ticket purchase".equals(ongoingTicketPurchase);

    if (!isOngoingTicketPurchase) {
      completeAccountRegistrationDuringOnboarding(userInfo);
    } else {
      completeAccountRegistrationDuringTicketPurchase(userInfo);
    }
  }


  /**
   * Begins account registrations process until CheckYourEmailScreen
   */
  @And("the user starts the account registration process with NBA credentials"
    + "( by Login| by Create An Account| by Login again)$")
  public void theUserStartsTheAccountRegistrationProcessNbaCredentials(String flow) {
    switch (flow) {
      case " by Login" -> fullExperienceEducationalTask.liveTheFullExperience()
        .completeFullEducationalExperience();
      case " by Login again" -> createAnAccountTask.completeFullEducationalExperience();
      case " by Create An Account" -> {
        UserInfo userInfo = getTestContext().get(USER_INFO.name());
        welcomeHomeTask.createAnAccount().liveTheFullExperience()
          .completeFullEducationalExperience();
        startYourJourneyTask.enterEmailAddress(userInfo.getAccountInfo().getEmail());
      }
      default -> throw new CustomException("The option is not valid.");
    }
  }

  /**
   * Completes account registration process until completing address information
   */
  @And("the NBA user completes the account registration with his email and"
    + "( all preloaded data| partial preloaded data)?$")
  public void theNbaUserCompletesTheAccountRegistrationDataWithHisEmailAddress(
    String nbaData) {
    boolean nbaAccountData = " all preloaded data".equals(nbaData);
    UserInfo userInfo = getTestContext().get(USER_INFO.name());
    if (nbaAccountData) {
      verifyPreloadedFields(true, userInfo);
      personalInformationTask.clickContinue();
      verifyPreloadedFields(false, userInfo);
      addressInformationTask.clickConfirmMyAccount();
    } else {
      verifyAndCompleteFields(userInfo, true);
      verifyAndCompleteFields(userInfo, false);
    }
  }

  /**
   * Complete account registration during onboarding
   *
   * @param userInfo UserInfo
   */
  public void completeAccountRegistrationDuringOnboarding(UserInfo userInfo) {
    personalInformationTask.completePersonalInformation(userInfo.getPersonalInfo());
    addressInformationTask.manageAddressInformation(userInfo.getAddressInfo());
    setPasswordTask.setAPassword();
  }

  /**
   * Complete account registration during ticket purchasing
   *
   * @param userInfo UserInfo
   */
  public void completeAccountRegistrationDuringTicketPurchase(UserInfo userInfo) {
    personalInformationTask.enterMandatoryFields(userInfo.getPersonalInfo());
    addressInformationTask.selectCountry(US.getValue());
    addressInformationTask.enterZipCode(userInfo.getAddressInfo());
    setPasswordTask.swipeToPasswordFields();
    setPasswordTask.completeConfirmAPassword();
    setPasswordTask.completeSetAPassword();
    personalInformationTask.clickContinue();
  }

  /**
   * Verifies preloaded fields and completes missing fields if necessary.
   *
   * @param userInfo       The user's information.
   * @param isPersonalInfo true for personal information screen, false for
   *                       address information screen.
   */
  private void verifyAndCompleteFields(UserInfo userInfo, boolean isPersonalInfo) {
    if (isPersonalInfo) {
      personalInformationQuestions.arePreloadedFields(false, userInfo);
      personalInformationTask.managePersonalInformationNbaAccount(userInfo.getPersonalInfo());
    } else {
      addressInformationQuestions.arePreloadedFields(false, userInfo.getAddressInfo());
      addressInformationTask.manageAddressInformationNbaAccount(userInfo.getAddressInfo());
    }
  }

  /**
   * Verifies preloaded fields without completing missing fields.
   *
   * @param isPersonalInfo true for personal information screen,
   *                       false for address information screen.
   */
  private void verifyPreloadedFields(boolean isPersonalInfo, UserInfo userInfo) {
    if (isPersonalInfo) {
      personalInformationQuestions.arePreloadedFields(true, userInfo);
    } else {
      addressInformationQuestions.arePreloadedFields(true, userInfo.getAddressInfo());
    }
  }
}