package com.automation.lac.qa.fanapp.api.models.tickets;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountSeatsResponse {
  private int accountId;
  private List<Tickets> tickets;

  @NoArgsConstructor
  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Tickets {
    private int eventId;
    private List<Seats> seats;

    @NoArgsConstructor
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Seats {
      private String sectionName;
      private String rowName;
      private int seatNum;
      private int lastSeat;
      private int numSeats;
    }
  }
}
