package com.automation.lac.qa.usercreation.models;

import io.qameta.allure.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * This class provides getters and setters for adding and getting the Vehicles result
 */
@Data
@Builder
@AllArgsConstructor
public class VehicleCreationResult {

  private String resultMessage;
  private Status status;
}