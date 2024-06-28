package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.common;

import com.automation.lac.qa.staffapp.mobile.questions.common.FanSearchQuestions;
import com.automation.lac.qa.staffapp.mobile.tasks.common.FanSearchTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FanSearchStepDefinitions {

  private final FanSearchTask fanSearchTask = new FanSearchTask();
  private final FanSearchQuestions fanSearchQuestions = new FanSearchQuestions();

  @And("the user fills the search bar with fan email {string}")
  public void fillsTheBarWithFanEmail(String fanEmail) {
    fanSearchTask.enterInputInManualSearchField(fanEmail);
  }

  @Then("the fan profile {string} is shown in the search results")
  public void theFanProfileIsShownInTheResults(String fanEmail) {
    fanSearchQuestions.isFanEmailShownInTheResults(fanEmail);
  }

  @When("the user taps on the fan note in the search results by email {string}")
  public void theUserTapsOnTheFanResultByEmail(String fanEmail) {
    fanSearchTask.selectFanAccountByEmail(fanEmail);
  }
}
