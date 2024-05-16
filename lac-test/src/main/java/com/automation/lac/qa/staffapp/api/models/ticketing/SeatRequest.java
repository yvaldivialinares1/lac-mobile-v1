package com.automation.lac.qa.staffapp.api.models.ticketing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class SeatRequest {
  private int eventId;
  private String section;
  private String row;
  private String seat;
}
