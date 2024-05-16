package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import com.automation.lac.qa.fanapp.mobile.questions.EducationalQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.identitypass.MyIdentityPassEducationalTask;
import io.cucumber.java.en.And;

public class EducationalStepsDefinition {

  EducationalQuestions educationalQuestions = new EducationalQuestions();

  protected MyIdentityPassEducationalTask myIdentityPassEducationalTask
    = new MyIdentityPassEducationalTask();

  /**
   * Valid string values:
   * "Game Face ID"
   * "Age Verification"
   * "Payment Method"
   * "Identity Pass"
   * "Mini Accounts"
   */
  @And("^the user is able to see the educational for "
    + "(Game Face ID|Age Verification|Payment Method|Identity Pass|Mini Accounts)$")
  public void validateEducationalScreen(String reminderName) {
    educationalQuestions.validateEducationalScreen(reminderName);
  }
}
