package com.automation.lac.qa.staffapp.mobile.questions.incidents;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static org.apache.logging.log4j.util.Strings.EMPTY;

import com.automation.lac.qa.staffapp.mobile.screens.incidents.IncidentsScreen;
import io.qameta.allure.Step;

public class IncidentsQuestions extends IncidentsScreen {

  /**
   * Validate if the banner is being displayed
   */
  @Step("Validate if the incident banner is displayed")
  public void isTheIncidentBannerDisplayed() {
    getSoftAssert().assertTrue(getIncidentsBannerComponent().getLblTitle().isDisplayed(),
      "Incident banner is displayed");
  }

  /**
   * Validate if the banner has incident types
   */
  @Step("Validate if into the banner are incident types")
  public void areVisibleTheIncidentTypes() {
    getSoftAssert().assertFalse(getIncidentsBannerComponent().getLstIncidentsButtons().isEmpty(),
      "Incident types are visible");
  }

  /**
   * Validate if incident form is visible
   */
  @Step("Validate form incident is visible")
  public void isIncidentFormVisible() {
    getSoftAssert().assertTrue(getBtnBack().isDisplayed(), "Incident form is visible");
  }

  /**
   * Validate if the form fields are being prefilled
   */
  @Step("Validate form prefilled fields")
  public void areFieldsPrefilled() {
    getSoftAssert().assertNotEquals(getFrmInputDepartment().getText(), EMPTY,
      "Department is filled");
    getSoftAssert().assertNotEquals(getFrmInputIncidentType().getText(), EMPTY,
      "Incident type is filled");
    getSoftAssert().assertNotEquals(getFrmDropdownLocation().getText(), EMPTY,
      "Location and section is filled");
    getSoftAssert().assertNotEquals(getFrmDropdownStatus().getText(), EMPTY,
      "Status is filled");
    getSoftAssert().assertNotEquals(getFrmDropdownPriority().getText(), EMPTY,
      "Priority is empty");
    getSoftAssert().assertNotEquals(getFrmInputReportedVia().getText(), EMPTY,
      "Reported via is filled");
    getSoftAssert().assertNotEquals(getFrmInputDepartmentReporting().getText(), EMPTY,
      "Department reporting is filled");
  }

  /**
   * Validate if the create incident button is enabled
   */
  @Step("Validate if the create incident button is enabled")
  public void isCreateIncidentButtonEnabled() {
    getSoftAssert().assertTrue(getBtnCreate().isEnabled(), "Create button is enabled");
  }
}
