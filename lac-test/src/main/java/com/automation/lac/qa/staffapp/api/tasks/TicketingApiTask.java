package com.automation.lac.qa.staffapp.api.tasks;

import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
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
   * Setup mocked response of ticketing mock service.
   *
   * @param ticketingId   String indicating intuitDomeAccount ticketingId.
   * @param result        String indicating expected result.
   * @param resultMessage String indicating expected result message.
   * @param location      String indicating expected garage location.
   * @param ticketType    String indicating expected type of ticket.
   */
  public void setUpTicketingMockResponse(String ticketingId,
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
  public String generateTicketMasterUserId(IntuitDomeAccountDto intuitDomeAccount) {

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
}
