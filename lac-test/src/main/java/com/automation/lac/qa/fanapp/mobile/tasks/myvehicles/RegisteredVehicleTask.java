package com.automation.lac.qa.fanapp.mobile.tasks.myvehicles;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.ADD_VEHICLE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.BACK;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.faker.models.VehicleInfo;
import com.automation.lac.qa.fanapp.mobile.screens.myvehicles.RegisteredVehiclesScreen;
import java.util.List;

public class RegisteredVehicleTask extends RegisteredVehiclesScreen {

  public AddVehicleTask clickAddVehicle() {
    click(getBtnAddVehicle(), ADD_VEHICLE.getValue());
    return new AddVehicleTask();
  }

  /**
   * From the second vehicle that needs to be added it has to be from the RegisteredVehiclesScreen
   * We set the limit to expected vehicles to add
   * Skipping the first as it should have been added on different screen
   *
   * @param numberOfVehicles Limit number of vehicles to add
   * @param vehicleList      List with vehicles info
   */
  public List<VehicleInfo> addNewVehicles(int numberOfVehicles, List<VehicleInfo> vehicleList) {
    vehicleList.stream().filter(v -> !v.isUsed()).limit(numberOfVehicles - 1)
      .forEach(v -> clickAddVehicle().completeAddVehicleProcess(v, vehicleList));
    return vehicleList;
  }

  public void clickBack() {
    click(getBtnBack(), BACK.getValue());
  }
}