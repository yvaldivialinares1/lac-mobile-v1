package com.automation.lac.qa.fanapp.api.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Represents information about a payment card.
 * This class provides getters and setters for accessing and modifying card details.
 */
@Builder
@Data
@AllArgsConstructor
public class CardInfo {
  private String cardNumber;
  private String expiryDate;
  private String securityCode;
  private String nameOnCard;
}
