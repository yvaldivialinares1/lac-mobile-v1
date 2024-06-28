package com.automation.lac.qa.staffapp.mobile.tasks.access;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitUntilAlertIsVisible;

import com.automation.lac.qa.staffapp.mobile.screens.access.components.AttendIssueHeaderComponent;

public class AttendIssueHeaderTask extends AttendIssueHeaderComponent {

  private static final String LABEL_ATTRIBUTE = "label";

  /**
   * return licence plate value.
   *
   * @return String
   */
  public String getLicencePlateTitleValue() {
    return getLicencePlateTitle().getAttribute(LABEL_ATTRIBUTE);
  }

  /**
   * return state value.
   *
   * @return String
   */
  public String getStateTitleValue() {
    return getStateTitle().getAttribute(LABEL_ATTRIBUTE);
  }

  /**
   * tap LEAVE ISSUE button.
   *
   * @return AttendIssueHeaderTask
   */
  public AttendIssueHeaderTask tapLeaveIssueButton() {
    click(getBtnLeaveIssue(), getBtnLeaveIssue().getAttribute(LABEL_ATTRIBUTE));
    return this;
  }

  /**
   * accept alert.
   *
   * @return AttendIssueHeaderTask
   */
  public AttendIssueHeaderTask acceptAlert() {
    waitUntilAlertIsVisible(5).accept();
    return this;
  }

  /**
   * tap FINISH ISSUE button.
   *
   * @return AttendIssueHeaderTask
   */
  public AttendIssueHeaderTask tapFinishIssueButton() {
    click(getBtnFinishIssue(), getBtnFinishIssue().getAttribute(LABEL_ATTRIBUTE));
    return this;
  }
}
