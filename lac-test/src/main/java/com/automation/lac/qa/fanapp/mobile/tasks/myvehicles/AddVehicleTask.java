package com.automation.lac.qa.fanapp.mobile.tasks.myvehicles;

import com.automation.lac.qa.faker.models.userinfo.VehicleInfo;
import java.util.List;

public class AddVehicleTask {

  private final EmptyVehiclesTask emptyVehiclesTask = new EmptyVehiclesTask();
  private final RegisteredVehicleTask registeredVehicleTask = new RegisteredVehicleTask();

  /**
   * Completes add vehicle process by filling the vehicle details
   * and clicking the continue button.
   */
  public void addVehicleInformation(List<VehicleInfo> vehiclesList) {
    for (VehicleInfo vehicle : vehiclesList) {
      if (vehiclesList.indexOf(vehicle) == 0) {
        emptyVehiclesTask.clickAddVehicle();
        emptyVehiclesTask.addVehicle(vehicle);
      } else {
        registeredVehicleTask.clickAddVehicle();
        registeredVehicleTask.addVehicle(vehicle);
      }
      registeredVehicleTask.closeSuccessBannerIfVisible();
    }
  }
}