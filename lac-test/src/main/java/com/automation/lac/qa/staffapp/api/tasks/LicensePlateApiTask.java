package com.automation.lac.qa.staffapp.api.tasks;

import static com.automation.lac.qa.staffapp.api.models.cms.CountryCodesResponse.State;

import com.automation.lac.qa.faker.models.userinfo.VehicleInfo;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDeleteDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.services.identity.IntuitDomeAccountService;
import com.automation.lac.qa.staffapp.api.services.identity.LicensePlateService;
import com.automation.lac.qa.staffapp.api.services.identity.StaffMemberAccountService;
import com.automation.lac.qa.staffapp.api.utils.TestDataUtils;
import com.automation.lac.qa.staffapp.mobile.enums.MemberType;
import java.security.SecureRandom;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class LicensePlateApiTask {

  private final SecureRandom random = new SecureRandom();
  private final String unitedStates = "United States";
  private static List<State> states;

  /**
   * Delete all linked license plates from account
   *
   * @param accountId  String indicating the id of fan account.
   * @param memberType MemberType indication the member type.
   */
  public static void deleteAllLicensePlates(String accountId, MemberType memberType) {
    List<LicensePlateDto> licensePlates = memberType.equals(MemberType.STAFF)
      ? StaffMemberAccountService.findStaffMemberAccountById(accountId).getLicensePlates()
      : IntuitDomeAccountService.findIntuitDomeAccountById(accountId).getLicensePlates();

    if (!licensePlates.isEmpty()) {
      if (memberType.equals(MemberType.STAFF))
        licensePlates.forEach(
          licensePlate -> licensePlate.setMemberType(MemberType.STAFF.getValue()));

      licensePlates.forEach(licensePlate -> {
        licensePlate.setId(accountId);
        LicensePlateDeleteDto dto = LicensePlateDeleteDto.builder()
          .id(licensePlate.getId())
          .licensePlate(licensePlate.getLicensePlate())
          .state(licensePlate.getState())
          .accountId(licensePlate.getId())
          .nickname(licensePlate.getNickname())
          .make(licensePlate.getMake())
          .model(licensePlate.getModel())
          .color(licensePlate.getColor())
          .memberType(licensePlate.getMemberType())
          .build();
        if (memberType.equals(MemberType.STAFF)) {
          licensePlate.setMemberType(MemberType.STAFF.getValue());
          LicensePlateService.deleteLicensePlateV2(dto);
        } else {
          LicensePlateService.deleteLicensePlate(dto);
        }
      });
    }
  }

  /**
   * Register specific amount of vehicles for an account
   *
   * @param accountId        String indicating the id of fan account.
   * @param numberOfVehicles int indication the expected amount of vehicles added to an account.
   * @param memberType       MemberType indication the member type.
   */
  public static void registerLicensePlates(String accountId,
                                           int numberOfVehicles, MemberType memberType) {
    if (states == null || states.isEmpty()) {
      states = CmsApiTask.getStateCodesAndStateNamesByCountryName(unitedStates);
    }

    List<VehicleInfo> randomVehicles = TestDataUtils.createRandomVehicles(numberOfVehicles);

    randomVehicles.forEach(vehicleInfo -> {
      final State state = states.get(random.nextInt(states.size() - 2));
      LicensePlateDto plateDto = LicensePlateDto.builder()
        .licensePlate(vehicleInfo.getLicensePlateNumber())
        .state(state.getCode())
        .intuitDomeAccountId(accountId)
        .nickname(vehicleInfo.getVehicleNickname())
        .make(vehicleInfo.getMake())
        .model(vehicleInfo.getModel())
        .color(vehicleInfo.getColor())
        .memberType(memberType.getValue())
        .build();

      LicensePlateService.registerLicensePlate(plateDto);
    });
  }

  /**
   * Generate licence plate
   *
   * @return LicensePlateDto
   */
  public static LicensePlateDto generateUnknownLicencePlate() {
    if (states == null || states.isEmpty()) {
      states = CmsApiTask.getStateCodesAndStateNamesByCountryName("United States");
    }
    State state = states.get(random.nextInt(states.size() - 2));
    log.info("Current licence plate state testing value is {}", state.getName());
    VehicleInfo vehicleInfo = TestDataUtils.createRandomVehicles(1).get(0);
    return LicensePlateDto.builder()
      .licensePlate(vehicleInfo.getLicensePlateNumber())
      .state(state.getCode())
      .nickname(vehicleInfo.getVehicleNickname())
      .make(vehicleInfo.getMake())
      .model(vehicleInfo.getModel())
      .color(vehicleInfo.getColor())
      .build();
  }

  /**
   * Extract the state name related to specific state code.
   *
   * @param stateCode state code value.
   * @return String state name value
   */
  public static String getStateName(String stateCode) {
    return states.stream().filter(state -> state.getCode().equals(stateCode)).findFirst()
      .orElseThrow().getName();
  }
}
