package com.automation.lac.qa.staffapp.api.models.accesscontrol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ParkingAccessValidationRequest {
  private String licensePlate;
  private String state;
  private String garage;
  private String laneId;
  private String color;
  private String make;
  private int confidence;
  private String eventType;
  private String transactionId;
  private String licensePlateFrontPhoto;
  private String licensePlateBackPhoto;
  private String vehicleFrontPhoto;
  private String vehicleBackPhoto;
}
