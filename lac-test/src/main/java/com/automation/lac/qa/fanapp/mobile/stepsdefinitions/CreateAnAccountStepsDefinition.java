package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.LAST_OTP_DATE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.onlinesim.OnlineSimApi.getLastOtpMessageDate;
import static com.automation.lac.qa.putsbox.PutsBoxApi.getOtpCodeFromEmails;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.fanapp.mobile.questions.WelcomeHomeQuestion;
import com.automation.lac.qa.fanapp.mobile.tasks.home.WelcomeHomeTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.AddressInformationTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.CheckYourEmailTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.PersonalInformationTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.SetPasswordTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.StartYourJourneyTask;
import com.automation.lac.qa.fanapp.mobile.tasks.initialpermissions.TurnOnNotificationTask;
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
  private final UserInfo userInfo = getTestContext().get(USER_INFO.name());

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
    welcomeHomeTask.createAnAccount().liveTheFullExperience().completeFullEducationalExperience();
    startYourJourneyTask.enterEmailAddress(userInfo.getAccountInfo().getEmail());
  }

  /**
   * Completes account registration process until completing address information
   */
  @And("the user completes the account registration data with his email address")
  public void theUserCompletesTheAccountRegistrationDataWithHisEmailAddress() {
    var getOtp = getOtpCodeFromEmails(userInfo.getAccountInfo().getEmailLocalPart());
    checkYourEmailTask.manageEmailOtpCode(getOtp);
    personalInformationTask.completePersonalInformation(userInfo.getPersonalInfo());
    addressInformationTask.manageAddressInformation(userInfo.getAddressInfo());

    //TODO: The next line should moved to Phone Validation flow after teh app implements the changes
    getTestContext().set(LAST_OTP_DATE.name(),
      getLastOtpMessageDate(userInfo.getPhoneInfo().getPhoneOtpCountryCode(),
        userInfo.getPersonalInfo().getPhoneNumber(),
        userInfo.getPhoneInfo().getPhoneOtpSenderCode()));

    setPasswordTask.setAPassword();
  }
}