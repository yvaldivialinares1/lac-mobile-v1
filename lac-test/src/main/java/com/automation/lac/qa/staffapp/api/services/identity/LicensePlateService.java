package com.automation.lac.qa.staffapp.api.services.identity;

import static com.automation.lac.qa.staffapp.constants.ServiceConstants.BACK_BASE_URI;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDeleteDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateRelationship;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

@UtilityClass
public class LicensePlateService {

  /**
   * DELETE license plate note from account
   *
   * @param request LicensePlateDeleteDto indicating the request data.
   */
  @Step("DELETE - identity/v1/intuit-dome-account/license-plate")
  public static void deleteLicensePlate(LicensePlateDeleteDto request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(request)
      .delete("identity/v1/intuit-dome-account/license-plate");

    Assert.assertEquals(response.getResponse().statusCode(), SC_NO_CONTENT,
      response.getResponse().getBody().asString());
  }

  /**
   * Delete registered license plates
   *
   * @param request request
   */
  @Step("DELETE - identity/v2/intuit-dome-account/license-plate")
  public static void deleteLicensePlateV2(LicensePlateDeleteDto request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(request)
      .delete("identity/v2/intuit-dome-account/license-plate");
    Assert.assertEquals(response.getResponse().statusCode(), SC_NO_CONTENT,
      response.getResponse().getBody().asString());
  }

  /**
   * POST license plate note for account
   *
   * @param request LicensePlateRelationship indicating the request data.
   * @return LicensePlateRelationship
   */
  @Step("POST - identity/v1/intuit-dome-account/license-plate")
  public static LicensePlateRelationship registerLicensePlate(LicensePlateDto request) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .contentType(ContentType.JSON)
      .body(request)
      .post("identity/v1/intuit-dome-account/license-plate");

    Assert.assertEquals(response.getResponse().statusCode(), SC_CREATED,
      response.getResponse().getBody().asString());
    return response.getResponse().as(LicensePlateRelationship.class);
  }

  /**
   * Get staff member account data by staffMemberAccountId
   *
   * @param staffMemberAccountId account ID
   */
  @Step("GET - identity/v1/staff-account/{staffMemberAccountId}")
  public static IntuitDomeAccountDto findStaffMemberAccountByStaffMemberId(
    String staffMemberAccountId) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .pathParam("staffMemberAccountId", staffMemberAccountId)
      .get("identity/v1/staff-account/{staffMemberAccountId}");
    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
    return response.getResponse().as(IntuitDomeAccountDto.class);
  }
}
