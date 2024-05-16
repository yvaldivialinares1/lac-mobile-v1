package com.automation.lac.qa.faker.models;

import lombok.Builder;
import lombok.Data;

/**
 * Represents information about a vehicle.
 * This class includes details such as the vehicle's nickname,
 * license plate number and state, make, model, and color.
 * Annotations from the Lombok library (@Getter, @Setter, @Builder)
 * are used to automatically generate getter and setter methods,
 * as well as a builder pattern for creating instances of this class.
 */
@Data
@Builder
public class VehicleInfo {
  private String vehicleNickname;
  private String licensePlateNumber;
  private String licensePlateState;
  private String make;
  private String model;
  private String color;
  private boolean isElectric;
  private boolean used;
}
