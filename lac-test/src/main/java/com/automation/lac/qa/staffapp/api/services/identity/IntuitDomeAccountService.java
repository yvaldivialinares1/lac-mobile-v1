package com.automation.lac.qa.staffapp.api.services.identity;

import static com.automation.lac.qa.staffapp.constants.ServiceConstants.BACK_BASE_URI;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import com.automation.lac.qa.staffapp.api.models.identity.AccountRegistrationResponse;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.NewIntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.TicketingIdRequestDto;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

@UtilityClass
public class IntuitDomeAccountService {

  /**
   * GET IntuitDomeAccountDto by id
   *
   * @param intuitDomeAccountId String indicating the id of fan account.
   * @return IntuitDomeAccount
   */
  @Step("GET - identity/v1/intuit-dome-account/{intuitDomeAccountId}")
  public IntuitDomeAccountDto findIntuitDomeAccountById(String intuitDomeAccountId) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .pathParam("intuitDomeAccountId", intuitDomeAccountId)
      .get("identity/v1/intuit-dome-account/{intuitDomeAccountId}");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
    return response.getResponse().as(IntuitDomeAccountDto.class);
  }

  /**
   * POST license plate note for account
   *
   * @param request NewIntuitDomeAccountDto indicating the request data.
   * @return LicensePlateRelationship
   */
  @Step("POST - identity/v1/intuit-dome-account/")
  public AccountRegistrationResponse registerNewIntuitDomeAccount(
    NewIntuitDomeAccountDto request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(request)
      .post("identity/v1/intuit-dome-account/");

    Assert.assertEquals(response.getResponse().statusCode(), SC_CREATED,
      response.getResponse().getBody().asString());
    return response.getResponse().as(AccountRegistrationResponse.class);
  }

  /**
   * POST link ticket master id to a fan account.
   *
   * @param request TicketingIdRequestDto indicating the request data.
   */
  @Step("POST - identity/v1/intuit-dome-account/ticket-master/id")
  public void addTicketingIdToIntuitDomeAccount(
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
