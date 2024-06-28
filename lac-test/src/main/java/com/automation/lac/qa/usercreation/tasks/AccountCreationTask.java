package com.automation.lac.qa.usercreation.tasks;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.ACCOUNT_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.LAST_OTP_DATE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PHONE_VERIFICATION_RESULT;
import static com.automation.lac.qa.putsbox.PutsBoxApi.getOtpCodeFromEmails;
import static com.automation.lac.qa.usercreation.utils.UserCreationBuilder.getCreationResult;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.retryRestartingMobileApp;
import static io.qameta.allure.model.Status.FAILED;
import static io.qameta.allure.model.Status.PASSED;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.fanapp.mobile.questions.EducationalQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.home.WelcomeHomeTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.AddressInformationTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.CheckYourEmailTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.PersonalInformationTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.SetPasswordTask;
import com.automation.lac.qa.fanapp.mobile.tasks.identitycreateanaccount.StartYourJourneyTask;
import com.automation.lac.qa.fanapp.mobile.tasks.initialpermissions.TurnOnNotificationTask;
import com.automation.lac.qa.fanapp.mobile.tasks.phoneverification.PhoneVerificationTask;
import io.qameta.allure.Allure;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AccountCreationTask {

  private final TurnOnNotificationTask turnOnNotificationTask = new TurnOnNotificationTask();
  private final WelcomeHomeTask welcomeHomeTask = new WelcomeHomeTask();
  private final StartYourJourneyTask startYourJourneyTask = new StartYourJourneyTask();
  private final CheckYourEmailTask checkYourEmailTask = new CheckYourEmailTask();
  private final PersonalInformationTask personalInformationTask = new PersonalInformationTask();
  private final AddressInformationTask addressInformationTask = new AddressInformationTask();
  private final SetPasswordTask setPasswordTask = new SetPasswordTask();
  private final PhoneVerificationTask phoneVerificationTask = new PhoneVerificationTask();
  private final EducationalQuestions educationalQuestions = new EducationalQuestions();

  /**
   * Complete the AccountCreation and the PhoneValidation flows
   */
  public void createAccountAndCompletePhoneValidation(UserInfo userInfo) {
    welcomeHomeTask.createAnAccount().liveTheFullExperience()
      .completeFullEducationalExperience();
    startYourJourneyTask.enterEmailAddress(userInfo.getAccountInfo().getEmail());

    var getOtp = getOtpCodeFromEmails(userInfo.getAccountInfo().getEmailLocalPart());

    checkYourEmailTask.manageEmailOtpCode(getOtp);
    personalInformationTask.completePersonalInformation(userInfo.getPersonalInfo());
    addressInformationTask.manageAddressInformation(userInfo.getAddressInfo());

    LocalDateTime utcNow = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC+3"));
    getTestContext().set(LAST_OTP_DATE.name(), utcNow);

    setPasswordTask.setAPassword();
    phoneVerificationTask.retrieveManagementPhoneOtp(userInfo);
    if (getCreationResult(PHONE_VERIFICATION_RESULT.name()) == null)
      getTestContext().set(PHONE_VERIFICATION_RESULT.name(),
        educationalQuestions.isGameFaceEducationalScreenVisible() ? PASSED : FAILED);
    if (getCreationResult(ACCOUNT_CREATION_RESULT.name()) != PASSED)
      getTestContext().set(ACCOUNT_CREATION_RESULT.name(), PASSED);
  }

  /**
   * Allows the app to be in a correct state for creating the next user
   */
  public void restartTheAppAndLogout() {
    retryRestartingMobileApp();
    try {
      turnOnNotificationTask.grantLacPermission();
    } catch (Exception e) {
      Allure.step(e.getMessage(), FAILED);
    }
  }
}