package com.automation.lac.qa.fanapp.api.services.payments;

import static com.automation.lac.qa.fanapp.api.enums.lacendpoints.LacEndpoints.DELETE_ALL_PAYMENTS_METHODS;
import static com.automation.lac.qa.fanapp.api.enums.lacendpoints.LacEndpoints.PAYMENTS_V1_BASE_URI;
import static com.automation.lac.qa.fanapp.api.utils.ServiceConstants.BACK_BASE_URI;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;

import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

@Slf4j
@UtilityClass
public class PaymentsMethodsService {

  /**
   * Deletes all payment methods associated with a user identified by userId.
   * Performs a DELETE request to the endpoint "/v1/users/{userId}/payment-methods".
   * Validates the response status code. If the userId is not found (404), throws a CustomException.
   *
   * @param userId The ID of the user whose payment methods are to be deleted.
   * @throws CustomException If the userId is not found (404).
   */
  @Step("DELETE - payments-/v1/users/{userId}/payment-methods")
  public static void deleteAllPaymentMethods(String userId) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .delete(String.format("%s%s", PAYMENTS_V1_BASE_URI.getText(),
        DELETE_ALL_PAYMENTS_METHODS.getText().replace("%s", userId)));
    Assert.assertEquals(response.getResponse().statusCode(), SC_NO_CONTENT,
      response.getResponse().getBody().asString());
  }
}
