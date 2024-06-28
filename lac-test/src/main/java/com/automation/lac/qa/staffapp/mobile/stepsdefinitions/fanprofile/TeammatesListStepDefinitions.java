package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.fanprofile;

import com.automation.lac.qa.staffapp.mobile.questions.fanprofile.TeammatesListQuestions;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.TeamMatesListTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class TeammatesListStepDefinitions {

  private final TeamMatesListTask teamMatesListTask = new TeamMatesListTask();
  private final TeammatesListQuestions teammatesListQuestions = new TeammatesListQuestions();

  @Then("the user sees the teammates list of the {string} account type properly displayed")
  public void theFanProfileVehiclesListIsProperlyDisplayed(String fanAccountType) {
    teammatesListQuestions.validateIfFanProfileTeammatesListIsProperlyDisplayed(fanAccountType);
  }

  @And("the user selects the random teammate note from {string} account teammates list")
  public void selectRandomTeamMateListNote(String fanAccountType) {
    teamMatesListTask.selectRandomTeamMateListNote(fanAccountType);
  }
}