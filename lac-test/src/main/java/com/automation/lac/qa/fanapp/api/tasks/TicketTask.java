package com.automation.lac.qa.fanapp.api.tasks;

import static com.automation.lac.qa.fanapp.api.services.tickets.TicketService.getAccountSeatsList;
import static com.automation.lac.qa.fanapp.api.services.tickets.TicketService.getParkingPassAvailability;
import static com.automation.lac.qa.fanapp.api.services.tickets.TicketService.getParkingPassTicketListFor;
import static com.automation.lac.qa.fanapp.api.services.tickets.TicketService.getTicketsAccordingToAvailabilityWithParkingMatch;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_AVAILABILITY_RESPONSE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_COUNT;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_GARAGE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_PARKING_TYPE_STATUS;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_RESPONSE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_COUNT;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_IS_ACCESSIBLE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_STATUS;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TYPE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.fanapp.mobile.enums.ParkingType.ACCESSIBLE;
import static com.automation.lac.qa.fanapp.mobile.enums.ParkingType.ELECTRIC;
import static com.automation.lac.qa.fanapp.mobile.enums.ParkingType.STANDARD;
import static com.automation.lac.qa.fanapp.mobile.enums.TicketType.EVENT;
import static com.automation.lac.qa.fanapp.mobile.enums.TicketType.GAME;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.fanapp.api.enums.ticketing.TimeFilter;
import com.automation.lac.qa.fanapp.api.models.tickets.AccountSeatsResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.ClubListResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.ParkingAvailabilityResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.ParkingTicketResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.SuggestedSeatsResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.TicketResponse;
import com.automation.lac.qa.fanapp.api.services.tickets.TicketService;
import com.automation.lac.qa.fanapp.mobile.enums.TicketType;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@UtilityClass
public class TicketTask {

  private final SecureRandom random = new SecureRandom();

  /**
   * get an available ticket for purchase of events/games/clubs filter the response by availability.
   * @return TicketResponse
   */
  public static TicketResponse getAvailableTicket(TicketType ticketType) {
    return ticketType.equals(EVENT) ? getFilteredTicket(getAllTicketsEvents())
      : getFilteredTicket(getAllTicketsGames());
  }


  /**
   * get an available ticket for given club ticket type by availability using given precondition
   *
   * @return TicketResponse
   */

  public static TicketResponse getAvailableClubTicket(String clubType) {
    //ToDo: This method is implemented as of now any type of club ticket
    // Once we have specific club type ticket scenario, we will implement it
    TicketType ticketType = getTestContext().get(SELECTED_TICKET_TYPE);
    List<ClubListResponse.CmsData> clubList = new ArrayList<>(ticketType.equals(GAME)
      ? getClubList(GAME) : getClubList(EVENT));
    for (int i = clubList.size() - 1; i >= 0; i--) {
      int randomIndex = random.nextInt(clubList.size());
      ClubListResponse.CmsData club = clubList.get(randomIndex);
      log.info("Club id : " + clubList.get(randomIndex).getClubId());
      TicketResponse clubTicket =
          getFilteredTicket(getClubTickets(ticketType, club.getClubId()));
      if (clubTicket != null) {
        log.info("found available Club ticket");
        return clubTicket;
      }
      clubList.remove(randomIndex);
    }
    return null;
  }

  /**
   * get an available ticket for given ticket type by availability using given precondition
   *
   * @return TicketResponse
   */
  private static TicketResponse getFilteredTicket(List<TicketResponse> ticketList) {
    int seatCount = getTestContext().getOrDefault(SELECTED_TICKET_COUNT, 1);
    int parkingTicketCount = getTestContext().getOrDefault(SELECTED_PARKING_TICKET_COUNT, 0);
    String accessible = getTestContext().getOrDefault(SELECTED_TICKET_IS_ACCESSIBLE, "without");
    String parkingTicketGarage = getTestContext()
      .getOrDefault(SELECTED_PARKING_TICKET_GARAGE, "any");
    String parkingTicketSoldOut = getTestContext()
      .getOrDefault(SELECTED_PARKING_TICKET_PARKING_TYPE_STATUS, "");
    List<TicketResponse> tickets = new ArrayList<>(ticketList);
    for (int i = tickets.size() - 1; i >= 0; i--) {
      int randomIndex = random.nextInt(tickets.size());
      TicketResponse ticket = tickets.get(randomIndex);

      if (((ticket.isOnSale() && StringUtils.isEmpty(ticket.getHostEventId()))
        || (!ticket.isOnSale() && ticket.getMapId() != null))
        && isTicketAvailable(ticket, seatCount, accessible, parkingTicketCount,
        parkingTicketGarage, parkingTicketSoldOut)
        && ticket.getCmsData().getTransactionTimer() >= 30
        && ticket.getCmsData().getAlertTime() >= 15)
        return ticket;
      tickets.remove(randomIndex);
    }
    return null;
  }

  private static boolean isTicketAvailable(TicketResponse ticket,
                                           int seatCount, String accessible) {
    boolean isAvailable = ticket.getAvailability() != null
      && (getTestContext().get(SELECTED_TICKET_TYPE).equals(GAME)
      ? ticket.getOpponent() != null : ticket.getPerformer() != null)
      && (ticket.getAvailability().equals("FEW_SEATS")
      || ticket.getAvailability().equals("AVAILABLE"))
      && ticket.getMaximumAllowed() >= seatCount
      && ticket.getCurrentAvailability() >= seatCount
      && (!accessible.equalsIgnoreCase("with")
      || ticket.getCurrentAccessibleAvailability() >= seatCount);

    if (isAvailable) {
      SuggestedSeatsResponse suggestedSeatsResponse =
        getSuggestedSeatsForTicket(ticket.getEventId(), seatCount,
          accessible.equalsIgnoreCase("with"));

      boolean isResponseNotNull = suggestedSeatsResponse != null;

      boolean areSuggestedTicketsValid = isResponseNotNull && suggestedSeatsResponse
        .getSuggestedTickets().stream().limit(5)
        .allMatch(suggestedTicket -> suggestedTicket.getPrice() > 0);

      boolean isUserInfoPresent = getTestContext().containsKey(USER_INFO.name());

      int remainingSeats = isUserInfoPresent ? getRemainingSeats(
        getTestContext().get(USER_INFO.name()),
        ticket.getEventId(),
        ticket.getCmsData().getMaximumAllowed()) : 0;

      boolean hasSufficientSeats = !isUserInfoPresent || remainingSeats >= seatCount;

      return isResponseNotNull && areSuggestedTicketsValid && hasSufficientSeats;
    }
    return false;
  }

  private static boolean isTicketAvailable(TicketResponse ticket,
                                           int seatCount, String accessible,
                                           int parkingTicketCount,
                                           String parkingTicketGarage,
                                           String parkingTicketSoldOut) {
    boolean isTicketAvailable =
      seatCount <= 0 || isTicketAvailable(ticket, seatCount, accessible);
    if (isTicketAvailable && parkingTicketCount == 0) {
      return true;
    } else if (isTicketAvailable && parkingTicketCount > 0) {
      List<ParkingTicketResponse> parkingTicketList =
        getParkingPassTicketListFor(ticket.getEventId(), -1);
      if (!parkingTicketList.isEmpty()) {
        ParkingTicketResponse parkingTicketResponse = parkingTicketList.get(0);
        int parkingId = parkingTicketResponse.getParkingId();
        ParkingAvailabilityResponse parkingAvailabilityResponse =
          getParkingPassAvailability(parkingId);
        boolean isParkingTicketAvailable =
          isParkingTicketAvailable(parkingAvailabilityResponse, parkingTicketResponse,
            parkingTicketCount);
        if (isParkingTicketAvailable && (parkingTicketGarage.equalsIgnoreCase("any")
          || (StringUtils.isNumeric(parkingTicketGarage)
          &&
          parkingAvailabilityResponse.getGarages().size() == Integer.parseInt(parkingTicketGarage)))
          &&
          isParkingAvailabilityConfigVerified(parkingAvailabilityResponse, parkingTicketSoldOut)) {
          getTestContext().set(SELECTED_PARKING_TICKET_AVAILABILITY_RESPONSE,
            parkingAvailabilityResponse);
          getTestContext().set(SELECTED_PARKING_TICKET_GARAGE,
            parkingAvailabilityResponse.getGarages().size());
          getTestContext().set(SELECTED_PARKING_TICKET_RESPONSE, parkingTicketList);
          return true;
        }
      }
    }
    return false;
  }

  private boolean isParkingTicketAvailable(ParkingAvailabilityResponse parkingAvailabilityResponse,
                                           ParkingTicketResponse parkingTicketResponse,
                                           int parkingTicketCount) {
    return parkingAvailabilityResponse != null
      &&
      parkingAvailabilityResponse.getGarages().stream().allMatch(
        garage -> garage.getAvailableSpots().stream().allMatch(spot -> spot.getPrice() > 0))
      && (!getTestContext().containsKey(USER_INFO.name())
      || getRemainingSeats(getTestContext().get(USER_INFO.name()),
        parkingTicketResponse.getParkingId(),
        parkingTicketResponse.getCmsData().getParkingMaximumAllowed()) >= parkingTicketCount);
  }

  private boolean areAllSpotsAvailableForParking(
    ParkingAvailabilityResponse parkingAvailabilityResponse) {
    if (parkingAvailabilityResponse != null) {
      List<String> typeOfParking =
        Arrays.asList(STANDARD.getValue(), ACCESSIBLE.getValue(), ELECTRIC.getValue());
      Set<String> availableSpotTypes = parkingAvailabilityResponse.getGarages().stream()
        .flatMap(garage -> garage.getAvailableSpots().stream())
        .map(ParkingAvailabilityResponse.Garage.AvailableSpot::getType)
        .collect(Collectors.toSet());
      return availableSpotTypes.size() == typeOfParking.size()
        && typeOfParking.containsAll(availableSpotTypes);
    }
    return false;
  }

  private boolean isParkingAvailabilityConfigVerified(
    ParkingAvailabilityResponse parkingAvailabilityResponse, String parkingTicketSoldOut) {
    if (StringUtils.isEmpty(parkingTicketSoldOut))
      return true;
    boolean areAllSpotsAvailableForParking =
      areAllSpotsAvailableForParking(parkingAvailabilityResponse);
    return ((parkingTicketSoldOut.equalsIgnoreCase("without sold out")
      && areAllSpotsAvailableForParking)
      || (parkingTicketSoldOut.equalsIgnoreCase("with sold out")
      && !areAllSpotsAvailableForParking));
  }

  public static List<TicketResponse> getAllTicketsGames() {
    return TicketService.getGameTickets();
  }

  public static List<TicketResponse> getAllTicketsEvents() {
    return TicketService.getEventTickets();
  }

  public static List<ClubListResponse.CmsData> getClubList(TicketType ticketType) {
    return TicketService.getCmsClubList(ticketType);
  }

  public static List<TicketResponse> getClubTickets(TicketType ticketType, int clubId) {
    return ticketType.equals(GAME) ? TicketService.getGameClubTickets(clubId)
      : TicketService.getEventClubTickets(clubId);
  }


  /**
   * It returns the suggested seats list for the given ticket
   *
   * @param eventId         id of the ticket
   * @param totalTickets    number of seats to purchase
   * @param needsAccessible true if requires accessible seats
   * @return SuggestedSeatsResponse
   */
  public static SuggestedSeatsResponse getSuggestedSeatsForTicket(int eventId,
                                                                  int totalTickets,
                                                                  boolean needsAccessible) {
    return getTestContext().get(SELECTED_TICKET_TYPE).equals(TicketType.EVENT)
      ? TicketService.getSuggestedSeatsForEvent(eventId, totalTickets, needsAccessible) :
      TicketService.getSuggestedSeatsForGame(eventId, totalTickets, needsAccessible);
  }

  /**
   * get the booked seat count for specific user
   *
   * @param userInfo user info tobe filled with AccountInfo
   * @param eventId  event id for which we need to find the booked seats
   * @return booked seats
   */
  public static int getBookedSeats(UserInfo userInfo, int eventId) {
    AccountSeatsResponse accountSeatsListFromApi =
      getAccountSeatsList(userInfo, eventId, TimeFilter.DEFAULT);
    if (accountSeatsListFromApi != null) {
      return accountSeatsListFromApi.getTickets().stream()
        .flatMap(ticket -> ticket.getSeats().stream())
        .mapToInt(AccountSeatsResponse.Tickets.Seats::getNumSeats)
        .sum();
    }
    return 0; // Default to 0 if no seats are booked.
  }

  /**
   * get the Remaining seats to purchase for specific user
   *
   * @param userInfo   user info tobe filled with AccountInfo
   * @param eventId    event id for which we need to find the remaining seats
   * @param maxAllowed maximum seat allowed for the event
   * @return remaining seats
   */
  public int getRemainingSeats(UserInfo userInfo, int eventId, int maxAllowed) {
    int remainingSeats = maxAllowed - getBookedSeats(userInfo, eventId);
    return remainingSeats >= 0 ? remainingSeats : maxAllowed;
  }

  /**
   * Retrieves a ticket based on availability and matches it with available parking passes.
   *
   * @param ticketType The type of ticket (e.g., GAME or EVENT).
   * @return A matching {@link TicketResponse} with available parking passes,null if none are found.
   */
  public static TicketResponse getTicketsAccordingToAvailabilityWithParkingAvailability(
    TicketType ticketType) {
    int parkingSeatCount = getTestContext().getOrDefault(SELECTED_PARKING_TICKET_COUNT, 0);
    String parkingTicketGarage =
      getTestContext().getOrDefault(SELECTED_PARKING_TICKET_GARAGE, "any");
    String ticketSoldOutStatus = getTestContext().getOrDefault(SELECTED_TICKET_STATUS, "");
    String parkingSoldOutStatus =
      getTestContext().getOrDefault(SELECTED_PARKING_TICKET_PARKING_TYPE_STATUS, "");
    List<TicketResponse> tickets = new ArrayList<>(
      getTicketsAccordingToAvailabilityWithParkingMatch(ticketType, ticketSoldOutStatus));
    for (int i = tickets.size() - 1; i >= 0; i--) {
      int randomIndex = random.nextInt(tickets.size());
      TicketResponse ticket = tickets.get(randomIndex);
      if (isTicketAvailable(ticket, -1, null, parkingSeatCount, parkingTicketGarage,
        parkingSoldOutStatus))
        return ticket;
      tickets.remove(randomIndex);
    }
    return null;
  }
}