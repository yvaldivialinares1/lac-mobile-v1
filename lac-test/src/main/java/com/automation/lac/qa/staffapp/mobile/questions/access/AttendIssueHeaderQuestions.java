package com.automation.lac.qa.staffapp.mobile.questions.access;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.LICENCE_PLATE;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.tasks.LicensePlateApiTask;
import com.automation.lac.qa.staffapp.mobile.tasks.access.AttendIssueHeaderTask;

public class AttendIssueHeaderQuestions {

  private final AttendIssueHeaderTask attendIssueHeaderTask =
    new AttendIssueHeaderTask();

  /**
   * tap issue card primary button
   */
  public void validateAttendNoParkingTicketIssueHeader() {
    final LicensePlateDto licensePlate = getTestContext().get(LICENCE_PLATE);
    final String state = LicensePlateApiTask.getStateName(licensePlate.getState());
    getSoftAssert().assertEquals(
      attendIssueHeaderTask.getLicencePlateTitleValue(),
      licensePlate.getLicensePlate(),
      "Licence plate number value is " + licensePlate.getLicensePlate());

    getSoftAssert().assertEquals(
      attendIssueHeaderTask.getStateTitleValue(),
      state, "Licence plate state value is " + state);
  }
}
