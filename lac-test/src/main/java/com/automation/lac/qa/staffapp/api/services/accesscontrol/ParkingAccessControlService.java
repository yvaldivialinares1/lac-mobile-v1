package com.automation.lac.qa.staffapp.api.services.accesscontrol;

import static com.automation.lac.qa.staffapp.constants.ServiceConstants.BACK_BASE_URI;
import static com.automation.lac.qa.staffapp.constants.ServiceConstants.HEADER_PARKING_API_KEY;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import com.automation.lac.qa.staffapp.api.models.accesscontrol.LanesResponse;
import com.automation.lac.qa.staffapp.api.models.accesscontrol.ParkingAccessValidationRequest;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

@UtilityClass
public class ParkingAccessControlService {

  /**
   * GET the parking lanes
   *
   * @param garage String indicating the garage for which we get the lines.
   * @return LanesResponse
   */
  @Step("GET - access-control/v1/parking/lanes?garage={garage}")
  public static LanesResponse getParkingLines(String garage) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .queryParam("garage", garage)
      .get("access-control/v1/parking/lanes?garage={garage}");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
    return response.getResponse().as(LanesResponse.class);
  }

  /**
   * POST new parking access validation event
   *
   * @param request ParkingAccessValidationRequest indicating the request data.
   */
  @Step("POST - access-control/v1/parking/lpr-events")
  public static void createParkingAccessValidationEvent(ParkingAccessValidationRequest request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .header("X-API-KEY", HEADER_PARKING_API_KEY)
      .contentType(ContentType.JSON)
      .body(request)
      .post("access-control/v1/parking/lpr-events");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
  }
}
