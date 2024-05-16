package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.faker.models.VehicleInfo;
import com.automation.lac.qa.fanapp.mobile.screens.myvehicles.RegisteredVehiclesScreen;
import java.util.List;

public class RegisteredVehiclesQuestion extends RegisteredVehiclesScreen {

  /**
   * check that the list of displayed vehicles matches expected list
   */
  public void listOfVehiclesMatchExpected(int numberOfVehicles, List<VehicleInfo> vehicleList) {
    List<VehicleInfo> expected =
      vehicleList.stream().filter(VehicleInfo::isUsed).limit(numberOfVehicles).toList();

    String labelValue = isAndroid()  ? "contentDescription" : "label";

    List<String> collected =
      getLstMyVehicles().stream().map(el -> el.getAttribute(labelValue)).toList();

    for (VehicleInfo vehicleInfo : expected) {
      String plate = vehicleInfo.getLicensePlateState();
      String vehicleUiData = collected.stream().filter(
        c -> c.contains(plate)).findFirst().orElse("");

      getSoftAssert().assertTrue(vehicleUiData.contains(
          vehicleInfo.getVehicleNickname()),
        String.format("Checking vehicle nickname for plate %s is displayed", plate));

      getSoftAssert().assertTrue(vehicleUiData.contains(
          vehicleInfo.getLicensePlateState()),
        String.format("Checking vehicle state for plate %s is displayed", plate));
    }
    getSoftAssert().assertAll();
  }
}
