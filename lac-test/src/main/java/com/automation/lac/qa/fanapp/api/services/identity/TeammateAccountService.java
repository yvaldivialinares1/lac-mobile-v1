package com.automation.lac.qa.fanapp.api.services.identity;

import static com.automation.lac.qa.fanapp.api.enums.lacendpoints.LacEndpoints.DELETE_TEAMMATE;
import static com.automation.lac.qa.fanapp.api.utils.ServiceConstants.BACK_BASE_URI;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;

import com.automation.lac.qa.fanapp.api.models.identity.DeleteTeammateRequestDto;
import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

@Slf4j
@UtilityClass
public class TeammateAccountService {

  /**
   * Deletes teammate account using the provided request body.
   *
   * @param requestBody The request body with teammate id to delete.
   */
  @Step("DELETE - /identity/v1/intuit-dome-account/mini")
  public static void deleteTeammateAccount(DeleteTeammateRequestDto requestBody) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(requestBody)
      .delete(DELETE_TEAMMATE.getText());

    Assert.assertEquals(response.getResponse().statusCode(), SC_NO_CONTENT,
            response.getResponse().getBody().asString());
  }
}