package com.automation.lac.qa.assertions;

import static com.automation.lac.qa.allure.AllureLogger.attachScreenShot;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import lombok.extern.slf4j.Slf4j;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

@Slf4j
public class CustomSoftAssert extends SoftAssert {

  /**
   * Logs and reports a successful assertion to Allure.
   *
   * @param assertCommand The assertion command that was successfully executed.
   */
  @Override
  public void onAssertSuccess(IAssert<?> assertCommand) {
    super.onAssertSuccess(assertCommand);
    String successMessage = "ASSERTION PASSED: " + assertCommand.getMessage();
    log.info(successMessage);
    Allure.step(successMessage, Status.PASSED);
  }

  /**
   * Logs a failed assertion, captures a screenshot, and reports the failure to Allure.
   *
   * @param assertCommand The assertion command that failed.
   * @param ex            The AssertionError thrown by the failed assertion.
   */
  @Override
  public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
    super.onAssertFailure(assertCommand, ex);
    String failureMessage =
      "ASSERTION FAILED: " + assertCommand.getMessage() + " - " + ex.getMessage();
    log.warn(failureMessage);
    attachScreenShot("assertScreenShoot");
    Allure.step(failureMessage, Status.FAILED);
  }
}
