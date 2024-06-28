package com.automation.lac.qa.staffapp.api.tasks;

import static com.automation.lac.qa.staffapp.mobile.stepsdefinitions.CommonsStep.getContextAccountType;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.staffapp.api.models.identity.AccountRegistrationResponse;
import com.automation.lac.qa.staffapp.api.models.identity.CreateMiniIntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.ManualAgeValidationDto;
import com.automation.lac.qa.staffapp.api.models.identity.NewIntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.NfcRelationship;
import com.automation.lac.qa.staffapp.api.services.identity.IntuitDomeAccountService;
import com.automation.lac.qa.staffapp.api.services.identity.LicensePlateService;
import com.automation.lac.qa.staffapp.api.utils.TestDataUtils;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IntuitDomeAccountApiTask {

  /**
   * Register new intuit Dome Account
   *
   * @param type The type of fan which determines the age range for the birthdate.
   * @return AccountRegistrationResponse data
   */
  public static AccountRegistrationResponse registerNewIntuitDomeAccount(FanType type) {
    NewIntuitDomeAccountDto randomNewIntuitDomeAccount =
      TestDataUtils.createRandomNewIntuitDomeAccountDto(type);

    return IntuitDomeAccountService.registerNewIntuitDomeAccount(randomNewIntuitDomeAccount);
  }

  /**
   * Register new intuit Dome Account
   *
   * @param accountId id of parent account.
   * @return AccountRegistrationResponse data
   */
  public static IntuitDomeAccountDto registerNewMiniIntuitDomeAccount(String accountId) {
    CreateMiniIntuitDomeAccountDto randomNewIntuitDomeAccount =
      TestDataUtils.createRandomCreateMiniIntuitDomeAccountDto(accountId);

    return IntuitDomeAccountService.createMiniIntuitDomeAccount(randomNewIntuitDomeAccount);
  }

  /**
   * Register new nfc relationship for a fan account.
   *
   * @param accountId id of parent account.
   * @return NfcRelationship data
   */
  public static NfcRelationship createNfcRelationship(String accountId) {
    NfcRelationship randomNewIntuitDomeAccount =
      TestDataUtils.createRandomNfcRelationship(accountId);
    return IntuitDomeAccountService.createNfcRelationship(randomNewIntuitDomeAccount);
  }

  /**
   * Provide manual age validation.
   *
   * @param accountId id of account.
   * @return IntuitDomeAccountDto data
   */
  public static IntuitDomeAccountDto manualAgeValidation(String accountId) {
    Instant instant = Instant.ofEpochSecond(Instant.now().getEpochSecond());
    ZonedDateTime laTime = instant.atZone(ZoneId.of("America/Los_Angeles")).plusDays(5);

    ManualAgeValidationDto request =
      ManualAgeValidationDto.builder().intuitDomeAccountId(accountId)
      .ageVerified(true)
      .ageVerificationExpirationDate(laTime.toInstant().getEpochSecond())
      .build();

    return IntuitDomeAccountService.manualAgeValidation(request);
  }

  /**
   * Retrieves latest update IntuitDomeAccount data from API.
   *
   * @param accountId String indicating the id of fan account.
   * @return IntuitDomeAccountDto
   */
  public static IntuitDomeAccountDto getUpdatedIntuitDomeAccountApiData(String accountId) {
    return IntuitDomeAccountService.findIntuitDomeAccountById(accountId);
  }

  /**
   * Retrieves Licence Plats detail from API
   *
   * @param staffMemberAccountId String indicating the id of fan account.
   * @return IntuitDomeAccountDto
   */
  public static IntuitDomeAccountDto findStaffMemberAccountByStaffMemberId(
    String staffMemberAccountId) {
    return LicensePlateService.findStaffMemberAccountByStaffMemberId(staffMemberAccountId);
  }

  /**
   * Update fan account context data.
   *
   * @param fanAccountType String indicating the id of fan account.
   */
  public static void updateFanAccountContextData(String fanAccountType) {
    final String accountType = getContextAccountType(fanAccountType);
    final IntuitDomeAccountDto intuitDomeAccount = getTestContext().get(accountType);
    IntuitDomeAccountDto intuitDomeAccountUpdated =
      IntuitDomeAccountApiTask.getUpdatedIntuitDomeAccountApiData(intuitDomeAccount.getId());
    getTestContext().set(accountType, intuitDomeAccountUpdated);
  }
}
