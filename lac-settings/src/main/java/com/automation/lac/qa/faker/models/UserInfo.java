package com.automation.lac.qa.faker.models;

import com.automation.lac.qa.faker.models.userinfo.AccountInfo;
import com.automation.lac.qa.faker.models.userinfo.AddressInfo;
import com.automation.lac.qa.faker.models.userinfo.PersonalInfo;
import com.automation.lac.qa.faker.models.userinfo.PhoneInfo;
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
public class UserInfo {
  private PersonalInfo personalInfo;
  private AddressInfo addressInfo;
  private AccountInfo accountInfo;
  private PhoneInfo phoneInfo;
  private AddressInfo billingAddress;
}
