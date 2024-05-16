package com.automation.lac.qa.staffapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.api.models.cms.CountryCodesResponse.State;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.DEVICE_ID;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.INTUIT_DOME_ACCOUNT_1;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.LICENCE_PLATE;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.STATE;
import static com.automation.lac.qa.staffapp.mobile.enums.QueueEventType.INCORRECT_GARAGE;
import static com.automation.lac.qa.staffapp.mobile.enums.QueueEventType.NO_PARKING_TICKET;
import static com.automation.lac.qa.staffapp.mobile.enums.QueueEventType.UNKNOWN_PLATE;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.mobile.enums.QueueEventType;
import com.automation.lac.qa.staffapp.mobile.screens.access.components.EventCardComponent;
import com.automation.lac.qa.staffapp.mobile.tasks.access.QueueIssuesTask;
import com.automation.lac.qa.utils.CustomException;
import java.util.Map;

public class QueueIssuesQuestion {

  private final QueueIssuesTask queueIssuesTask = new QueueIssuesTask();
  private final Map<QueueEventType, String> primaryBtnTitlesMap =
    Map.of(NO_PARKING_TICKET, "BUY TICKETS",
      INCORRECT_GARAGE, "VIEW FAN PROFILE",
      UNKNOWN_PLATE, "REGISTER");

  /**
   * validate that parking event card contains all expected attributes
   */
  public void validateParkingEventCardIsProperlyDisplayed(QueueEventType type) {
    final String deviceId = getTestContext().get(DEVICE_ID);
    final State state = getTestContext().get(STATE);
    final LicensePlateDto licensePlate = getTestContext().get(LICENCE_PLATE);

    EventCardComponent noParkingTicketCard =
      queueIssuesTask.getEventCardByLicencePlate(licensePlate.getLicensePlate());

    getSoftAssert().assertEquals(
      noParkingTicketCard.getIssueCardTitle().getAttribute("label"),
      type.getValue(), "Event card title value is " + type.getValue());
    getSoftAssert().assertFalse(
      noParkingTicketCard.getIssueCardTime().getAttribute("label").isBlank(),
      "Issue card time value is not blank");
    getSoftAssert().assertEquals(
      noParkingTicketCard.getIssueCardDeviceId().getAttribute("label"),
      deviceId, "Event card device id value is " + deviceId);
    getSoftAssert().assertEquals(
      noParkingTicketCard.getBtnPrimary().getAttribute("label"), primaryBtnTitlesMap.get(type),
      "Event card primary button value is " + primaryBtnTitlesMap.get(type));

    switch (type) {
      case NO_PARKING_TICKET, UNKNOWN_PLATE:
        getSoftAssert().assertEquals(
          noParkingTicketCard.getIssueCardPlateValues().get(2).getAttribute("label"),
          state.getName(), "Event card state value is " + state.getName());
        break;
      case INCORRECT_GARAGE:
        final IntuitDomeAccountDto accountDto = getTestContext().get(INTUIT_DOME_ACCOUNT_1);
        final String fanName = accountDto.getFirstname() + " " + accountDto.getLastname();
        getSoftAssert().assertEquals(
          noParkingTicketCard.getBtnFanInformation().getAttribute("label"),
          fanName, "Event fan name value is " + fanName);
        getSoftAssert().assertEquals(
          noParkingTicketCard.getIssueCardPlateValues().get(2).getAttribute("label"),
          state.getName(), "Event card state value is " + state.getName());
        break;
      default:
        throw new CustomException("Unexpected value: " + type);
    }
    getSoftAssert().assertAll();
  }
}
