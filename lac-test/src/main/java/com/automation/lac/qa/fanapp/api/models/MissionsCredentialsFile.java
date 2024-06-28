package com.automation.lac.qa.fanapp.api.models;

import static com.automation.lac.qa.fanapp.api.enums.FilesPath.MISSION_CREDENTIALS_PATH;

import com.automation.lac.qa.utils.CustomException;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class representing the structure of the credentialsMissions file with the accounts
 * for each scenario.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MissionsCredentialsFile
  extends AbstractJsonFileReader<List<MissionsCredentialsFile.Account>> {

  private List<Account> accounts;

  @Override
  protected String getFilePath() {
    return MISSION_CREDENTIALS_PATH.getText();
  }

  @Override
  protected TypeReference<List<Account>> getTypeReference() {
    return new TypeReference<List<Account>>() {
    };
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Account {
    private String scenario;
    private String userType;
    private OnboardingSteps onboardingSteps;
    private String userID;
    private String userEmail;
    private String userPassword;
    private PersonalInformation personalInformation;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class OnboardingSteps {
    private boolean gameFace;
    private boolean identityPass;
    private boolean paymentMethod;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class PersonalInformation {
    private String name;
    private String lastName;
    private String preferredName;
    private String dateOfBirth;
    private PhoneNumber phoneNumber;
    private AddressInformation addressInformation;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class PhoneNumber {
    private String countryCode;
    private String number;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class AddressInformation {
    private String addressLine;
    private String appSuiteUnit;
    private String country;
    private String stateProvinceRegion;
    private String city;
    private String zipcode;
  }

  /**
   * Retrieves a valid account for the specified scenario from the credentials file.
   *
   * @param scenario The scenario of the account to retrieve.
   * @return The Account object of the specified scenario from the credentials file.
   * @throws CustomException If no accounts of the specified scenario are available.
   */
  public static Account getValidCredentials(String scenario) {
    List<Account> accounts = new MissionsCredentialsFile().fromJsonFile();
    return accounts.stream()
      .filter(account -> scenario.equals(account.getScenario()))
      .findFirst()
      .orElseThrow(() -> new CustomException("No account available for the scenario: " + scenario));
  }
}
