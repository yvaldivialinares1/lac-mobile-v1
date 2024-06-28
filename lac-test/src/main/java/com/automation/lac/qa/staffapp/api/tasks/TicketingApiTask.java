package com.automation.lac.qa.staffapp.api.tasks;

import static com.automation.lac.qa.staffapp.constants.ContextConstants.ADULT_INTUIT_DOME_ACCOUNT;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.GARAGE_CATEGORY;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.enums.GarageCategoryType;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.TicketingIdRequestDto;
import com.automation.lac.qa.staffapp.api.models.ticketing.MockRedeemRequestV2;
import com.automation.lac.qa.staffapp.api.models.ticketing.SeatRequest;
import com.automation.lac.qa.staffapp.api.models.ticketing.TicketMasterUserIdRequest;
import com.automation.lac.qa.staffapp.api.services.ticketing.TicketingService;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TicketingApiTask {

  /**
   * Prepare ticketing mock service with valid fan parking ticket.
   */
  public static void assignValidParkingTicket() {
    final GarageCategoryType garageCategory = getTestContext().get(GARAGE_CATEGORY);
    String tmUserId;
    IntuitDomeAccountDto account;
    account = getTestContext().get(ADULT_INTUIT_DOME_ACCOUNT);
    tmUserId = TicketingApiTask.generateTicketMasterUserId(account);
    TicketingApiTask.addTicketingIdToIntuitDomAccount(account.getId(), tmUserId);
    TicketingApiTask.setUpTicketingMockResponse(
      tmUserId,
      "SUCCESS",
      "",
      garageCategory.getParking(),
      "MAIN_ACCESS");
  }

  /**
   * Setup mocked response of ticketing mock service.
   *
   * @param ticketingId   String indicating intuitDomeAccount ticketingId.
   * @param result        String indicating expected result.
   * @param resultMessage String indicating expected result message.
   * @param location      String indicating expected garage location.
   * @param ticketType    String indicating expected type of ticket.
   */
  public static void setUpTicketingMockResponse(String ticketingId,
                                                String result,
                                                String resultMessage,
                                                String location,
                                                String ticketType) {

    SeatRequest tickets = SeatRequest.builder()
      .eventId(0)
      .section("A")
      .row("1")
      .seat("1")
      .build();
    List<SeatRequest> ticketsList = new ArrayList<>();
    ticketsList.add(tickets);
    MockRedeemRequestV2 request = MockRedeemRequestV2.builder()
      .result(result)
      .resultMessage(resultMessage)
      .redeemDateTime(0)
      .location(location)
      .eventId(0)
      .ticketType(ticketType)
      .tickets(ticketsList)
      .isDependent(false)
      .ticketingId(Integer.parseInt(ticketingId))
      .build();

    TicketingService.setUpTicketingMockResponseV2(request);
  }

  /**
   * Generate tmUserId for a fan account.
   *
   * @param intuitDomeAccount IntuitDomeAccountDto data.
   * @return String tmUserId value
   */
  public static String generateTicketMasterUserId(IntuitDomeAccountDto intuitDomeAccount) {

    TicketMasterUserIdRequest request = TicketMasterUserIdRequest.builder()
      .email(intuitDomeAccount.getEmail())
      .firstname(intuitDomeAccount.getFirstname())
      .lastname(intuitDomeAccount.getLastname())
      .dateOfBirth(intuitDomeAccount.getDateOfBirth())
      .phone(intuitDomeAccount.getPhone())
      .address1(intuitDomeAccount.getAddress1())
      .address2(intuitDomeAccount.getAddress2())
      .city(intuitDomeAccount.getCity())
      .state(intuitDomeAccount.getState())
      .country(intuitDomeAccount.getCountry())
      .zipCode(intuitDomeAccount.getZipcode())
      .build();
    return TicketingService.generateTicketMasterUserId(request).getTmUserId();
  }

  /**
   * Add ticket mater id to an existing fan account.
   *
   * @param accountId      String indicating the id of fan account.
   * @param ticketMasterId String indicating the ticket master id.
   */
  public static void addTicketingIdToIntuitDomAccount(String accountId, String ticketMasterId) {
    TicketingIdRequestDto request =
      TicketingIdRequestDto.builder()
        .ticketingId(ticketMasterId)
        .intuitDomeAccountId(accountId)
        .build();
    TicketingService.addTicketingIdToIntuitDomeAccount(request);
  }
}
