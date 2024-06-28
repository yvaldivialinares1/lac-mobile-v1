package com.automation.lac.qa.staffapp.api.tasks;

import static com.automation.lac.qa.staffapp.api.models.cms.ClosingIssueResponse.Item;

import com.automation.lac.qa.staffapp.api.models.cms.CountryCodesResponse;
import com.automation.lac.qa.staffapp.api.models.cms.CountryCodesResponse.State;
import com.automation.lac.qa.staffapp.api.services.cms.CmsService;
import com.automation.lac.qa.staffapp.mobile.enums.QueueEventType;
import com.automation.lac.qa.utils.CustomException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CmsApiTask {

  /**
   * get the list of All available Country Codes for specific country.
   * //TODO: remove exception of devault value 'California' when dev team update the UI structure
   *
   * @param countryName name of country.
   * @return list of State objects
   */
  public static List<State> getStateCodesAndStateNamesByCountryName(String countryName) {
    return CmsService.getAllCountryCodesOptions()
      .getData().getCountryCodesConfigByPath().getItem().getCountryCodes()
      .stream().filter(c -> countryName.equals(c.getName()))
      .findFirst().orElse(new CountryCodesResponse.CountryCodes())
      .getStates().stream().limit(5).sorted(Comparator.comparing(State::getName))
      .filter(state -> !state.getName().equals("California"))
      .toList();
  }

  /**
   * GET list of finish issue options for specific queue event.
   * TODO: When cms date will be correctly set from development side - update method.
   *
   * @param eventType QueueEventType type.
   * @return list of State objects
   */
  public static List<String> getListOfFinishIssueOptionsFromCms(QueueEventType eventType) {

    Stream<Item> stream = CmsService.getClosingIssuePreloadedOptions().getData()
      .getClosingIssueReasonsList().getItems().stream();
    switch (eventType) {
      case NO_PARKING_TICKET, PLATE_NOT_PROPERLY_READ, UNKNOWN_PLATE:
        return stream
          .filter(i -> i.getClosingIssueTitle().contains("wrongParking")).map(
            Item::getIssueType).toList().get(0);
      default:
        throw new CustomException("Unexpected value: " + eventType);
    }
  }
}
