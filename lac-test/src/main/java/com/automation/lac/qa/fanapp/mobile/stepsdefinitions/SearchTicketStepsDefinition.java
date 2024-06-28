package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_COUNT;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_GARAGE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_PARKING_TYPE_STATUS;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_RESPONSE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_COUNT;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_IS_ACCESSIBLE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_NAME;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_RESPONSE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_STATUS;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TIMESTAMP;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TYPE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.IS_PARKING_SELECTED;
import static com.automation.lac.qa.fanapp.mobile.enums.TicketAvailability.AVAILABLE;
import static com.automation.lac.qa.fanapp.mobile.enums.TicketAvailability.SOLD_OUT;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static java.util.Objects.isNull;

import com.automation.lac.qa.fanapp.api.models.tickets.ParkingTicketResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.TicketResponse;
import com.automation.lac.qa.fanapp.mobile.enums.TicketType;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.TicketFilterTask;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.TicketListTask;
import com.automation.lac.qa.utils.CustomException;
import io.cucumber.java.en.And;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.testng.SkipException;


@Slf4j
public class SearchTicketStepsDefinition {


  private final TicketFilterTask ticketFilterTask = new TicketFilterTask();
  private final TicketListTask ticketListTask = new TicketListTask();


  /**
   * Search for the event/game from ticket list screen
   */
  @And("^the user looks for the ticket")
  public void theUserLooksForTheTicket() {
    TicketType ticket = getTestContext().get(SELECTED_TICKET_TYPE);
    TicketResponse ticketResponse = getTestContext().get(SELECTED_TICKET_RESPONSE);
    int id;
    String name;
    long timeStamp = 0;
    switch (ticket) {
      case EVENT -> {
        id = ticketResponse.getEventId();
        name = ticketResponse.getPerformer();
        ticketListTask.goToEventsTab();
        timeStamp = ticketResponse.getEventTimeStamp();
      }
      case GAME -> {
        id = ticketResponse.getGameId();
        name = ticketResponse.getOpponent();
        ticketListTask.goToClippersTab();
        timeStamp = ticketResponse.getEventTimeStamp();
      }
      case PARKING -> {
        List<ParkingTicketResponse> parkingTicketList
          = getTestContext().get(SELECTED_PARKING_TICKET_RESPONSE);
        ParkingTicketResponse parkingTicketResponse = parkingTicketList.get(0);
        id = parkingTicketResponse.getParkingId();
        name = parkingTicketResponse.getDescription();
        ticketListTask.goToParkingTab();
        timeStamp = parkingTicketResponse.getEventTimeStamp();
      }
      default -> throw new CustomException("There is no option to select defined as " + ticket);
    }
    log.info("Selected Ticket Id {} with game name {}", id, name);
    getTestContext().set(SELECTED_TICKET_NAME, name);
    getTestContext().set(SELECTED_TICKET_TIMESTAMP, timeStamp);
    ticketFilterTask.searchTicket(name);
  }

  /**
   * Search for the Game/Event/Club ticket from API according to provided configuration
   * <br> please set below properties in test context to get the customized ticket from the API
   * <br><br>SELECTED_TICKET_COUNT : number of seats to purchase
   * <br>SELECTED_TICKET_IS_ACCESSIBLE : "with" or "without"
   * <br>SELECTED_PARKING_TICKET_COUNT : number of parking to purchase
   * <br>SELECTED_PARKING_TICKET_GARAGE : "any" or number of garage required for parking
   * <br>SELECTED_PARKING_TICKET_SOLD_OUT : Empty or "with sold out" or "without sold out"
   *
   * @param ticketType           "game", "event"
   * @param clubType             "red", "green", "gold", "Orange", "Any"
   * @param seatCount            number of seats to purchase
   * @param accessible           "with" or "without"
   * @param parkingTicketCount   number of parking to purchase
   * @param parkingTicketGarage  "any" or number of garage required for parking
   * @param parkingTicketSoldOut Empty or "with sold out" or "without sold out"
   */
  @And("^the user needs (red|green|gold|orange|Any)?(?: club ticket for )?(event|game) "
    + "with (\\d+) seat (with|without) accessibility and (\\d+) parking with "
    + "(any|\\d+) garage( with sold out| without sold out)?$")
  public void fetchTicketWithSeatAccessibilityParkingGarageSoldOut(String clubType,
                                                                   String ticketType,
                                                                   int seatCount,
                                                                   String accessible,
                                                                   int parkingTicketCount,
                                                                   String parkingTicketGarage,
                                                                   String parkingTicketSoldOut) {
    TicketType ticket = TicketType.valueOf(ticketType.toUpperCase());
    getTestContext().set(SELECTED_TICKET_TYPE, ticket);
    getTestContext().set(SELECTED_TICKET_COUNT, seatCount);
    getTestContext().set(SELECTED_TICKET_IS_ACCESSIBLE, accessible);
    getTestContext().set(SELECTED_PARKING_TICKET_COUNT, parkingTicketCount);
    getTestContext().set(SELECTED_PARKING_TICKET_GARAGE, parkingTicketGarage);
    getTestContext().set(SELECTED_PARKING_TICKET_PARKING_TYPE_STATUS,
      parkingTicketSoldOut != null ? parkingTicketSoldOut.trim() : "");
    TicketResponse ticketResponse = ticketFilterTask.getTicketByFeature(clubType, ticket);

    if (isNull(ticketResponse)) {
      throw new SkipException("No ticket is available to purchase with given parameters");
    }
    log.info("Selected Ticket : " + ticketResponse);
    getTestContext().set(SELECTED_TICKET_RESPONSE, ticketResponse);
  }


  @And("the user access the option to Buy Tickets")
  public void userBuyTicketFromTicketList() {
    ticketListTask.scrollToTicketAndClick();
  }

  /**
   * Fetches parking passes with specific seat count, availability, garage, and event type criteria.
   *
   * @param parkingSeatCount     The number of parking seats.
   * @param parkingTicketSoldOut availability status ("sold out", "any", or "all").
   * @param parkingTicketGarage  garage criteria ("any" or a specific number).
   * @param ticketType           type of the event ("event" or "game").
   * @param ticketSoldOutStatus  ticket availability status ("available" or "sold out").
   */
  @And("^the user needs (\\d+) parking seat with (sold out|any|all) availability"
    + " and (any|\\d+) garage for (event|game) with (available|sold out)?$")
  public void fetchParkingWithSeatAccessibilityGarageForSoldOut(int parkingSeatCount,
                                                                String parkingTicketSoldOut,
                                                                String parkingTicketGarage,
                                                                String ticketType,
                                                                String ticketSoldOutStatus) {
    final TicketType ticket = TicketType.valueOf(ticketType.toUpperCase());
    getTestContext().set(IS_PARKING_SELECTED.name(), true);
    getTestContext().set(SELECTED_TICKET_TYPE, ticket);
    getTestContext().set(SELECTED_PARKING_TICKET_COUNT, parkingSeatCount);
    parkingTicketSoldOut =
      parkingTicketSoldOut.trim().equalsIgnoreCase("all") ? "without sold out" : "with sold out";
    getTestContext().set(SELECTED_PARKING_TICKET_PARKING_TYPE_STATUS, parkingTicketSoldOut);
    getTestContext().set(SELECTED_PARKING_TICKET_GARAGE, parkingTicketGarage);
    ticketSoldOutStatus =
      String.valueOf(
        ticketSoldOutStatus.trim().equalsIgnoreCase("sold out") ? SOLD_OUT.getName()
          : AVAILABLE.getName());
    getTestContext().set(SELECTED_TICKET_STATUS, ticketSoldOutStatus);
    TicketResponse ticketResponse = ticketFilterTask.getTicketByFeature(null, ticket);
    if (isNull(ticketResponse)) {
      throw new CustomException("No ticket is available to purchase with given parameters");
    }
    log.info("Selected Ticket is: " + ticketResponse);
    getTestContext().set(SELECTED_TICKET_RESPONSE, ticketResponse);
  }
}