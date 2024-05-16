package com.automation.lac.qa.staffapp.api.models.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntuitDomeAccountDto {
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
  private boolean banned;
  private String banReason;
  private long banExpirationDate;
  private List<LinkedAccount> linkedAccounts;
  private boolean biometricStatus;
  private boolean paymentStatus;
  private boolean credentialStatus;
  private String ageVerificationSource;
  private List<LicensePlateDto> licensePlates;
  private boolean enabled;
  private long ageVerificationExpirationDate;
  private String roleId;
  private long createdDate;
  private long updatedDate;
  private boolean skippedGameFaceId;
  private boolean skippedAgeVerification;
  private boolean skippedPayment;
  private boolean skippedIdentityPass;
  private boolean adultCompanionRequired;
  private boolean isInUse;
}
