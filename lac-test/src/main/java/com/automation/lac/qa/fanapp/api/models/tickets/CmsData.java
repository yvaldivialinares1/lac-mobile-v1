package com.automation.lac.qa.fanapp.api.models.tickets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CmsData {
  private String id;
  private String ticketmasterEventName;
  private String ticketmasterSuiteEventName;
  private String ticketmasterParkingEventName;
  private int maximumAllowed;
  private int transactionTimer;
  private int alertTime;
  private int parkingMaximumAllowed;
  private int parkingTransactionTimer;
  private int parkingAlertTimer;
  private String performer;
  private String cmsEventType;
}
