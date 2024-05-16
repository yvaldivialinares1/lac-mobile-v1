package com.automation.lac.qa.fanapp.api.tasks;

import static com.automation.lac.qa.fanapp.api.services.tickets.TicketService.getAccountSeatsList;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_ACCESSIBLE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_COUNT;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.fanapp.api.enums.ticketing.TimeFilter;
import com.automation.lac.qa.fanapp.api.models.tickets.AccountSeatsResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.SuggestedSeatsResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.TicketResponse;
import com.automation.lac.qa.fanapp.api.services.tickets.TicketService;
import com.automation.lac.qa.fanapp.mobile.enums.TicketType;
import java.security.SecureRandom;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@UtilityClass
public class TicketTask {
  private SecureRandom random = new SecureRandom();

  /**
   * get an available ticket for events/games filter the response by availability
   *
   * @return TicketResponse
   */
  public static TicketResponse getAvailableTicket(TicketType ticketType) {
    int seatCount = getTestContext().getOrDefault(SELECTED_TICKET_COUNT, 1);
    String accessible = getTestContext().getOrDefault(SELECTED_TICKET_ACCESSIBLE, "without");
    List<TicketResponse> tickets;
    if (ticketType.equals(TicketType.EVENT))
      tickets = getAllTicketsEvents();
    else
      tickets = getAllTicketsGames();
    tickets = tickets.stream().filter(
      ticket -> (ticket.isOnSale() && StringUtils.isEmpty(ticket.getHostEventId())
        && isTicketAvailable(ticketType, ticket, seatCount, accessible))
        || (!ticket.isOnSale() && ticket.getMapId() != null
        && isTicketAvailable(ticketType, ticket, seatCount, accessible))).toList();
    if (!tickets.isEmpty()) {
      return tickets.get(random.nextInt(tickets.size()));
    } else {
      return null;
    }
  }

  private static boolean isTicketAvailable(TicketType ticketType, TicketResponse ticket,
                                           int seatCount, String accessible) {
    boolean isAvailable = ticket.getAvailability() != null
      && (ticket.getAvailability().equals("FEW_SEATS")
      || ticket.getAvailability().equals("AVAILABLE"))
      && ticket.getMaximumAllowed() >= seatCount
      && ticket.getCurrentAvailability() >= seatCount
      && (!accessible.equalsIgnoreCase("with")
      || ticket.getCurrentAccessibleAvailability() >= seatCount);
    if (isAvailable) {
      SuggestedSeatsResponse suggestedSeatsResponse =
        getSuggestedSeatsForTicket(ticketType, ticket.getEventId(), seatCount,
          accessible.equalsIgnoreCase("with"));
      return suggestedSeatsResponse != null
        && suggestedSeatsResponse.getSuggestedTickets().stream().limit(5)
        .allMatch(suggestedTicket -> suggestedTicket.getPrice() > 0);
    }
    return false;
  }

  public static List<TicketResponse> getAllTicketsGames() {
    return TicketService.getTicketingGames();
  }

  public static List<TicketResponse> getAllTicketsEvents() {
    return TicketService.getTicketingEvents();
  }

  /**
   * It returns the suggested seats list for the given ticket
   *
   * @param ticketType      ticket type(game|event)
   * @param eventId         id of the ticket
   * @param totalTickets    number of seats to purchase
   * @param needsAccessible true if requires accessible seats
   * @return SuggestedSeatsResponse
   */
  public static SuggestedSeatsResponse getSuggestedSeatsForTicket(TicketType ticketType,
                                                                  int eventId,
                                                                  int totalTickets,
                                                                  boolean needsAccessible) {
    return ticketType.equals(TicketType.EVENT)
      ? TicketService.getSuggestedSeatsForEvent(eventId, totalTickets, needsAccessible) :
      TicketService.getSuggestedSeatsForGame(eventId, totalTickets, needsAccessible);
  }

  /**
   * get the actual Booked seat count
   */
  public static int getBookedSeats(int eventId) {
    AccountSeatsResponse accountSeatsListFromApi = getAccountSeatsList(eventId, TimeFilter.DEFAULT);
    if (accountSeatsListFromApi != null) {
      return accountSeatsListFromApi.tickets.stream()
        .flatMap(ticket -> ticket.seats.stream())
        .mapToInt(seat -> seat.numSeats)
        .sum();
    }
    return 0; // Default to 0 if no seats are booked.
  }
}