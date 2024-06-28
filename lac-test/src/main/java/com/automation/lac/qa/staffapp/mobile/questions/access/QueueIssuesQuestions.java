package com.automation.lac.qa.staffapp.mobile.questions.access;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.ADULT_INTUIT_DOME_ACCOUNT;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.DEVICE_ID;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.LICENCE_PLATE;
import static com.automation.lac.qa.staffapp.mobile.enums.QueueEventType.INCORRECT_GARAGE;
import static com.automation.lac.qa.staffapp.mobile.enums.QueueEventType.NO_PARKING_TICKET;
import static com.automation.lac.qa.staffapp.mobile.enums.QueueEventType.PLATE_NOT_PROPERLY_READ;
import static com.automation.lac.qa.staffapp.mobile.enums.QueueEventType.UNKNOWN_PLATE;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.tasks.LicensePlateApiTask;
import com.automation.lac.qa.staffapp.mobile.enums.QueueEventType;
import com.automation.lac.qa.staffapp.mobile.screens.access.components.EventCardComponent;
import com.automation.lac.qa.staffapp.mobile.tasks.access.QueueIssuesTask;
import java.util.Map;

public class QueueIssuesQuestions {

  private static final String LABEL_ATTRIBUTE = "label";
  private final QueueIssuesTask queueIssuesTask = new QueueIssuesTask();
  private final Map<QueueEventType, String> primaryBtnTitlesMap =
    Map.of(NO_PARKING_TICKET, "BUY TICKETS",
      INCORRECT_GARAGE, "VIEW FAN PROFILE",
      PLATE_NOT_PROPERLY_READ, "IDENTIFY",
      UNKNOWN_PLATE, "REGISTER");

  /**
   * validate that parking event card contains all expected attributes.
   */
  public void validateParkingEventCardIsProperlyDisplayed(QueueEventType type) {
    final String deviceId = getTestContext().get(DEVICE_ID);
    final LicensePlateDto licensePlate = getTestContext().get(LICENCE_PLATE);
    final String state = LicensePlateApiTask.getStateName(licensePlate.getState());
    EventCardComponent parkingEventCard;

    if (type.equals(PLATE_NOT_PROPERLY_READ)) {
      parkingEventCard = queueIssuesTask.getEventCardById(deviceId);
    } else {
      parkingEventCard =
        queueIssuesTask.getEventCardByLicencePlate(licensePlate.getLicensePlate());
    }

    getSoftAssert().assertEquals(
      parkingEventCard.getIssueCardTitle().getAttribute(LABEL_ATTRIBUTE),
      type.getValue(), "Event card title value is " + type.getValue());
    getSoftAssert().assertFalse(
      parkingEventCard.getIssueCardTime().getAttribute(LABEL_ATTRIBUTE).isBlank(),
      "Issue card time value is not blank");
    getSoftAssert().assertEquals(
      parkingEventCard.getIssueCardDeviceId().getAttribute(LABEL_ATTRIBUTE),
      deviceId, "Event card device id value is " + deviceId);
    getSoftAssert().assertEquals(
      parkingEventCard.getBtnPrimary().getAttribute(LABEL_ATTRIBUTE), primaryBtnTitlesMap.get(type),
      "Event card primary button value is " + primaryBtnTitlesMap.get(type));

    if (type.equals(NO_PARKING_TICKET) || type.equals(UNKNOWN_PLATE)) {
      getSoftAssert().assertEquals(
        parkingEventCard.getIssueCardPlateValues().get(2).getAttribute(LABEL_ATTRIBUTE),
        state, "Event card state value is " + state);
    } else if (type.equals(INCORRECT_GARAGE)) {
      final IntuitDomeAccountDto accountDto = getTestContext().get(ADULT_INTUIT_DOME_ACCOUNT);
      final String fanName = accountDto.getFirstname() + " " + accountDto.getLastname();
      getSoftAssert().assertEquals(
        parkingEventCard.getBtnFanInformation().getAttribute(LABEL_ATTRIBUTE),
        fanName, "Event fan name value is " + fanName);
      getSoftAssert().assertEquals(
        parkingEventCard.getIssueCardPlateValues().get(2).getAttribute(LABEL_ATTRIBUTE),
        state, "Event card state value is " + state);
    }
  }

  /**
   * validate that there is no displayed queue events and expected message is displayed.
   */
  public void validateThereIsNoCurrentIssuesInThisView() {
    getSoftAssert().assertEquals(
      queueIssuesTask.getNoCurrentIssuesInThisViewMessage().getAttribute(LABEL_ATTRIBUTE),
      "NO CURRENT ISSUES IN THIS VIEW",
      "No current issues is displayed in this view");
  }

  /**
   * validate the issue is finished and removed from the queue.
   */
  public void validateIssueFinishedAndRemovedFromQueue() {
    getSoftAssert().assertEquals(
      queueIssuesTask.getBannerMessageText(),
      "The issue has been finished and removed from queue.",
      "The issue has been finished and removed from queue. message is displayed");
    validateThereIsNoCurrentIssuesInThisView();
  }
}
