package com.automation.lac.qa.fanapp.mobile.tasks.myvehicles;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_VEHICLE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.EXPECTED_STATE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.STATE_DROPDOWN;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.COLOR;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.LICENSE_PLATE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.MAKE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.MODEL;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.SEARCH;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.VEHICLE_NICKNAME;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;

import com.automation.lac.qa.faker.models.VehicleInfo;
import com.automation.lac.qa.fanapp.mobile.screens.myvehicles.AddVehicleScreen;
import java.util.List;

public class AddVehicleTask extends AddVehicleScreen {

  /**
   * Completes add vehicle process by filling the vehicle details
   * and clicking the continue button.
   */
  public List<VehicleInfo> completeAddVehicleProcess(VehicleInfo vehicleInfo,
                                                     List<VehicleInfo> vehiclesList) {
    fillAddVehicleInformation(vehicleInfo);
    click(getBtnAddVehicle(), ADD_VEHICLE.getValue());
    new RegisteredVehicleTask();
    return setVehicleAsUsed(vehiclesList, vehicleInfo);
  }

  /**
   * Fills the add vehicle form with random generated data
   */
  public void fillAddVehicleInformation(VehicleInfo vehicle) {
    sendKeys(getInputVehicleNickname(), vehicle.getVehicleNickname(), VEHICLE_NICKNAME.getValue());
    sendKeys(getInputLicensePlate(), vehicle.getLicensePlateNumber(), LICENSE_PLATE.getValue());
    click(getDdlState(), STATE_DROPDOWN.getValue());
    sendKeys(getInputSearchState(), vehicle.getLicensePlateState(), SEARCH.getValue());
    click(btnExpectedState(vehicle.getLicensePlateState()), EXPECTED_STATE.getValue());
    sendKeys(getInputMake(), vehicle.getMake(), MAKE.getValue());
    sendKeys(getInputModel(), vehicle.getModel(), MODEL.getValue());
    sendKeys(getInputColor(), vehicle.getColor(), COLOR.getValue());
  }

  /**
   * Set vehicle as used
   *
   * @param vehiclesList - vehicle list from test context
   * @param vehicle      - vehicle to be updated
   * @return updated list
   */
  public List<VehicleInfo> setVehicleAsUsed(List<VehicleInfo> vehiclesList,
                                            VehicleInfo vehicle) {
    vehiclesList.remove(vehicle);
    vehicle.setUsed(true);
    vehiclesList.add(vehicle);
    return vehiclesList;
  }
}