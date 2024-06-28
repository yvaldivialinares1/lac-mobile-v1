package com.automation.lac.qa.fanapp.api.models.tickets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingAvailabilityResponse {
  private int parkingEventId;
  private List<Garage> garages;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class Garage {
    private String garageId;
    private List<AvailableSpot> availableSpots;
    private CmsData cmsData;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class AvailableSpot {
      private String type;
      private int available;
      private double price;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CmsData {
      private String modalTitle;
      private String name;
      @JsonProperty("_path")
      private String path;
      private String id;
      private String title;
      private List<BasicInformationItem> basicInformationItems;
      private List<Description> description;

      public List<String> getGarageBasicInformationItems() {
        return getBasicInformationItems().stream().map(basicInformationItem -> basicInformationItem
          .getDescription().getPlaintext()).collect(Collectors.toList());
      }

      public List<String> getGarageDescription() {
        return getDescription().stream().map(Description::getPlaintext)
          .collect(Collectors.toList());
      }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class BasicInformationItem {
      @JsonProperty("_path")
      private String path;
      private Description description;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Description {
      private String plaintext;
    }
  }

  public List<String> getGarageNames() {
    return getGarages().stream().map(garage -> garage.getCmsData().getName())
      .collect(Collectors.toList());
  }
}