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
  public int accountId;
  public List<Tickets> tickets;

  @NoArgsConstructor
  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Tickets {
    public int eventId;
    public List<Seats> seats;

    @NoArgsConstructor
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Seats {
      public String sectionName;
      public String rowName;
      public int seatNum;
      public int lastSeat;
      public int numSeats;

    }
  }

}
