package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.access;

import static com.automation.lac.qa.staffapp.constants.ContextConstants.DEVICE_ID;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.GARAGE_CATEGORY;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.enums.GarageCategoryType;
import com.automation.lac.qa.staffapp.api.tasks.ParkingAccessControlApiTask;
import com.automation.lac.qa.staffapp.mobile.enums.QueueEventType;
import com.automation.lac.qa.staffapp.mobile.questions.QueueIssuesQuestion;
import com.automation.lac.qa.staffapp.mobile.tasks.access.QueueIssuesTask;
import com.automation.lac.qa.staffapp.mobile.tasks.access.SelectDeviceTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

public class ParkingAccessStepDefinitions {

  private final SecureRandom random = new SecureRandom();
  private final QueueIssuesTask queueIssuesTask = new QueueIssuesTask();
  private final SelectDeviceTask selectDeviceTask = new SelectDeviceTask();
  private final QueueIssuesQuestion queueIssuesQuestion = new QueueIssuesQuestion();

  @And("there is a {string} queue notification for selected parking line")
  public void thereIsParkingQueueNotificationForSelectedDevice(String queueEventType) {
    QueueEventType type = QueueEventType.valueOf(queueEventType);
    ParkingAccessControlApiTask.createParkingAccessValidationEvent(type);
  }

  /**
   * select the random line under garage parking queue
   */
  @And("employee select the random line under garage parking queue")
  public void selectTheRandomLineUnderGarageParkingQueue() {
    queueIssuesTask.clickSelectLinesButton();

    GarageCategoryType garageCategory = getTestContext().get(GARAGE_CATEGORY);
    List<String> lanes =
      ParkingAccessControlApiTask.getParkingLines(garageCategory.getParking()).getLanes();

    String lineId = lanes.get(random.nextInt(lanes.size() - 1));
    selectDeviceTask
      .selectDevicesById(Collections.singletonList(lineId))
      .confirmSelectedDevices();
    getTestContext().set(DEVICE_ID, lineId);

    queueIssuesTask.waitForSpinnerDisappear();
  }

  /**
   * validate specific parking queue notification
   */
  @Then("employee see {string} parking queue notification for selected parking line")
  public void employeeSeeSpecificParkingQueueNotificationForSelectedParkingLine(String eventType) {
    QueueEventType type = QueueEventType.valueOf(eventType);
    queueIssuesQuestion.validateParkingEventCardIsProperlyDisplayed(type);
  }
}
