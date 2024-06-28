package com.automation.lac.qa.staffapp.api.services.identity;

import static com.automation.lac.qa.staffapp.constants.ServiceConstants.BACK_BASE_URI;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import com.automation.lac.qa.staffapp.api.models.identity.AccountPhotoResponseDto;
import com.automation.lac.qa.staffapp.api.models.identity.RegisterBiometricRequestDto;
import com.automation.lac.qa.staffapp.api.models.identity.RegisterBiometricResponseDto;
import com.automation.lac.qa.staffapp.api.models.identity.SearchPhotoParamsDto;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import java.util.Arrays;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

@UtilityClass
public class BiometricsService {

  /**
   * POST biometric(game face id) for fan account
   *
   * @param request RegisterBiometricRequestDto indicating the request data.
   * @return RegisterBiometricResponseDto
   */
  @Step("POST - identity/v1/intuit-dome-account/biometrics")
  public static RegisterBiometricResponseDto registerBiometrics(
    RegisterBiometricRequestDto request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(request)
      .post("identity/v1/intuit-dome-account/biometrics");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
    return response.getResponse().as(RegisterBiometricResponseDto.class);
  }

  /**
   * PUT to find fan account by photo
   *
   * @param request SearchPhotoDto indicating the request data.
   * @return IntuitDomeAccountDto
   */
  @Step("PUT - identity/v1/intuit-dome-account/biometric/look-a-like")
  public static List<AccountPhotoResponseDto> findIntuitDomeAccountsByPhoto(
    SearchPhotoParamsDto request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(request)
      .put("identity/v1/intuit-dome-account/biometric/look-a-like");

    return response.getResponse().statusCode() == SC_OK
      ? Arrays.asList(response.getResponse().as(AccountPhotoResponseDto[].class)) : null;
  }


  /**
   * DEL fan account photo.
   *
   * @param intuitDomeAccountId account id.
   */
  @Step("DELETE - identity/v1/intuit-dome-account/{intuitDomeAccountId}/biometric")
  public static void deleteBiometric(String intuitDomeAccountId) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .pathParam("intuitDomeAccountId", intuitDomeAccountId)
      .delete("identity/v1/intuit-dome-account/{intuitDomeAccountId}/biometric");
    //TODO: ''findIntuitDomeAccountsByPhoto' may return the duplicated notes.
    // So when we iterate the notes to delete the biometric,
    // we get the error the it is already deleted. Will update the check when
    // api behaviour will be changed and there is no duplicated notes are stored.
    if (response.getResponse().statusCode() == SC_BAD_REQUEST) {
      Assert.assertTrue(
        response.getResponse().getBody().asString().contains("The fan does not have biometric"));
    } else {
      Assert.assertEquals(response.getResponse().statusCode(), SC_NO_CONTENT,
        response.getResponse().getBody().asString());
    }
  }
}
