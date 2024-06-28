package com.automation.lac.qa.staffapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.staffapp.constants.ContextConstants.ADULT_INTUIT_DOME_ACCOUNT;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.LICENCE_PLATE;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.MINOR_ADULT_INTUIT_DOME_ACCOUNT;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.MINOR_INTUIT_DOME_ACCOUNT;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.STAFF_MEMBER_ACCOUNT;
import static com.automation.lac.qa.staffapp.constants.ServiceConstants.STAFF_ID;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.enums.FanType;
import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.tasks.BiometricsApiTask;
import com.automation.lac.qa.staffapp.api.tasks.IntuitDomeAccountApiTask;
import com.automation.lac.qa.staffapp.api.tasks.LicensePlateApiTask;
import com.automation.lac.qa.staffapp.api.tasks.StaffMemberAccountApiTask;
import com.automation.lac.qa.staffapp.mobile.enums.MemberType;
import com.automation.lac.qa.utils.CustomException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonsStep {

  /**
   * Initiate a fan account with a specific amount of registered vehicles.
   *
   * @param numberOfVehicles int indication the expected amount of vehicles added to an account.
   */
  @Given("a fan with a {int} registered vehicle data")
  public void fanWithSpecificAmountOfRegisteredVehicles(int numberOfVehicles) {
    String id = IntuitDomeAccountApiTask.registerNewIntuitDomeAccount(FanType.ADULT)
      .getIntuitDomeAccount().getId();

    LicensePlateApiTask.registerLicensePlates(id, numberOfVehicles, MemberType.FAN);

    IntuitDomeAccountDto intuitDomeAccount =
      IntuitDomeAccountApiTask.getUpdatedIntuitDomeAccountApiData(id);

    getTestContext().set(LICENCE_PLATE, intuitDomeAccount.getLicensePlates().get(0));
    getTestContext().set(ADULT_INTUIT_DOME_ACCOUNT, intuitDomeAccount);
  }

  /**
   * Initiate a fan account with a specific amount of registered vehicles.
   *
   * @param numberOfVehicles int indication the expected amount of vehicles added to an account.
   */
  @Given("a staff with a {int} registered vehicle data")
  public void staffWithSpecificAmountOfRegisteredVehicles(int numberOfVehicles) {
    LicensePlateApiTask.deleteAllLicensePlates(STAFF_ID, MemberType.STAFF);
    LicensePlateApiTask.registerLicensePlates(STAFF_ID, numberOfVehicles, MemberType.STAFF);

    IntuitDomeAccountDto staffMemberAccount =
      StaffMemberAccountApiTask.getUpdatedStaffMemberAccountApiData(STAFF_ID);
    getTestContext().set(LICENCE_PLATE, staffMemberAccount.getLicensePlates().get(0));
    getTestContext().set(STAFF_MEMBER_ACCOUNT, staffMemberAccount);
  }

  /**
   * Initiate a fan account of specific type.
   */
  @Given("the following fan accounts exist:")
  public void fanAccountCreation(DataTable dataTable) {
    List<Map<String, String>> accounts = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> account : accounts) {
      String fanAccountType = account.get("type");
      String id =
        IntuitDomeAccountApiTask.registerNewIntuitDomeAccount(FanType.valueOf(fanAccountType))
          .getIntuitDomeAccount().getId();
      IntuitDomeAccountDto intuitDomeAccount =
        IntuitDomeAccountApiTask.getUpdatedIntuitDomeAccountApiData(id);

      String accountType = getContextAccountType(fanAccountType);
      getTestContext().set(accountType, intuitDomeAccount);
      if (Boolean.parseBoolean(account.get("identity_completed"))) {
        BiometricsApiTask.registerIntuitDomeAccountBiometrics(intuitDomeAccount.getId());
        IntuitDomeAccountApiTask.manualAgeValidation(intuitDomeAccount.getId());
      }
      String vehicles = account.get("registered_vehicles");
      if (vehicles != null && Integer.parseInt(vehicles) > 0) {
        LicensePlateApiTask.registerLicensePlates(intuitDomeAccount.getId(),
          Integer.parseInt(vehicles), MemberType.STAFF);
        IntuitDomeAccountApiTask.updateFanAccountContextData(fanAccountType);
      }
      if (Boolean.parseBoolean(account.get("wearable_linked"))) {
        IntuitDomeAccountApiTask.createNfcRelationship(intuitDomeAccount.getId());
        IntuitDomeAccountApiTask.updateFanAccountContextData(fanAccountType);
      }
      if (Boolean.parseBoolean(account.get("game_face_id"))) {
        BiometricsApiTask.registerIntuitDomeAccountBiometrics(intuitDomeAccount.getId());
        IntuitDomeAccountApiTask.updateFanAccountContextData(fanAccountType);
      }
      String teammatesNumber = account.get("teammates_linked");
      if (teammatesNumber != null && Integer.parseInt(teammatesNumber) > 0) {
        IntStream.range(0, Integer.parseInt(teammatesNumber)).forEach(
          iteration -> IntuitDomeAccountApiTask.registerNewMiniIntuitDomeAccount(
            intuitDomeAccount.getId()));
        IntuitDomeAccountApiTask.updateFanAccountContextData(fanAccountType);
      }
      if (Boolean.parseBoolean(account.get("teammates_wearable_linked"))) {
        IntuitDomeAccountDto intuitDomeAccountUpdated = getTestContext().get(accountType);
        intuitDomeAccountUpdated.getLinkedAccounts().forEach(
          teammate -> IntuitDomeAccountApiTask.createNfcRelationship(teammate.getId()));
        IntuitDomeAccountApiTask.updateFanAccountContextData(fanAccountType);
      }
    }
  }

  /**
   * Generate not linked licence plate.
   */
  @Given("there is a car with unknown licence plate")
  public void carWithUnknownLicencePlate() {
    LicensePlateDto licensePlateDto = LicensePlateApiTask.generateUnknownLicencePlate();
    getTestContext().set(LICENCE_PLATE, licensePlateDto);
  }

  /**
   * Identify context fan account type.
   */
  public static String getContextAccountType(String fanAccountType) {
    FanType fanType = FanType.valueOf(fanAccountType);
    String accountType;
    switch (fanType) {
      case ADULT -> accountType = ADULT_INTUIT_DOME_ACCOUNT;
      case MINOR_ADULT -> accountType = MINOR_ADULT_INTUIT_DOME_ACCOUNT;
      case YOUNG_ADULT -> accountType = MINOR_INTUIT_DOME_ACCOUNT;
      default -> throw new CustomException("There is no option for a fan of type :" + fanType);
    }
    return accountType;
  }

  /**
   * Get context fan account data.
   *
   * @param fanAccountType account type
   * @return IntuitDomeAccountDto account data.
   */
  public static IntuitDomeAccountDto getFanAccountContextDataByAccountType(String fanAccountType) {
    String accountType = getContextAccountType(fanAccountType);
    return getTestContext().get(accountType);
  }
}