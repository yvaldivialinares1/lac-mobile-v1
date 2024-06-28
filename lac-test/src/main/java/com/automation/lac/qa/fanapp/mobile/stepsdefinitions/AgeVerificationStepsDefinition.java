package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;

import com.automation.lac.qa.fanapp.mobile.tasks.ageverification.AgeVerificationEducationalTask;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgeVerificationStepsDefinition {
  private final AgeVerificationEducationalTask ageVerificationEducationalTask =
    new AgeVerificationEducationalTask();

  /**
   * Skips age verification
   */
  @And("the user skips the age verification")
  public void theUserSkipTheAgeVerification() {
    //TODO: remove conditional and else condition upon the mock support is in place for iOS.
    if (isAndroid()) {
      if (isTheElementVisible(ageVerificationEducationalTask.getBtnVerifyMyAge(), 10)) {
        ageVerificationEducationalTask.skipAgeVerificationEducationalScreen();
      }
    } else {
      log.info("iOS mock is not supported for now, therefore skipping this step.");
    }
  }

}