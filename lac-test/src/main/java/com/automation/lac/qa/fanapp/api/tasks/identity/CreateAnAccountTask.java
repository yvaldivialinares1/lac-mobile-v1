package com.automation.lac.qa.fanapp.api.tasks.identity;

import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.CHANNEL;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.COUNTRY;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.EMAIL_CONSENT;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.EXTENSION;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.LEAGUE;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.LEAGUE_TEAM;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.PHONE_DELIVERY_PERMISSION;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.PLUS;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.PRIVACY_CONSENT;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.STATE;
import static com.automation.lac.qa.fanapp.api.enums.identity.NbaStaticData.TYPE;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.fanapp.api.models.identity.NbaAccountRequestDto;
import com.automation.lac.qa.fanapp.api.models.identity.NbaAccountResponse;
import com.automation.lac.qa.fanapp.api.models.identity.UpdateNbaAccountRequest;
import com.automation.lac.qa.fanapp.api.models.identity.UpdateNbaAccountRequest.BillingAddress;
import com.automation.lac.qa.fanapp.api.models.identity.UpdateNbaAccountRequest.ExtendedNameAttributes;
import com.automation.lac.qa.fanapp.api.models.identity.UpdateNbaAccountRequest.PrimaryPhone;
import com.automation.lac.qa.fanapp.api.services.identity.CreateAnAccountService;
import com.automation.lac.qa.utils.CustomException;
import java.time.Instant;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class CreateAnAccountTask {


  /**
   * Registers a new NBA account using the provided user information.
   *
   * @param userInfo       The user information used to register the account.
   * @param nbaAccountData If true, additional NBA account data will be updated.
   * @return The response from the account registration process.
   * @throws CustomException if userInfo is null.
   */
  public static NbaAccountResponse registerNbaAccount(UserInfo userInfo, boolean nbaAccountData) {
    if (userInfo == null) {
      throw new CustomException("UserInfo must not be null.");
    }

    NbaAccountRequestDto requestBody = buildNbaAccountRequest(userInfo);
    NbaAccountResponse nbaAccountResponse = CreateAnAccountService.registerNbaAccount(requestBody);
    if (nbaAccountData) {
      updateNbaAccount(userInfo, nbaAccountResponse.getData().getJwt());
    }

    return nbaAccountResponse;
  }

  /**
   * Builds the NBA account request with the basic account data.
   *
   * @param userInfo The user information used to populate the account data.
   * @return A DTO containing the NBA account request data.
   */
  private static NbaAccountRequestDto buildNbaAccountRequest(UserInfo userInfo) {
    return NbaAccountRequestDto.builder()
      .emailAddress(userInfo.getAccountInfo().getEmail())
      .password(userInfo.getAccountInfo().getPassword())
      .displayName(userInfo.getAccountInfo().getEmailLocalPart())
      .channel(CHANNEL.getText())
      .leagueTeam(LEAGUE_TEAM.getText())
      .league(LEAGUE.getText())
      .firstName(userInfo.getPersonalInfo().getFirstName())
      .lastName(userInfo.getPersonalInfo().getLastName())
      .dateOfBirth(String.valueOf(userInfo.getPersonalInfo().getBirthDate()))
      .country(COUNTRY.getText())
      .postalCode(userInfo.getAddressInfo().getZipCode())
      .emailConsent(EMAIL_CONSENT.getText())
      .privacyConsent(PRIVACY_CONSENT.getText())
      .privacyConsentTimestamp(Instant.now().toString())
      .emailConsentTimestamp(Instant.now().toString())
      .build();
  }

  /**
   * Updates the NBA account with the provided user information and JWT.
   *
   * @param userInfo The user information to update the NBA account with.
   * @param jwt      The JSON Web Token (JWT) for authentication.
   * @throws CustomException if userInfo or jwt is null or empty.
   */
  public static void updateNbaAccount(UserInfo userInfo, String jwt) {
    if (userInfo == null || jwt == null || jwt.isEmpty()) {
      throw new CustomException("UserInfo and JWT must not be null or empty.");
    }

    UpdateNbaAccountRequest requestBody = buildUpdateNbaAccountRequest(userInfo);
    CreateAnAccountService.updateDataProfile(requestBody, jwt);
  }

  private static UpdateNbaAccountRequest buildUpdateNbaAccountRequest(UserInfo userInfo) {
    return UpdateNbaAccountRequest.builder()
      .primaryPhone(buildPrimaryPhone(userInfo))
      .billingAddress(buildBillingAddress(userInfo))
      .extendedNameAttributes(buildExtendedNameAttributes(userInfo))
      .build();
  }

  private static PrimaryPhone buildPrimaryPhone(UserInfo userInfo) {
    String countryPrefix =
      String.format("%s%s", PLUS.getText(), userInfo.getPhoneInfo().getPhoneOtpCountryCode());
    return PrimaryPhone.builder()
      .countryPrefix(countryPrefix)
      .extension(EXTENSION.getText())
      .number(userInfo.getPersonalInfo().getPhoneNumber())
      .phoneDeliveryPermission(PHONE_DELIVERY_PERMISSION.getText())
      .type(TYPE.getText())
      .build();
  }

  private static BillingAddress buildBillingAddress(UserInfo userInfo) {
    return BillingAddress.builder()
      .state(STATE.getText())
      .city(userInfo.getAddressInfo().getCity())
      .addressLine1(userInfo.getAddressInfo().getPrimaryAddress())
      .addressLine2(userInfo.getAddressInfo().getSecondaryAddress())
      .build();
  }

  private static ExtendedNameAttributes buildExtendedNameAttributes(
    UserInfo userInfo) {
    return ExtendedNameAttributes.builder()
      .preferredName(userInfo.getPersonalInfo().getLastName())
      .build();
  }
}