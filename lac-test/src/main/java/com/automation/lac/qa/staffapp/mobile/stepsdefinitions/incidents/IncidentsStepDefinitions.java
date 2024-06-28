package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.incidents;

import static com.automation.lac.qa.pages.MobileBaseScreen.isIpad;
import static com.automation.lac.qa.staffapp.mobile.enums.SkipExceptionMessageType.IOS_PLATFORM_NOT_SUPPORTED;

import com.automation.lac.qa.staffapp.mobile.questions.home.HomeQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.incidents.IncidentsQuestions;
import com.automation.lac.qa.staffapp.mobile.tasks.home.HomeTask;
import com.automation.lac.qa.staffapp.mobile.tasks.incidents.IncidentsTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.testng.SkipException;

@Slf4j
public class IncidentsStepDefinitions {

  private final HomeTask homeTask = new HomeTask();
  private final IncidentsTask incidentsTask = new IncidentsTask();
  private final IncidentsQuestions incidentsQuestions = new IncidentsQuestions();
  private final HomeQuestions homeQuestions = new HomeQuestions();

  @And("notice an emergency incident into the venue")
  public void noticeAnEmergencyIncidentIntoTheVenue() {
    //TODO call API for have the type of incidents
    log.warn("Call the API for GET the type of incidents");
  }

  /**
   * Tap on emergency incident icon
   */
  @When("the user taps on the emergency incident icon")
  public void theUserTapsOnTheEmergencyIncidentIcon() {
    if (!isIpad()) {
      throw new SkipException(IOS_PLATFORM_NOT_SUPPORTED.getMessage());
    }
    homeTask.getNavigationHeaderComponent().clickOnEmergencyIncidentIcon();
  }

  @Then("the user should see the emergency incident module opened")
  public void theUserSeesTheEmergencyIncidentModuleOpened() {
    incidentsQuestions.isTheIncidentBannerDisplayed();
    incidentsQuestions.areVisibleTheIncidentTypes();
  }

  @When("the user chooses the type of incident {string}")
  public void theUserChoosesTheTypeOfIncident(String incidentType) {
    incidentsTask.clickOnIncidentType(incidentType);
  }

  @Then("the user sees a form with prefilled data")
  public void theUserSeesAFormWithPrefilledData() {
    incidentsQuestions.isIncidentFormVisible();
    incidentsQuestions.areFieldsPrefilled();
  }

  @When("the user completes the mandatory fields")
  public void theUserCompletesTheMandatoryFields() {
    //TODO mandatory fields are completed automatically
    log.warn("The fields are being completed automatically");
  }

  @Then("the create incident button is enabled")
  public void theCreateIncidentButtonIsEnabled() {
    incidentsQuestions.isCreateIncidentButtonEnabled();
  }

  @When("the user taps on create incident button")
  public void theUserTapsOnCreateIncidentButton() {
    incidentsTask.clickOnCreateIncidentButton();
  }
}
