package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.access;

import com.automation.lac.qa.staffapp.mobile.tasks.access.AttendIssueHeaderTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class AttendIssueHeaderStepDefinitions {

  private final AttendIssueHeaderTask attendIssueHeaderTask =
    new AttendIssueHeaderTask();

  /**
   * employee leave issue from attend issue screen.
   */
  @When("the user leaves issue from attend issue screen")
  public void employeeLeaveIssueFromAttendIssueScreen() {
    attendIssueHeaderTask
      .tapLeaveIssueButton()
      .acceptAlert();
  }

  /**
   * employee finish issue from attend issue screen.
   */
  @And("the user selects finish issue from attend issue screen")
  public void employeeSelectFinishIssueFromAttendIssueScreen() {
    attendIssueHeaderTask.tapFinishIssueButton();
  }
}
