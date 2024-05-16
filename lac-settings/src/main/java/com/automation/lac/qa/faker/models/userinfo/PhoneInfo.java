package com.automation.lac.qa.faker.models.userinfo;

import lombok.Builder;
import lombok.Data;

/**
 * Represents personal information for an individual.
 * This class includes personal details such as name, contact information,
 * and optional vehicle and payment method information.
 * Annotations from the Lombok library (@Getter, @Setter, @Builder)
 * are used to automatically generate getter and setter methods,
 * as well as a builder pattern for creating instances of this class.
 */
@Data
@Builder
public class PhoneInfo {
  private String phoneOtpCountry;
  private String phoneOtpCountryCode;
  private String phoneOtpSenderCode;
}
