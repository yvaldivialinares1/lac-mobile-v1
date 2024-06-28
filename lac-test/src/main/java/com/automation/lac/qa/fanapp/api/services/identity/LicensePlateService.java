package com.automation.lac.qa.fanapp.api.services.identity;

import static com.automation.lac.qa.fanapp.api.enums.lacendpoints.LacEndpoints.DELETE_LICENSE_PLATE;
import static com.automation.lac.qa.fanapp.api.utils.ServiceConstants.BACK_BASE_URI;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;

import com.automation.lac.qa.fanapp.api.models.identity.DeleteLicensePlateRequestDto;
import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

@Slf4j
@UtilityClass
public class LicensePlateService {

  /**
   * Deletes License plate using the provided request body.
   *
   * @param requestBody The request body with license plate to delete.
   */
  @Step("DELETE - /identity/v1/intuit-dome-account/license-plate")
  public static void deleteLicensePlate(DeleteLicensePlateRequestDto requestBody) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(requestBody)
      .delete(DELETE_LICENSE_PLATE.getText());

    Assert.assertEquals(response.getResponse().statusCode(), SC_NO_CONTENT,
            response.getResponse().getBody().asString());
  }
}