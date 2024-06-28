package com.automation.lac.qa.usercreation.models;

import io.qameta.allure.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * This class provides getters and setters for adding and getting the TeamMates result
 * Type.
 */
@Data
@Builder
@AllArgsConstructor
public class TeamMateCreationResult {

  private String resultMessage;
  private Status status;
}