package com.automation.lac.qa.faker.models.userinfo;

import java.time.LocalDate;
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
public class PersonalInfo {
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private String phoneNumber;
  private String countryCode;
  private String phoneOtpCountry;
}
