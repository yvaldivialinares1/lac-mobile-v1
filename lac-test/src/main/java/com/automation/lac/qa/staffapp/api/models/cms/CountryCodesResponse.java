package com.automation.lac.qa.staffapp.api.models.cms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CountryCodesResponse {
  private Data data;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class Data {
    private CountryCodesConfigByPath countryCodesConfigByPath;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class CountryCodesConfigByPath {
    private Item item;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class Item {
    private List<CountryCodes> countryCodes;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class CountryCodes {
    private String name;
    private List<State> states;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class State {
    private String name;
    private String code;
  }
}
