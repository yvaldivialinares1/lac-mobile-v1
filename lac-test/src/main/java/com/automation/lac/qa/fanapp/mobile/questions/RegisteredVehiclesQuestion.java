package com.automation.lac.qa.fanapp.mobile.questions;

import static com.automation.lac.qa.assertions.SoftAssertManager.assertAll;
import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;

import com.automation.lac.qa.faker.models.userinfo.VehicleInfo;
import com.automation.lac.qa.fanapp.mobile.screens.myvehicles.RegisteredVehiclesScreen;
import java.util.List;

public class RegisteredVehiclesQuestion extends RegisteredVehiclesScreen {

  /**
   * check that the list of displayed vehicles matches expected list
   */
  public void listOfVehiclesMatchExpected(List<VehicleInfo> vehicleList) {
    String labelValue = isAndroid() ? "contentDescription" : "label";

    List<String> collected =
      getLstMyVehicles().stream().map(el -> el.getAttribute(labelValue)).toList();

    for (VehicleInfo vehicleInfo : vehicleList) {
      String nickname = vehicleInfo.getVehicleNickname();
      String plate = vehicleInfo.getLicensePlateState();
      String vehicleUiData = collected.stream().filter(
        c -> c.contains(plate)).findFirst().orElse("");

      getSoftAssert().assertTrue(vehicleUiData.contains(vehicleInfo.getLicensePlateNumber()),
        String.format("Checking vehicle plate for vehicle %s is correct", nickname));

      getSoftAssert().assertTrue(vehicleUiData.contains(vehicleInfo.getLicensePlateState()),
        String.format("Checking vehicle state for vehicle %s is correct", nickname));
    }
    assertAll();
  }

  /**
   * Gets the list of TeamMate to validate if they were added correctly
   *
   * @return an Integer
   */
  public Integer getTheAddedVehiclesNumber() {
    try {
      return getLstMyVehicles().size();
    } catch (Exception e) {
      return 0;
    }
  }
}
