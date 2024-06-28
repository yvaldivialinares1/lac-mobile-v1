package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.access;

import static com.automation.lac.qa.pages.MobileBaseScreen.isIpad;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.ADULT_INTUIT_DOME_ACCOUNT;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.DEVICE_ID;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.GARAGE_CATEGORY;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.LICENCE_PLATE;
import static com.automation.lac.qa.staffapp.mobile.enums.SkipExceptionMessageType.IOS_PLATFORM_NOT_SUPPORTED;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.enums.GarageCategoryType;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.tasks.LicensePlateApiTask;
import com.automation.lac.qa.staffapp.api.tasks.ParkingAccessControlApiTask;
import com.automation.lac.qa.staffapp.api.tasks.TicketingApiTask;
import com.automation.lac.qa.staffapp.mobile.enums.QueueEventType;
import com.automation.lac.qa.staffapp.mobile.questions.access.AttendIssueHeaderQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.access.AttendIssueNoParkingTicketQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.access.QueueIssuesQuestions;
import com.automation.lac.qa.staffapp.mobile.tasks.access.AccessTask;
import com.automation.lac.qa.staffapp.mobile.tasks.access.AttendIssuePlateNotReadTask;
import com.automation.lac.qa.staffapp.mobile.tasks.access.QueueIssuesTask;
import com.automation.lac.qa.staffapp.mobile.tasks.access.SelectDeviceTask;
import com.automation.lac.qa.staffapp.mobile.tasks.common.FanSearchTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.testng.SkipException;

@Slf4j
public class ParkingAccessStepDefinitions {

  private final SecureRandom random = new SecureRandom();
  private final QueueIssuesTask queueIssuesTask = new QueueIssuesTask();
  private final SelectDeviceTask selectDeviceTask = new SelectDeviceTask();
  private final QueueIssuesQuestions queueIssuesQuestion = new QueueIssuesQuestions();
  private final AccessTask accessTask = new AccessTask();
  private final AttendIssueHeaderQuestions attendIssueHeaderQuestions =
    new AttendIssueHeaderQuestions();
  private final FanSearchTask fanSearchTask = new FanSearchTask();
  private final AttendIssuePlateNotReadTask attendIssuePlateNotReadTask
    = new AttendIssuePlateNotReadTask();
  private final AttendIssueNoParkingTicketQuestions attendIssueNoParkingTicketQuestions
    = new AttendIssueNoParkingTicketQuestions();

  @And("there is a {string} queue notification for selected parking line")
  public void thereIsParkingQueueNotificationForSelectedDevice(String queueEventType) {
    QueueEventType type = QueueEventType.valueOf(queueEventType);
    ParkingAccessControlApiTask.createParkingAccessValidationEvent(type);
  }

  /**
   * assign valid parking ticket to a fan account.
   */
  @And("the fan account has a valid parking ticket")
  public void fanAccountHasTheValidParkingTicket() {
    TicketingApiTask.assignValidParkingTicket();
  }

  /**
   * select the random parking category and line under garage parking queue.
   */
  @And("the user connects to the parking queue line")
  public void selectTheRandomLineUnderGarageParkingQueue() {
    GarageCategoryType garageCategory = accessTask.selectRandomParkingCategoryGarage();
    getTestContext().set(GARAGE_CATEGORY, garageCategory);

    queueIssuesTask.clickSelectLinesButton();
    List<String> lanes =
      ParkingAccessControlApiTask.getParkingLines(garageCategory.getParking()).getLanes();

    String lineId = lanes.get(random.nextInt(lanes.size() - 1));
    selectDeviceTask
      .selectDevicesById(Collections.singletonList(lineId))
      .confirmSelectedDevices();
    getTestContext().set(DEVICE_ID, lineId);
    queueIssuesTask.getProgressBarComponent().waitForSpinnerDisappear();
  }

  /**
   * validate specific parking queue notification
   */
  @Then("the user sees {string} parking queue notification for selected parking line")
  public void employeeSeeSpecificParkingQueueNotificationForSelectedParkingLine(String eventType) {
    QueueEventType type = QueueEventType.valueOf(eventType);
    queueIssuesQuestion.validateParkingEventCardIsProperlyDisplayed(type);
  }

  /**
   * validate employee see the vehicle added to account but fan has no parking ticket
   */
  @Then("the user sees the vehicle added to account but fan has no parking ticket")
  public void employeeSeeVehicleAddedToAccountButFanHasNoParkingTicket() {
    attendIssueNoParkingTicketQuestions.validateVehicleIsAddedButFanHasNoParkingTicket();
  }

  /**
   * manually identify the licence plate when plate not properly read
   */
  @When("the user manually identifies the licence plate when plate not properly read")
  public void employeeManuallyIdentifyTheLicencePlateWhenPlateNotProperlyRead() {
    if (!isIpad()) {
      throw new SkipException(IOS_PLATFORM_NOT_SUPPORTED.getMessage());
    }
    String deviceId = getTestContext().get(DEVICE_ID);
    final LicensePlateDto licensePlate = getTestContext().get(LICENCE_PLATE);
    final String state = LicensePlateApiTask.getStateName(licensePlate.getState());
    log.info("Current licence plate state testing value is {}", state);
    queueIssuesTask.tapEventCardPrimaryButton(deviceId);
    attendIssuePlateNotReadTask
      .enterVehicleLicensePlate(licensePlate.getLicensePlate())
      .selectVehicleState(state);
  }

  /**
   * select a fan account to register the vehicle when plate not properly read
   */
  @And("the user selects a fan account to register the vehicle when plate not properly read")
  public void employeeSelectFanAccountToRegisterTheVehicleWhenPlateNotProperlyRead() {
    if (!isIpad()) {
      throw new SkipException(IOS_PLATFORM_NOT_SUPPORTED.getMessage());
    }
    IntuitDomeAccountDto accountDto = getTestContext().get(ADULT_INTUIT_DOME_ACCOUNT);
    fanSearchTask
      .enterInputInManualSearchField(accountDto.getEmail())
      .selectFanAccountByEmail(accountDto.getEmail());
  }

  /**
   * confirm register the vehicle for a selected fan account when plate not properly read
   */
  @And("the user registers the vehicle for a selected fan account when plate not properly read")
  public void employeeRegisterTheVehicleForASelectedFanAccountWhenPlateNotProperlyRead() {
    attendIssuePlateNotReadTask.tapPrimaryButton();
  }

  /**
   * validate that there is no displayed queue events and expected message is displayed.
   */
  @Then("the user sees that there is no current issues in the parking queue")
  public void employeeSeeThatThereIsNoCurrentIssuesInTheParkingQueue() {
    queueIssuesQuestion.validateThereIsNoCurrentIssuesInThisView();
  }

  /**
   * validate that attend NO PARKING TICKET issue screen is properly displayed.
   */
  @Then("the user sees attend NO PARKING TICKET issue screen is properly displayed")
  public void employeeSeeAttendNoParkingTicketIssueScreenIsProperlyDisplayed() {
    if (!isIpad()) {
      throw new SkipException(IOS_PLATFORM_NOT_SUPPORTED.getMessage());
    }
    attendIssueHeaderQuestions.validateAttendNoParkingTicketIssueHeader();
  }

  /**
   * validate the issue is finished and removed from the queue.
   */
  @Then("the user sees the issue was finished and removed from the queue message")
  public void validateIssueFinishedAndRemovedFromQueue() {
    queueIssuesQuestion.validateIssueFinishedAndRemovedFromQueue();
  }
}
