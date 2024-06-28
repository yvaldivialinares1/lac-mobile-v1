package com.automation.lac.qa.fanapp.api.models.identity;

import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.REGISTER_REASON;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.REGISTER_REASON_CODE;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.REGISTER_SOURCE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "builderWithDefaults")
public class NbaAccountRequestDto {
  private String emailAddress;
  private String password;
  private String displayName;
  private String channel;
  private String leagueTeam;
  private String league;
  private String firstName;
  private String lastName;
  private String dateOfBirth;
  private String country;
  private String postalCode;
  private String emailConsent;
  private String privacyConsent;
  private String privacyConsentTimestamp;
  private String emailConsentTimestamp;
  private CustomSourceDetails customSourceDetails;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class CustomSourceDetails {
    private String registerReason;
    private String registerReasonCode;
    private String registerSource;
  }

  /**
   * Returns a builder for NbaAccountRequestDto with default values for the customSourceDetails
   * field. This ensures that the customSourceDetails object is always initialized with default
   * values for registerReason, registerReasonCode, and registerSource, unless explicitly overridden
   * during the build process.
   *
   * @return an NbaAccountRequestDtoBuilder pre-configured with default values for
   *   customSourceDetails.
   */
  public static NbaAccountRequestDtoBuilder builder() {
    return builderWithDefaults()
      .customSourceDetails(CustomSourceDetails.builder()
        .registerReason(REGISTER_REASON.getText())
        .registerReasonCode(REGISTER_REASON_CODE.getText())
        .registerSource(REGISTER_SOURCE.getText())
        .build());
  }
}

