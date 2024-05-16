package com.automation.lac.qa.fanapp.api.models.tickets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuggestedSeatsResponse {

  private int eventId;
  private List<SuggestedTickets> suggestedTickets;

  @JsonIgnoreProperties(ignoreUnknown = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  public static class SuggestedTickets {
    private boolean seatsTogether;
    private boolean seatsTogetherBool;
    private String sectionName;
    private String rowName;
    private double price;
    private int totalTickets;
    private List<Tickets> tickets;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Tickets {
      private int firstSeat;
      private int lastSeat;
      private int numSeat;
    }
  }
}