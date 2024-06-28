package com.automation.lac.qa.fanapp.api.services.tickets;

import static com.automation.lac.qa.fanapp.api.tasks.identity.IdentityTask.getAccessTokenWithRetry;
import static com.automation.lac.qa.fanapp.api.utils.ServiceConstants.BACK_BASE_URI;
import static com.automation.lac.qa.fanapp.mobile.enums.TicketAvailability.AVAILABLE;
import static com.automation.lac.qa.fanapp.mobile.enums.TicketAvailability.FEW_SEATS;
import static com.automation.lac.qa.fanapp.mobile.enums.TicketType.GAME;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.fanapp.api.enums.ticketing.TimeFilter;
import com.automation.lac.qa.fanapp.api.models.tickets.AccountSeatsResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.ClubListResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.ParkingAvailabilityResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.ParkingTicketResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.SuggestedSeatsResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.TicketResponse;
import com.automation.lac.qa.fanapp.mobile.enums.TicketType;
import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

@Slf4j
@UtilityClass
public class TicketService {

  /**
   * GET Games Ticket List
   *
   * @return Games Ticket List
   */
  @Step("GET - ticketing/v1/games")
  public static List<TicketResponse> getGameTickets() {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.URLENC)
      .get("ticketing/v1/games");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "GET Service failing: ticketing/v1/games");
    return Arrays.asList(response.getResponse().as(TicketResponse[].class));
  }

  /**
   * GET Club List
   *
   * @return Club List
   */
  @Step("GET - ticketing/v1/clubs")
  public static List<ClubListResponse.CmsData> getCmsClubList(TicketType ticketType) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .queryParam("tmEventType", ticketType.name())
      .get("/ticketing/v1/clubs");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "GET Service failing: ticketing/v1/clubs");

    return response.getResponse().jsonPath()
      .getList("clubs.cmsData.flatten()", ClubListResponse.CmsData.class);
  }

  /**
   * GET Games club ticket list
   *
   * @return Games club ticket list
   */
  @Step("GET - ticketing/v1/games?clubId={clubId}")
  public static List<TicketResponse> getGameClubTickets(int clubId) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .queryParam("clubId", clubId)
      .get("/ticketing/v1/games");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "GET Service failing: ticketing/v1/games?clubId=" + clubId);
    return Arrays.asList(response.getResponse().as(TicketResponse[].class));
  }

  /**
   * GET Events Ticket List
   *
   * @return Events Ticket List
   */
  @Step("GET - ticketing/v1/events")
  public static List<TicketResponse> getEventTickets() {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.URLENC)
      .get("ticketing/v1/events");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "GET Service failing: ticketing/v1/events");
    return Arrays.asList(response.getResponse().as(TicketResponse[].class));
  }

  /**
   * GET Events club ticket list
   *
   * @return Events club ticket list
   */
  @Step("GET - ticketing/v1/events?clubId={clubId}")
  public static List<TicketResponse> getEventClubTickets(int clubId) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .queryParam("clubId", clubId)
      .get("/ticketing/v1/events");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "GET Service failing: ticketing/v1/events?clubId=" + clubId);
    return Arrays.asList(response.getResponse().as(TicketResponse[].class));
  }

  /**
   * It returns the suggested seats list for the given game
   *
   * @param gameId          id of the game
   * @param totalTickets    number of seats to purchase
   * @param needsAccessible true if requires accessible seats
   * @return SuggestedSeatsResponse
   */
  @Step("GET - ticketing/v1/games/{gameId}/tickets/suggestions")
  public static SuggestedSeatsResponse getSuggestedSeatsForGame(int gameId,
                                                                int totalTickets,
                                                                boolean needsAccessible) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .pathParam("gameId", String.valueOf(gameId))
      .queryParam("totalTickets", String.valueOf(totalTickets))
      .queryParam("needsAccessible", needsAccessible)
      .get("ticketing/v1/games/{gameId}/tickets/suggestions");

    //TODO Validate if 404 is still a expected response
    // 404 is appearing when user doesn't purchase history for a specific event ID
    int responseStatusCode = response.getResponse().statusCode();
    boolean statusCode = responseStatusCode == SC_OK
                         || responseStatusCode == SC_NO_CONTENT
                         || responseStatusCode == SC_NOT_FOUND;

    String assertMsg = String.format("GET Service failing: "
      + "ticketing/v1/games/%s/tickets/suggestions", String.valueOf(gameId));

    Assert.assertTrue(statusCode,assertMsg);
    if (responseStatusCode == SC_NO_CONTENT || responseStatusCode == SC_NOT_FOUND) {
      return null;
    } else {
      return response.getResponse().as(SuggestedSeatsResponse.class);
    }
  }

  /**
   * It returns the suggested seats list for the given event
   *
   * @param eventId         id of the event
   * @param totalTickets    number of seats to purchase
   * @param needsAccessible true if requires accessible seats
   * @return SuggestedSeatsResponse
   */
  @Step("GET - ticketing/v1/events/{eventId}/tickets/suggestions")
  public static SuggestedSeatsResponse getSuggestedSeatsForEvent(int eventId,
                                                                 int totalTickets,
                                                                 boolean needsAccessible) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .pathParam("eventId", String.valueOf(eventId))
      .queryParam("totalTickets", String.valueOf(totalTickets))
      .queryParam("needsAccessible", needsAccessible)
      .get("ticketing/v1/events/{eventId}/tickets/suggestions");

    //TODO Validate if 404 is still a expected response
    // 404 is appearing when user doesn't purchase history for a specific event ID
    int responseStatusCode = response.getResponse().statusCode();
    boolean statusCode = responseStatusCode == SC_OK
                         || responseStatusCode == SC_NO_CONTENT
                         || responseStatusCode == SC_NOT_FOUND;

    String assertMsg = String.format("GET Service failing: "
      + "ticketing/v1/events/%s/tickets/suggestions", String.valueOf(eventId));

    Assert.assertTrue(statusCode,assertMsg);
    if (responseStatusCode == SC_NO_CONTENT || responseStatusCode == SC_NOT_FOUND) {
      return null;
    } else {
      return response.getResponse().as(SuggestedSeatsResponse.class);
    }
  }

  /**
   * Get the  account seats list for a given event ID and time filter.
   *
   * @param tmEventId  The ID of the event.
   * @param timeFilter The time filter for the seats.
   * @return The response containing the account seats list.
   */
  @Step("GET - ticketing/v1/accounts/seats")
  public static AccountSeatsResponse getAccountSeatsList(UserInfo userInfo, int tmEventId,
                                                         TimeFilter timeFilter) {
    String accessToken = getAccessTokenWithRetry(userInfo);
    if (StringUtils.isNotEmpty(accessToken)) {
      Request request = new Request();
      if (tmEventId != -1)
        request.queryParam("tmEventId", tmEventId);
      if (!timeFilter.equals(TimeFilter.DEFAULT))
        request.queryParam("timeFilter", timeFilter.name());
      Response response = request
        .baseUri(BACK_BASE_URI)
        .header("authorization", "Bearer " + accessToken)
        .get("ticketing/v1/accounts/seats");

      //TODO Validate if 404 is still a expected response
      // 404 is appearing when user doesn't purchase history for a specific event ID
      int responseStatusCode = response.getResponse().statusCode();
      boolean statusCode = responseStatusCode == SC_OK
              || responseStatusCode == SC_NO_CONTENT
              || responseStatusCode == SC_NOT_FOUND;

      Assert.assertTrue(statusCode,"GET Service failing: ticketing/v1/accounts/seats");
      if (responseStatusCode == SC_NO_CONTENT || responseStatusCode == SC_NOT_FOUND) {
        return null;
      } else {
        return response.getResponse().as(AccountSeatsResponse.class);
      }
    }
    return null;
  }

  /**
   * get parking pass ticket list for specific event/parking
   *
   * @param eventId        valid eventId or -1 if we don't require eventId
   * @param parkingEventId valid parkingId or -1 if we don't require parkingEventId
   * @return List of ParkingTicketResponse
   */
  @Step("GET - ticketing/v1/parking")
  public static List<ParkingTicketResponse> getParkingPassTicketListFor(int eventId,
                                                                        int parkingEventId) {
    Request request = new Request();
    if (eventId != -1)
      request.queryParam("eventId", eventId);
    if (parkingEventId != -1)
      request.queryParam("parkingEventId", parkingEventId);
    Response response = request
      .baseUri(BACK_BASE_URI)
      .get("ticketing/v1/parking");

    return response.getResponse().statusCode() == 200
      ? Arrays.asList(response.getResponse().as(ParkingTicketResponse[].class)) :
      new ArrayList<>();
  }

  /**
   * get the availability of parking
   *
   * @param parkingId id of the parking
   * @return ParkingAvailabilityResponse
   */
  @Step("GET - ticketing/v1/parking/{parkingId}/availability")
  public static ParkingAvailabilityResponse getParkingPassAvailability(int parkingId) {
    Request request = new Request();
    Response response = request
      .baseUri(BACK_BASE_URI)
      .pathParam("parkingId", String.valueOf(parkingId))
      .get("ticketing/v1/parking/{parkingId}/availability");

    return response.getResponse().statusCode() == 200
      ? response.getResponse().as(ParkingAvailabilityResponse.class) : null;
  }

  /**
   * Retrieves a list of parking passes based on their availability and on-sale status.
   *
   * @param onSaleFlag Indicates whether to filter by tickets that are on sale.
   * @return A list of {@link ParkingTicketResponse} objects matching the criteria.
   */
  public static List<ParkingTicketResponse> getListOfParkingPassesAvailabilityWithOnSaleFlag(
    Boolean onSaleFlag) {
    return getParkingPassTicketListFor(-1, -1).stream()
      .filter(ticket -> ticket.getAvailability() != null
        && (ticket.getAvailability().equals("FEW_SEATS")
        || ticket.getAvailability().equals("AVAILABLE"))
        && ticket.isOnSale() == onSaleFlag)
      .toList();
  }

  /**
   * Retrieves tickets based on availability and matches them with available parking passes.
   *
   * @param ticketType   The type of ticket (e.g., GAME or EVENT).
   * @param availability The availability status of the tickets (e.g., "AVAILABLE").
   * @return A list of {@link TicketResponse} objects that match availability and parking passes.
   */
  public static List<TicketResponse> getTicketsAccordingToAvailabilityWithParkingMatch(
    TicketType ticketType, String availability) {

    List<TicketResponse> tickets = ticketType.equals(GAME) ? getGameTickets() : getEventTickets();

    List<TicketResponse> availableTickets = tickets.stream()
      .filter(ticket -> {
        String ticketAvailability = ticket.getAvailability();
        if (ticketAvailability == null) {
          return false;
        }
        if (availability.equals(AVAILABLE.getName())) {
          return ticketAvailability.equals(FEW_SEATS.getName())
            || ticketAvailability.equals(AVAILABLE.getName());
        } else {
          return ticketAvailability.equals(availability);
        }
      })
      .toList();

    List<ParkingTicketResponse> parkingTickets =
      getListOfParkingPassesAvailabilityWithOnSaleFlag(false)
        .stream()
        .filter(ticket -> ticket.getTypeOfEvent().equals(ticketType.toString()))
        .toList();

    return parkingTickets.stream()
      .map(ParkingTicketResponse::getMatchingEventId)
      .distinct()
      .filter(matchingId -> availableTickets.stream()
        .anyMatch(ticket -> ticket.getEventId() == matchingId))
      .flatMap(matchingId -> availableTickets.stream()
        .filter(ticket -> ticket.getEventId() == matchingId))
      .toList();
  }
}