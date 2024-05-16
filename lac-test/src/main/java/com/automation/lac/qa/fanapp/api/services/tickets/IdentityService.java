package com.automation.lac.qa.fanapp.api.services.tickets;

import static com.automation.lac.qa.fanapp.api.utils.ServiceConstants.BACK_BASE_URI;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.fanapp.api.models.identity.AccountIdRequestDto;
import com.automation.lac.qa.fanapp.api.models.identity.AccountIdResponse;
import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

@Slf4j
@UtilityClass
public class IdentityService {

  /**
   * Logs into the Intuit Dome account using the provided request body.
   *
   * @param requestBody The request body with account credentials.
   * @return The response containing the account ID information.
   */
  @Step("POST - identity/v1/intuit-dome-account/ticket-master/id")
  public static AccountIdResponse intuitDomeLogin(AccountIdRequestDto requestBody) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(requestBody)
      .post("/identity/v2/intuit-dome-account/login");
    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "Unexpected get token StatusCode");
    return response.getResponse().as(AccountIdResponse.class);
  }
}
