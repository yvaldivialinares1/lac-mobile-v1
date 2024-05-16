package com.automation.lac.qa.fanapp.api.models.tickets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketResponse {

  private int maximumAllowed;
  private String availability;
  private boolean onSale;
  private long eventTimeStamp;
  private String opponent;
  private int gameId;
  private int eventId;
  private String mapId;
  private String performer;
  private int currentAvailability;
  private int currentAccessibleAvailability;
  private String hostEventId;
}
