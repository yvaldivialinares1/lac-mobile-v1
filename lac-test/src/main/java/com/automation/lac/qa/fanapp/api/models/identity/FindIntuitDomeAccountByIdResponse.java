package com.automation.lac.qa.fanapp.api.models.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FindIntuitDomeAccountByIdResponse {
  private String memberType;
  private String id;
  private String identityVerificationId;
  private String biometricId;
  private long biometricDate;
  private String gameFaceIdStatus;
  private String ticketingId;
  private long aifiCustomerId;
  private List<String> nfcIds;
  private String email;
  private String firstname;
  private String lastname;
  private String chosenName;
  private long dateOfBirth;
  private String countryPrefix;
  private String phoneNumber;
  private String phone;
  private String address1;
  private String address2;
  private String city;
  private String state;
  private String country;
  private String zipcode;
  private String accountType;
  private List<String> accountStatus;
  private boolean ageVerified;
  private List<LinkedIntuitDomeAccountDto> linkedAccounts;
  private boolean biometricStatus;
  private boolean paymentStatus;
  private boolean credentialStatus;
  private String ageVerificationSource;
  private List<LicensePlatesDto> licensePlates;
  private boolean enabled;
  private AccessBohMetadataDto bohMetadata;
  private long ageVerificationExpirationDate;
  private String roleId;
  private long createdDate;
  private long updatedDate;
  private boolean skippedGameFaceId;
  private boolean skippedAgeVerification;
  private boolean skippedPayment;
  private boolean skippedIdentityPass;
  private String tncVersion;
  private long tncDate;
  private String gameFaceTncVersion;
  private long gameFaceTncDate;
  private String source;
  private boolean inactive;
  private long inactiveDate;
  private long disabledDate;
  private boolean adultCompanionRequired;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class LinkedIntuitDomeAccountDto {
    private String id;
    private String email;
    private String firstname;
    private String lastname;
    private String chosenName;
    private long dateOfBirth;
    private String countryPrefix;
    private String phoneNumber;
    private String phone;
    private String accountType;
    private boolean inactive;
    private long inactiveDate;
    private boolean enabled;
    private long disabledDate;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class LicensePlatesDto {
    private String licensePlate;
    private String color;
    private String nickname;
    private String model;
    private String id;
    private String state;
    private String make;
    private boolean disabledDate;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class AccessBohMetadataDto {
    private String cardholderId;
    private String groupId;
    private String credentialId;
    private String credentialPin;
  }
}