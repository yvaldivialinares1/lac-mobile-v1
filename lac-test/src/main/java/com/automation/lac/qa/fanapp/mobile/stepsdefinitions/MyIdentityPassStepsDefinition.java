package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.mobile.enums.ReminderNames.IDENTITY_PASS;

import com.automation.lac.qa.fanapp.mobile.tasks.identitypass.MyIdentityPassEducationalTask;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyLoggedProfileTask;
import io.cucumber.java.en.And;

public class MyIdentityPassStepsDefinition {

  private MyIdentityPassEducationalTask myIdentityPassEducationalTask =
    new MyIdentityPassEducationalTask();
  private final MyLoggedProfileTask myLoggedProfileTasks = new MyLoggedProfileTask();

  @And("the user skip the identity pass registration")
  public void theUserSkipTheGameFaceRegistration() {
    myIdentityPassEducationalTask.skipMyIdentityPassEducationalScreen();
  }

  /**
   * Triggers the action where the user taps on the identity pass reminder.
   * It navigates to the reminder card and clicks on the reminder for "Identity Pass".
   */

  @And("the user taps on the identity pass reminder")
  public void theUserTapsOnTheIdentityPassReminder() {
    myLoggedProfileTasks.goToReminderCard(IDENTITY_PASS.getName());
    myLoggedProfileTasks.clickOnReminder(IDENTITY_PASS.getName());
  }

}