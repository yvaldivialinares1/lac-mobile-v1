package com.automation.lac.qa.staffapp.api.models.ticketing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MockRedeemRequestV2 {
  private String result;
  private String resultMessage;
  private int redeemDateTime;
  private String location;
  private int numberOfMinorsToRedeem;
  private int eventId;
  private String ticketType;
  private List<SeatRequest> tickets;
  private boolean isDependent;
  @JsonProperty("accountId")
  private int ticketingId;
}
