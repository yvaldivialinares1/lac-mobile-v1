package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.LAST_OTP_DATE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.fanapp.mobile.tasks.phoneverification.PhoneVerificationTask;
import io.cucumber.java.en.And;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneVerificationStepsDefinition {

  private final PhoneVerificationTask phoneVerificationTask = new PhoneVerificationTask();

  /**
   * Complete the phone verification flow
   */
  @And("the user completes the phone verification")
  public void theUserCompletesThePhoneVerification() {
    LocalDateTime utcNow = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC+3"));
    getTestContext().set(LAST_OTP_DATE.name(), utcNow);
    phoneVerificationTask.retrieveManagementPhoneOtp(getTestContext().get(USER_INFO.name()));
  }
}