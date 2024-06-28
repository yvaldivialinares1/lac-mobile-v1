package com.automation.lac.qa.fanapp.api.services.identity;

import static com.automation.lac.qa.fanapp.api.enums.lacendpoints.LacEndpoints.LOGIN;
import static com.automation.lac.qa.fanapp.api.utils.ServiceConstants.BACK_BASE_URI;

import com.automation.lac.qa.fanapp.api.models.identity.AccountIdRequestDto;
import com.automation.lac.qa.fanapp.api.models.identity.AccountIdResponse;
import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class IdentityService {

  /**
   * Logs into the Intuit Dome account using the provided request body.
   *
   * @param requestBody The request body with account credentials.
   * @return The response containing the account ID information.
   */
  @Step("POST - /identity/v2/intuit-dome-account/login")
  public static AccountIdResponse intuitDomeLogin(AccountIdRequestDto requestBody) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(requestBody)
      .post(LOGIN.getText());
    return response.getResponse().statusCode() == 200
      ? response.getResponse().as(AccountIdResponse.class) : null;
  }
}
