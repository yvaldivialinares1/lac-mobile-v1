package com.automation.lac.qa.staffapp.api.tasks;

import com.automation.lac.qa.staffapp.api.models.cms.CountryCodesResponse;
import com.automation.lac.qa.staffapp.api.models.cms.CountryCodesResponse.State;
import com.automation.lac.qa.staffapp.api.services.cms.CmsService;
import java.util.Comparator;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CmsApiTask {

  /**
   * get the list of All available Country Codes for specific country.
   *
   * @param countryName name of country.
   * @return list of State objects
   */
  public List<State> getStateCodesAndStateNamesByCountryName(String countryName) {
    return CmsService.getAllCountryCodesOptions()
      .getData().getCountryCodesConfigByPath().getItem().getCountryCodes()
      .stream().filter(c -> countryName.equals(c.getName()))
      .findFirst().orElse(new CountryCodesResponse.CountryCodes())
      .getStates().stream().sorted(Comparator.comparing(State::getName))
      .toList();
  }
}
