package com.automation.lac.qa.staffapp.mobile.tasks.incidents;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import com.automation.lac.qa.staffapp.mobile.screens.incidents.IncidentsScreen;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;

public class IncidentsTask extends IncidentsScreen {

  /**
   * Click on incident type
   *
   * @param incidentType value for select the incident type
   */
  @Step("Tap on incident type {incidentType}")
  public void clickOnIncidentType(String incidentType) {
    getIncidentsBannerComponent().getLstIncidentsButtons().stream().filter(
        incidentTypes -> containsIgnoreCase(incidentTypes.getAttribute("label"), incidentType))
      .findFirst().orElseThrow(() -> new CustomException("Incident type not found")).click();
  }

  /**
   * Click on create incident button
   */
  public void clickOnCreateIncidentButton() {
    click(getBtnCreate(), "Create button");
  }
}
