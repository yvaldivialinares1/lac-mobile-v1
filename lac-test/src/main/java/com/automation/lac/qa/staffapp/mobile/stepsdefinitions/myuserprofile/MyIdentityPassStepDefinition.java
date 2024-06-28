package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.myuserprofile;

import com.automation.lac.qa.staffapp.mobile.questions.myuserprofile.MyIdentityQuestions;
import io.cucumber.java.en.Then;

public class MyIdentityPassStepDefinition {
  private final MyIdentityQuestions myIdentityQuestions = new MyIdentityQuestions();

  /**
   * User sees the QR code image and informative message
   */
  @Then("the user sees the QR code image and informative message")
  public void validateMyIdentityPassPanel() {
    myIdentityQuestions.validateMyIdentityPassPanel();
  }
}
