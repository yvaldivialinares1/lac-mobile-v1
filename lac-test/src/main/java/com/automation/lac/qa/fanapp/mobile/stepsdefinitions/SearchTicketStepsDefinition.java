package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_ACCESSIBLE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_COUNT;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_NAME;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TIMESTAMP;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TYPE;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static java.util.Objects.isNull;

import com.automation.lac.qa.fanapp.api.models.tickets.TicketResponse;
import com.automation.lac.qa.fanapp.api.tasks.TicketTask;
import com.automation.lac.qa.fanapp.mobile.enums.TicketType;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.TicketFilterTask;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.TicketListTask;
import com.automation.lac.qa.utils.CustomException;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class SearchTicketStepsDefinition {

  private final TicketFilterTask ticketFilterTask = new TicketFilterTask();
  private final TicketListTask ticketListTask = new TicketListTask();


  /**
   * Search for the event/game from ticket list screen and click on the event
   * And the user looks for the event/game to buy XX seat with accessibility
   * And the user looks for the event/game to buy XX seat without accessibility
   */
  @And("^the user looks for the (event|game) to buy (\\d+) seat (with|without) accessibility")
  public void theUserLooksForTheEventToBuySeatAccessibility(String ticketType, int seatCount,
                                                            String accessible) {
    TicketType ticket = TicketType.valueOf(ticketType.toUpperCase());
    getTestContext().set(SELECTED_TICKET_TYPE, ticket);
    getTestContext().set(SELECTED_TICKET_COUNT, seatCount);
    getTestContext().set(SELECTED_TICKET_ACCESSIBLE, accessible);
    TicketResponse ticketResponse =
      TicketTask.getAvailableTicket(ticket);

    if (isNull(ticketResponse))
      throw new CustomException("No ticket is available to purchase with given parameters");

    int id;
    String name;
    if (ticket.equals(TicketType.EVENT)) {
      id = ticketResponse.getEventId();
      name = ticketResponse.getPerformer();
      ticketListTask.goToEventsTab();
    } else {
      id = ticketResponse.getGameId();
      name = ticketResponse.getOpponent();
      ticketListTask.goToClippersTab();
    }
    log.info("Selected Ticket Id : " + id);
    getTestContext().set(SELECTED_TICKET_NAME, name);
    getTestContext().set(SELECTED_TICKET_TIMESTAMP, ticketResponse.getEventTimeStamp());
    ticketFilterTask.searchTicket(name);
  }

  @And("the user access the option to Buy Tickets")
  public void userBuyTicketFromTicketList() {
    ticketListTask.scrollToTicketAndClick();
  }
}