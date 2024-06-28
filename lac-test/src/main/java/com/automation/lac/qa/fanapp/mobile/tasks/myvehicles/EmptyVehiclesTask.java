package com.automation.lac.qa.fanapp.mobile.tasks.myvehicles;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_VEHICLE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.STATE_DROPDOWN;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.COLOR;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.LICENSE_PLATE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.MAKE;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.MODEL;
import static com.automation.lac.qa.fanapp.mobile.enums.InputsDescription.VEHICLE_NICKNAME;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.hideKeyboard;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.faker.models.userinfo.VehicleInfo;
import com.automation.lac.qa.fanapp.mobile.screens.myvehicles.EmptyVehiclesScreen;

public class EmptyVehiclesTask extends EmptyVehiclesScreen {

  public AddVehicleTask clickAddVehicle() {
    click(getBtnAddVehicle(), ADD_VEHICLE.getValue());
    return new AddVehicleTask();
  }

  /**
   * Add Vehicle from widget
   * @param vehicle VehicleInfo
   */
  public void addVehicle(VehicleInfo vehicle) {
    waitForElementVisibility(getAddVehicleWidget().getInputVehicleNickname(), 5);

    sendKeys(getAddVehicleWidget().getInputVehicleNickname(),
            vehicle.getVehicleNickname(), VEHICLE_NICKNAME.getValue());
    sendKeys(getAddVehicleWidget().getInputLicensePlate(),
            vehicle.getLicensePlateNumber(), LICENSE_PLATE.getValue());

    click(getAddVehicleWidget().getDdlState(), STATE_DROPDOWN.getValue());
    getSelectStateWidget().selectState(vehicle.getLicensePlateState());

    sendKeys(getAddVehicleWidget().getInputMake(), vehicle.getMake(), MAKE.getValue());
    sendKeys(getAddVehicleWidget().getInputModel(), vehicle.getModel(), MODEL.getValue());
    sendKeys(getAddVehicleWidget().getInputColor(), vehicle.getColor(), COLOR.getValue());
    hideKeyboard("done");
    click(getAddVehicleWidget().getBtnAddVehicle(), ADD_VEHICLE.getValue());
  }
}