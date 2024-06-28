package com.automation.lac.qa.fanapp.api.models.tickets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParkingTicketResponse {
  private int parkingId;
  private String parkingName;
  private String availability;
  private boolean onSale;
  private int parkingDate;
  private int startHour;
  private boolean upComingEvent;
  private String description;
  private String venueId;
  private String venueName;
  private long eventTimeStamp;
  private String hostEventId;
  private int matchingEventId;
  private String matchingEventName;
  private String typeOfEvent;
  private int capacity;
  private int currentAvailability;
  private CmsData cmsData;
}