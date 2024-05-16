package com.automation.lac.qa.fanapp.api.services.tickets;

import static com.automation.lac.qa.driver.AppiumConstants.platformName;
import static com.automation.lac.qa.fanapp.api.utils.ServiceConstants.BACK_BASE_URI;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.fanapp.api.enums.ticketing.TimeFilter;
import com.automation.lac.qa.fanapp.api.models.identity.AccountIdRequestDto;
import com.automation.lac.qa.fanapp.api.models.identity.AccountIdResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.AccountSeatsResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.SuggestedSeatsResponse;
import com.automation.lac.qa.fanapp.api.models.tickets.TicketResponse;
import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import java.util.Arrays;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
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
  public static List<TicketResponse> getTicketingGames() {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.URLENC)
      .get("ticketing/v1/games");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
    return Arrays.asList(response.getResponse().as(TicketResponse[].class));
  }

  /**
   * GET Events Ticket List
   *
   * @return Events Ticket List
   */
  @Step("GET - ticketing/v1/events")
  public static List<TicketResponse> getTicketingEvents() {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.URLENC)
      .get("ticketing/v1/events");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
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
  public SuggestedSeatsResponse getSuggestedSeatsForGame(int gameId,
                                                         int totalTickets,
                                                         boolean needsAccessible) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .pathParam("gameId", String.valueOf(gameId))
      .queryParam("totalTickets", String.valueOf(totalTickets))
      .queryParam("needsAccessible", needsAccessible)
      .get("ticketing/v1/games/{gameId}/tickets/suggestions");
    return response.getResponse().statusCode() == 200
      ? response.getResponse().as(SuggestedSeatsResponse.class) : null;
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
  public SuggestedSeatsResponse getSuggestedSeatsForEvent(int eventId,
                                                          int totalTickets,
                                                          boolean needsAccessible) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .pathParam("eventId", String.valueOf(eventId))
      .queryParam("totalTickets", String.valueOf(totalTickets))
      .queryParam("needsAccessible", needsAccessible)
      .get("ticketing/v1/events/{eventId}/tickets/suggestions");
    return response.getResponse().statusCode() == 200
      ? response.getResponse().as(SuggestedSeatsResponse.class) : null;
  }

  /**
   * Get the  account seats list for a given event ID and time filter.
   *
   * @param tmEventId  The ID of the event.
   * @param timeFilter The time filter for the seats.
   * @return The response containing the account seats list.
   */
  public static AccountSeatsResponse getAccountSeatsList(int tmEventId, TimeFilter timeFilter) {
    UserInfo userInfo = getTestContext().get(USER_INFO.name());
    AccountIdRequestDto requestBody =
      AccountIdRequestDto.builder()
        .email(userInfo.getAccountInfo().getEmail())
        .password(userInfo.getAccountInfo().getPassword())
        .hostOs(platformName.toUpperCase())
        .build();
    AccountIdResponse accountIdResponse = IdentityService.intuitDomeLogin(requestBody);
    String accessToken = accountIdResponse.getAccessToken();
    Request request = new Request();
    if (tmEventId != -1)
      request.queryParam("tmEventId", tmEventId);
    if (!timeFilter.equals(TimeFilter.DEFAULT))
      request.queryParam("timeFilter", timeFilter.name());
    Response response = request
      .baseUri(BACK_BASE_URI)
      .header("authorization", "Bearer " + accessToken)
      .get("ticketing/v1/accounts/seats");
    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "Unexpected get token StatusCode");
    return response.getResponse().as(AccountSeatsResponse.class);
  }
}