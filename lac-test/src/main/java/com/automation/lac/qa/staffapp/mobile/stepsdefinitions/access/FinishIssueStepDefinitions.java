package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.access;

import com.automation.lac.qa.staffapp.api.tasks.CmsApiTask;
import com.automation.lac.qa.staffapp.mobile.enums.QueueEventType;
import com.automation.lac.qa.staffapp.mobile.tasks.access.FinishIssueTask;
import io.cucumber.java.en.And;
import java.security.SecureRandom;
import java.util.List;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

public class FinishIssueStepDefinitions {

  private final SecureRandom random = new SecureRandom();
  private final Faker faker = new Faker();
  private final FinishIssueTask finishIssueTask = new FinishIssueTask();

  /**
   * employee complete form to finish specific issue.
   */
  @And("the user completes form to finish {string} issue")
  public void employeeCompleteFormToFinishIssue(String queueEventType) {
    QueueEventType type = QueueEventType.valueOf(queueEventType);
    List<String> options = CmsApiTask.getListOfFinishIssueOptionsFromCms(type);
    String option = options.get(random.nextInt(options.size()));
    finishIssueTask.selectOptionFromSendIssueToBoxOfficeDropdown(option);
    String message = faker.lorem().sentence(5);
    if (StringUtils.containsIgnoreCase(option, "other")) {
      finishIssueTask.typeIntoFinishIssueReasonInput(message);
    }
    finishIssueTask.confirmFinishIssueForm();
  }
}
