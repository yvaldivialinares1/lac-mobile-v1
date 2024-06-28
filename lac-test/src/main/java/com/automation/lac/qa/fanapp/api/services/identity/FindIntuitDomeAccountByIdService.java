package com.automation.lac.qa.fanapp.api.services.identity;

import static com.automation.lac.qa.fanapp.api.enums.lacendpoints.LacEndpoints.FIND_INTUIT_DOME_ACCOUNT_BY_ID;
import static com.automation.lac.qa.fanapp.api.utils.ServiceConstants.BACK_BASE_URI;

import com.automation.lac.qa.fanapp.api.models.identity.FindIntuitDomeAccountByIdResponse;
import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class FindIntuitDomeAccountByIdService {

  /**
   * Retrieves intuit dome account information for given user id
   *
   * @param userId The user id to retrieve information.
   * @return The response containing the account ID information.
   */
  @Step("GET - identity/v1/intuit-dome-account/{userId}")
  public static FindIntuitDomeAccountByIdResponse getIntuitDomeAccountInformation(String userId) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .get(String.format(FIND_INTUIT_DOME_ACCOUNT_BY_ID.getText(), userId));
    return response.getResponse().as(FindIntuitDomeAccountByIdResponse.class);
  }
}