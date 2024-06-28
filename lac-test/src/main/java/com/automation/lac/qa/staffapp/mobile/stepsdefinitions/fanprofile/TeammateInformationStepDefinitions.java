package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.fanprofile;

import com.automation.lac.qa.staffapp.mobile.questions.fanprofile.TeammateInformationQuestions;
import io.cucumber.java.en.Then;

public class TeammateInformationStepDefinitions {

  private final TeammateInformationQuestions teammateInformationQuestions =
    new TeammateInformationQuestions();

  @Then("the user sees the teammate information properly displayed")
  public void teamMateInformationIsProperlyDisplayed() {
    teammateInformationQuestions.validateIfTeamMateInformationIsProperlyDisplayed();
  }
}