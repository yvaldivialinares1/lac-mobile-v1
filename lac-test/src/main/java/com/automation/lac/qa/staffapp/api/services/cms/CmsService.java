package com.automation.lac.qa.staffapp.api.services.cms;

import static com.automation.lac.qa.staffapp.constants.ServiceConstants.CMS_URI;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import com.automation.lac.qa.staffapp.api.models.cms.CountryCodesResponse;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

@UtilityClass
public class CmsService {

  /**
   * GET list of All Country Codes
   */
  @Step("GET - getAllCountryCodesConfig")
  public CountryCodesResponse getAllCountryCodesOptions() {
    Response response = new Request()
      .baseUri(CMS_URI)
      .get("getAllCountryCodesConfig");

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      response.getResponse().getBody().asString());
    return response.getResponse().as(CountryCodesResponse.class);
  }
}
