package com.automation.lac.qa.fanapp.api.services.identity;

import static com.automation.lac.qa.fanapp.api.enums.identity.AccountCreation.IDENTITY_NBA_URL;
import static com.automation.lac.qa.fanapp.api.enums.identity.AccountCreation.NBA_REGISTER;
import static com.automation.lac.qa.fanapp.api.enums.identity.AccountCreation.NBA_UPDATE;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.fanapp.api.models.identity.NbaAccountRequestDto;
import com.automation.lac.qa.fanapp.api.models.identity.NbaAccountResponse;
import com.automation.lac.qa.fanapp.api.models.identity.UpdateNbaAccountRequest;
import com.automation.lac.qa.fanapp.api.models.identity.UpdateNbaAccountResponse;
import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;

@Slf4j
@UtilityClass
public class CreateAnAccountService {

  /**
   * Register an NBA account using the provided request body.
   *
   * @param requestBody The request body with account data.
   * @return The response containing the account information.
   */
  @Step("POST - identity-dev.nba.com/dev/register")
  public static NbaAccountResponse registerNbaAccount(NbaAccountRequestDto requestBody) {
    Response response =  new Request()
      .baseUri(IDENTITY_NBA_URL.getText())
      .contentType(ContentType.JSON)
      .body(requestBody)
      .post(NBA_REGISTER.getText());
    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
    return response.getResponse().as(NbaAccountResponse.class);
  }

  /**
   * Sends a PUT request to update the NBA account profile.
   *
   * @param requestBody The request body containing the data to update the NBA account profile.
   * @param jwt         The JWT token for authorization.
   * @return An UpdateNbaAccountResponse object containing the response from the update request,
   *         or null if the response status code is not 200.
   */
  @Step("PUT - identity-dev.nba.com/dev/profile")
  public static UpdateNbaAccountResponse updateDataProfile(UpdateNbaAccountRequest requestBody,
                                                           String jwt) {
    Response response = new Request().header("Authorization", "Bearer " + jwt)
      .baseUri(IDENTITY_NBA_URL.getText())
      .contentType(ContentType.JSON)
      .body(requestBody)
      .put(NBA_UPDATE.getText());
    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
    return response.getResponse().as(UpdateNbaAccountResponse.class);
  }
}
