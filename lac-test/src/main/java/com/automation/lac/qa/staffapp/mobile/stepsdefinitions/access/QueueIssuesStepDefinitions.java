package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.access;

import static com.automation.lac.qa.staffapp.constants.ContextConstants.DEVICE_ID;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.mobile.tasks.access.QueueIssuesTask;
import io.cucumber.java.en.And;

public class QueueIssuesStepDefinitions {

  private final QueueIssuesTask queueIssuesTask = new QueueIssuesTask();

  /**
   * tap issue card primary button
   */
  @And("the user taps issue card primary button")
  public void tapIssueCardPrimaryButton() {
    queueIssuesTask.tapEventCardPrimaryButton(getTestContext().get(DEVICE_ID));
  }
}
