package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.fanapp.mobile.tasks.phoneverification.PhoneVerificationTask;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneVerificationStepsDefinition {

  private final PhoneVerificationTask phoneVerificationTask = new PhoneVerificationTask();

  /**
   * Complete the phone verification flow
   */
  @And("the user completes the phone verification")
  public void theUserCompletesThePhoneVerification() {
    phoneVerificationTask.retrieveManagementPhoneOtp(getTestContext().get(USER_INFO.name()));
  }
}