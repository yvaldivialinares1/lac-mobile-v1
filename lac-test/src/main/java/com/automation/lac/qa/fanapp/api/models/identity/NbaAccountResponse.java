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
public class NbaAccountResponse {
  private String status;
  private Data data;

  @lombok.Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Data {
    private User user;
    private long ttl;
    private String jwt;
    private String refreshToken;
  }

  @lombok.Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class User {
    private String vipType;
    private String created;
    private String lastUpdated;
    private String akamaiRegion;
    private String akamaiEnv;
    private List<String> favoritePlayers;
    private List<String> favoriteTeams;
    private AlternateId alternateIds;
    private EmailProperties emailProperties;
    private PersonalDetails personalDetails;
    private BillingAddress billingAddress;
    private Governance governance;
    private List<String> pushNotificationPreferences;
    private List<String> emailSubscriptionPreferences;
    private List<String> extendedProfile;
    private String email;
    private String givenName;
    private String familyName;
  }

  @lombok.Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class AlternateId {
    private String nbaciamguId;
    private String endeavorCustomerId;
    private String adobeEcId;
    private String amplitudeId;
    private String brazeId;
    private String evergentId;
    private String membershipId;
    private String isOPiNUser;
  }

  @lombok.Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class EmailProperties {
    private Boolean recoveryEmailVerified;
  }

  @lombok.Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class PersonalDetails {
    private String languagePreference;
    private DateOfBirth dob;
  }

  @lombok.Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class BillingAddress {
    private String zip;
  }

  @lombok.Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Governance {
    private int emailCommunicationConsent;
    private Integer partnerSharingConsent;
    private int privacyPolicyAcceptance;
    private String emailCommunicationConsentTimestamp;
    private String partnerSharingConsentTimestamp;
    private String privacyPolicyAcceptanceTimestamp;
    private Integer wnbaEmailCommunicationConsent;
    private String wnbaEmailCommunicationConsentTimestamp;
    private String originLeague;
    private Boolean doNotSell;
  }

  @lombok.Data
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class DateOfBirth {
    private String dobDate;
    private String dobDay;
    private String dobMonth;
    private String dobYear;
  }
}
