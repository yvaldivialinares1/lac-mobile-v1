package com.automation.lac.qa.usercreation.models;

import io.qameta.allure.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * This class provides getters and setters for adding and getting the user creation result.
 */
@Data
@Builder
@AllArgsConstructor
public class UserCreationResult {

  private String email;
  private String password;
  private Status status;
  private Status accountCreationResult;
  private Status phoneVerificationResult;
  private Status gameFaceIdResult;
  private Status identityPassResult;
  private Status paymentMethodResult;
  private TeamMateCreationResult teamMateCreationResult;
  private VehicleCreationResult vehicleCreationResult;
  private TicketPurchaseResult ticketPurchaseResult;
}