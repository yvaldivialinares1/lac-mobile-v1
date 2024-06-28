package com.automation.lac.qa.staffapp.api.services.identity;

import static com.automation.lac.qa.staffapp.constants.ServiceConstants.BACK_BASE_URI;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import com.automation.lac.qa.staffapp.api.models.identity.AccountRegistrationResponse;
import com.automation.lac.qa.staffapp.api.models.identity.CreateMiniIntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.ManualAgeValidationDto;
import com.automation.lac.qa.staffapp.api.models.identity.NewIntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.NfcRelationship;
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
  public static IntuitDomeAccountDto findIntuitDomeAccountById(String intuitDomeAccountId) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .pathParam("intuitDomeAccountId", intuitDomeAccountId)
      .get("identity/v1/intuit-dome-account/{intuitDomeAccountId}");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
    return response.getResponse().as(IntuitDomeAccountDto.class);
  }

  /**
   * POST new intuit dome account
   *
   * @param request NewIntuitDomeAccountDto indicating the request data.
   * @return AccountRegistrationResponse
   */
  @Step("POST - identity/v1/intuit-dome-account/")
  public static AccountRegistrationResponse registerNewIntuitDomeAccount(
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
   * POST new mini intuit dome account
   *
   * @param request CreateMiniIntuitDomeAccountDto indicating the request data.
   * @return IntuitDomeAccountDto
   */
  @Step("POST - identity/v1/intuit-dome-account/mini/")
  public static IntuitDomeAccountDto createMiniIntuitDomeAccount(
    CreateMiniIntuitDomeAccountDto request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(request)
      .post("identity/v1/intuit-dome-account/mini/");

    Assert.assertEquals(response.getResponse().statusCode(), SC_CREATED,
      response.getResponse().getBody().asString());
    return response.getResponse().as(IntuitDomeAccountDto.class);
  }

  /**
   * POST new nfc relationship
   *
   * @param request NfcRelationship indicating the request data.
   * @return NfcRelationship
   */
  @Step("POST - identity/v1/intuit-dome-account/nfc/")
  public static NfcRelationship createNfcRelationship(
    NfcRelationship request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(request)
      .post("identity/v1/intuit-dome-account/nfc/");

    Assert.assertEquals(response.getResponse().statusCode(), SC_CREATED,
      response.getResponse().getBody().asString());
    return response.getResponse().as(NfcRelationship.class);
  }

  /**
   * PUT manual age validation.
   *
   * @param request ManualAgeValidationDto indicating the request data.
   * @return IntuitDomeAccountDto
   */
  @Step("POST - identity/v1/intuit-dome-account/age-validation/manual")
  public static IntuitDomeAccountDto manualAgeValidation(
    ManualAgeValidationDto request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(request)
      .put("identity/v1/intuit-dome-account/age-validation/manual");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
    return response.getResponse().as(IntuitDomeAccountDto.class);
  }
}
