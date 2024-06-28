package com.automation.lac.qa.staffapp.api.services.identity;

import static com.automation.lac.qa.staffapp.constants.ServiceConstants.BACK_BASE_URI;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

@UtilityClass
public class StaffMemberAccountService {

  /**
   * GET StaffMemberAccountDto by id
   *
   * @param staffMemberAccountId String indicating the id of fan account.
   * @return IntuitDomeAccountDto
   */
  @Step("GET - identity/v1/staff-account/{staffMemberAccountId}")
  public static IntuitDomeAccountDto findStaffMemberAccountById(String staffMemberAccountId) {
    Response response = new Request()
      .baseUri(BACK_BASE_URI)
      .pathParam("staffMemberAccountId", staffMemberAccountId)
      .get("identity/v1/staff-account/{staffMemberAccountId}");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
    return response.getResponse().as(IntuitDomeAccountDto.class);
  }
}
