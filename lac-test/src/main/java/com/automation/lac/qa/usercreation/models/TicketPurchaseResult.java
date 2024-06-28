package com.automation.lac.qa.usercreation.models;

import io.qameta.allure.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * This class provides getters and setters for adding and getting the ticket purchase result
 */
@Data
@Builder
@AllArgsConstructor
public class TicketPurchaseResult {

  private Status status;
}