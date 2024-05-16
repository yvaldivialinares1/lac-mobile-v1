package com.automation.lac.qa.staffapp.api.tasks;

import static com.automation.lac.qa.staffapp.api.models.cms.CountryCodesResponse.State;
import static com.automation.lac.qa.staffapp.constants.ContextConstants.STATE;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.faker.models.VehicleInfo;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDeleteDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.services.identity.IntuitDomeAccountService;
import com.automation.lac.qa.staffapp.api.services.identity.LicensePlateService;
import com.automation.lac.qa.staffapp.api.utils.TestDataUtils;
import java.security.SecureRandom;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LicensePlateApiTask {

  private final SecureRandom random = new SecureRandom();

  /**
   * Delete all linked license plates from account
   *
   * @param accountId String indicating the id of fan account.
   */
  public void deleteAllLicensePlates(String accountId) {
    List<LicensePlateDto> licensePlates =
      IntuitDomeAccountService.findIntuitDomeAccountById(accountId).getLicensePlates();

    if (!licensePlates.isEmpty()) {
      licensePlates.forEach(licensePlate -> licensePlate.setId(accountId));

      licensePlates.forEach(licensePlate -> {
        LicensePlateDeleteDto dto = LicensePlateDeleteDto.builder()
          .id(licensePlate.getId())
          .licensePlate(licensePlate.getLicensePlate())
          .state(licensePlate.getState())
          .accountId(licensePlate.getId())
          .nickname(licensePlate.getNickname())
          .make(licensePlate.getMake())
          .model(licensePlate.getModel())
          .color(licensePlate.getColor())
          .build();

        LicensePlateService.deleteLicensePlate(dto);
      });
    }
  }

  /**
   * Register specific amount of vehicles for an account
   *
   * @param accountId        String indicating the id of fan account.
   * @param numberOfVehicles int indication the expected amount of vehicles added to an account.
   */
  public void registerLicensePlates(String accountId, int numberOfVehicles) {
    List<State> states = CmsApiTask.getStateCodesAndStateNamesByCountryName("United States");
    State state = states.get(random.nextInt(states.size() - 2));
    getTestContext().set(STATE, state);

    List<VehicleInfo> randomVehicles = TestDataUtils.createRandomVehicles(numberOfVehicles);

    randomVehicles.forEach(vehicleInfo -> {
      LicensePlateDto plateDto = LicensePlateDto.builder()
        .licensePlate(vehicleInfo.getLicensePlateNumber())
        .state(state.getCode())
        .intuitDomeAccountId(accountId)
        .nickname(vehicleInfo.getVehicleNickname())
        .make(vehicleInfo.getMake())
        .model(vehicleInfo.getModel())
        .color(vehicleInfo.getColor())
        .build();

      LicensePlateService.registerLicensePlate(plateDto);
    });
  }

  /**
   * Generate licence plate
   *
   * @return LicensePlateDto
   */
  public LicensePlateDto generateUnknownLicencePlate() {
    List<State> states = CmsApiTask.getStateCodesAndStateNamesByCountryName("United States");
    State state = states.get(random.nextInt(states.size() - 2));
    getTestContext().set(STATE, state);
    VehicleInfo vehicleInfo =
      TestDataUtils.createRandomVehicles(1).get(0);
    return LicensePlateDto.builder()
      .licensePlate(vehicleInfo.getLicensePlateNumber())
      .state(state.getCode())
      .nickname(vehicleInfo.getVehicleNickname())
      .make(vehicleInfo.getMake())
      .model(vehicleInfo.getModel())
      .color(vehicleInfo.getColor())
      .build();
  }
}
