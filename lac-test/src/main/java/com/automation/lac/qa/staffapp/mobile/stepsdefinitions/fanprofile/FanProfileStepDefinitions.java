package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.fanprofile;

import static com.automation.lac.qa.pages.MobileBaseScreen.isIpad;

import com.automation.lac.qa.staffapp.mobile.enums.FanProfileInformationOption;
import com.automation.lac.qa.staffapp.mobile.questions.fanprofile.FanProfileInformationQuestions;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.FanProfileInformationTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class FanProfileStepDefinitions {

  private final FanProfileInformationQuestions fanProfileInformationQuestions =
    new FanProfileInformationQuestions();
  private final FanProfileInformationTask fanProfileInformationTask =
    new FanProfileInformationTask();

  @And("the fan information screen is showed")
  public void theFanInformationScreenIsDisplayed() {
    fanProfileInformationQuestions.isFanProfileInformationDisplayed();
  }

  @And("the user sees the fan basic information of the {string} account type properly displayed")
  public void theFanBasicInformationIsProperlyDisplayed(String fanAccountType) {
    fanProfileInformationQuestions.isFanProfileBasicInformationProperlyDisplayed(fanAccountType);
  }

  /**
   * validate basic information and teammates section
   */
  @And("the user views the fan basic information and teammates section for {string}")
  public void viewsTheFanBasicInformationAndTeammates(String fanAccountType) {
    fanProfileInformationQuestions.isFanProfileBasicInformationProperlyDisplayed(fanAccountType);
    fanProfileInformationQuestions.isFanProfileTeammatesSectionProperlyDisplayed(
      fanAccountType);
  }

  @And("the user sees the fan teammates section of the {string} account type properly displayed")
  public void theFanTeammatesInformationIsProperlyDisplayed(String fanAccountType) {
    fanProfileInformationQuestions.isFanProfileTeammatesSectionProperlyDisplayed(
      fanAccountType);
  }

  /**
   * Tap fan information section to view details in case of iPhone platform.
   *
   * @param fanInfoSection String fan info section type
   */
  @When("the user views the fan {string} identity section")
  public void theUserTapsOnTheViewSpecificFanInformationSection(String fanInfoSection) {
    if (!isIpad()) {
      FanProfileInformationOption informationOption =
        FanProfileInformationOption.valueOf(fanInfoSection);
      fanProfileInformationTask.clickOnFanOptionButton(informationOption);
    }
  }

  /**
   * Tap the button to view fan teammates list.
   */
  @When("the user views fan teammates accounts list")
  public void theUserTapsOnTheViewFanTeammatesSection() {
    fanProfileInformationTask.tapSeeTeammatesAccountsList();
  }
}
