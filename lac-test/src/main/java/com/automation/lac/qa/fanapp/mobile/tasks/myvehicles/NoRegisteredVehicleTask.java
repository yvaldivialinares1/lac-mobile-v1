package com.automation.lac.qa.fanapp.mobile.tasks.myvehicles;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_VEHICLE;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.fanapp.mobile.screens.myvehicles.NoRegisteredVehiclesScreen;

public class NoRegisteredVehicleTask extends NoRegisteredVehiclesScreen {

  public AddVehicleTask clickAddVehicle() {
    click(getBtnAddVehicle(), ADD_VEHICLE.getValue());
    return new AddVehicleTask();
  }
}