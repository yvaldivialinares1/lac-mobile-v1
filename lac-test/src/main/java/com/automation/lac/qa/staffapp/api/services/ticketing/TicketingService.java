package com.automation.lac.qa.staffapp.api.services.ticketing;

import static com.automation.lac.qa.staffapp.constants.ServiceConstants.BACK_BASE_URI;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;

import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import com.automation.lac.qa.staffapp.api.models.identity.TicketingIdRequestDto;
import com.automation.lac.qa.staffapp.api.models.ticketing.MockRedeemRequestV2;
import com.automation.lac.qa.staffapp.api.models.ticketing.TicketMasterUserIdRequest;
import com.automation.lac.qa.staffapp.api.models.ticketing.TicketMasterUserIdResponse;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

@UtilityClass
public class TicketingService {

  /**
   * POST ticketing mock response
   *
   * @param request MockRedeemRequestV2 indicating the request data.
   */
  @Step("POST - ticketing/v2/add/redeem")
  public static void setUpTicketingMockResponseV2(MockRedeemRequestV2 request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(request)
      .post("ticketing/v2/add/redeem");

    Assert.assertEquals(response.getResponse().statusCode(), SC_CREATED,
      response.getResponse().getBody().asString());
  }

  /**
   * POST ticketing account
   *
   * @param request MockRedeemRequestV2 indicating the request data.
   * @return TicketMasterUserIdResponse
   */
  @Step("POST - ticketing/v1/accounts")
  public static TicketMasterUserIdResponse generateTicketMasterUserId(
    TicketMasterUserIdRequest request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(request)
      .post("ticketing/v1/accounts");

    Assert.assertEquals(response.getResponse().statusCode(), SC_CREATED,
      response.getResponse().getBody().asString());
    return response.getResponse().as(TicketMasterUserIdResponse.class);
  }

  /**
   * POST link ticket master id to a fan account.
   *
   * @param request TicketingIdRequestDto indicating the request data.
   */
  @Step("POST - identity/v1/intuit-dome-account/ticket-master/id")
  public static void addTicketingIdToIntuitDomeAccount(
    TicketingIdRequestDto request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(request)
      .post("identity/v1/intuit-dome-account/ticket-master/id");

    Assert.assertEquals(response.getResponse().statusCode(), SC_NO_CONTENT,
      response.getResponse().getBody().asString());
  }
}
