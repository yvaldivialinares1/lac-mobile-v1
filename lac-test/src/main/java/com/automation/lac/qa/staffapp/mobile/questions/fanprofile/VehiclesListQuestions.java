package com.automation.lac.qa.staffapp.mobile.questions.fanprofile;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.staffapp.mobile.stepsdefinitions.CommonsStep.getFanAccountContextDataByAccountType;

import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.api.models.identity.LicensePlateDto;
import com.automation.lac.qa.staffapp.api.tasks.IntuitDomeAccountApiTask;
import com.automation.lac.qa.staffapp.api.tasks.LicensePlateApiTask;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.VehiclesListScreen;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.VehicleInfoComponent;
import io.qameta.allure.Step;
import java.util.List;

public class VehiclesListQuestions extends VehiclesListScreen {

  private static final String LABEL = "label";

  /**
   * Validate if the fan profile vehicles list is properly displayed.
   *
   * @param fanAccountType type of account
   */
  @Step("checking fan profile vehicle list is properly displayed")
  public void theFanProfileVehiclesListIsProperlyDisplayed(String fanAccountType) {
    IntuitDomeAccountDto account = getFanAccountContextDataByAccountType(fanAccountType);
    IntuitDomeAccountDto updatedAccount =
      IntuitDomeAccountApiTask.getUpdatedIntuitDomeAccountApiData(account.getId());
    List<LicensePlateDto> accoundVehiclesList = updatedAccount.getLicensePlates();

    String vehiclesCountLabel = getLblFanVehicles().getAttribute(LABEL);
    getSoftAssert().assertTrue(
      vehiclesCountLabel.contains(String.valueOf(accoundVehiclesList.size())),
      "Vehicles list label display correct fan vehicles count");

    List<VehicleInfoComponent> screenVehiclesList = getVehiclesList();
    getSoftAssert().assertEquals(accoundVehiclesList.size(), screenVehiclesList.size(),
      "Expected amount of vehicles notes is displayed");

    for (int i = 0; i < accoundVehiclesList.size(); i++) {
      VehicleInfoComponent vehicleScreenNote = screenVehiclesList.get(i);
      LicensePlateDto vehicleApiNote = accoundVehiclesList.get(i);
      final String state = LicensePlateApiTask.getStateName(vehicleApiNote.getState());
      getSoftAssert().assertEquals(
        vehicleScreenNote.getLblVehicleNickname().getAttribute(LABEL),
        vehicleApiNote.getNickname(),
        "Licence nickname correctly displayed on vehicles list screen as: "
          + vehicleApiNote.getNickname());
      getSoftAssert().assertTrue(vehicleScreenNote.getLblVehicleRowData().getAttribute(LABEL)
          .contains(vehicleApiNote.getLicensePlate()),
        "Vehicles note contains expected plate number on vehicles list screen as "
          + vehicleApiNote.getLicensePlate());
      getSoftAssert().assertTrue(
        vehicleScreenNote.getLblVehicleRowData().getAttribute(LABEL).contains(state),
        "Vehicles note contains expected plate number");
    }
  }

  /**
   * Validate if the fan profile vehicles list is properly displayed.
   *
   * @param staffAccountId type of account
   */
  public void theStaffProfileVehiclesListIsProperlyDisplayed(String staffAccountId) {
    IntuitDomeAccountDto updatedAccount =
      IntuitDomeAccountApiTask.findStaffMemberAccountByStaffMemberId(staffAccountId);
    List<LicensePlateDto> accoundVehiclesList = updatedAccount.getLicensePlates();

    String vehiclesCountLabel = getLblStaffVehicle().getAttribute(LABEL);
    getSoftAssert().assertTrue(
      vehiclesCountLabel.contains(String.valueOf(accoundVehiclesList.size())),
      "Vehicles list label display correct fan vehicles count");

    List<VehicleInfoComponent> screenVehiclesList = getVehiclesList();
    getSoftAssert().assertEquals(accoundVehiclesList.size(), screenVehiclesList.size(),
      "Expected amount of vehicles notes is displayed");

    for (int i = 0; i < accoundVehiclesList.size(); i++) {
      VehicleInfoComponent vehicleScreenNote = screenVehiclesList.get(i);
      LicensePlateDto vehicleApiNote = accoundVehiclesList.get(i);
      final String state = LicensePlateApiTask.getStateName(vehicleApiNote.getState());
      getSoftAssert().assertEquals(
        vehicleScreenNote.getLblVehicleNickname().getAttribute(LABEL),
        vehicleApiNote.getNickname(),
        "Licence nickname correctly displayed on vehicles list screen as: "
          + vehicleApiNote.getNickname());
      getSoftAssert().assertTrue(vehicleScreenNote.getLblVehicleRowData().getAttribute(LABEL)
          .contains(vehicleApiNote.getLicensePlate()),
        "Vehicles note contains expected plate number on vehicles list screen as "
          + vehicleApiNote.getLicensePlate());
      getSoftAssert().assertTrue(
        vehicleScreenNote.getLblVehicleRowData().getAttribute(LABEL).contains(state),
        "Vehicles note contains expected plate number");
    }
  }

  /**
   * validate if correct banner message is displayed.
   */
  public void correctBannerMessageIsDisplayed(String message) {
    getSoftAssert().assertEquals(
      getBannerMessageComponent().getBannerMessageText(), message,
      "Banner message contains expected text as " + message);
    getSoftAssert().assertTrue(getBannerMessageComponent().isBannerMessageDisappearedInFewSeconds(),
      "Banner message disappeared in few seconds");
  }
}
