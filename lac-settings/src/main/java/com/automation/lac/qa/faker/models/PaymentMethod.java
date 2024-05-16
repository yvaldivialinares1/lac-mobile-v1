package com.automation.lac.qa.faker.models;

import lombok.Builder;
import lombok.Data;

/**
 * Represents a payment method containing credit card information.
 * This class includes details such as the card number, expiration date,
 * security code (CVV), and the cardholder's name.
 * Annotations from Lombok library (@Getter, @Setter, @Builder)
 * are used to automatically generate getter and setter methods,
 * as well as a builder pattern for creating instances of this class.
 */
@Data
@Builder
public class PaymentMethod {
  private String cardNumber;
  private String expirationDate;
  private String securityCode;
  private String cardholderName;
}
