package com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class FanProfileAccessVehiclesComponent extends Widget {

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Vehicles' AND type == 'XCUIElementTypeButton' "
    + "OR name CONTAINS 'fan_vehicle_label'")
  private WebElement lblFanVehicles;

  @iOSXCUITFindBy(id = "fan_identity_vehicle_view_all_button")
  private WebElement btnViewAllVehicles;

  @iOSXCUITFindBy(id = "ADD VEHICLE")
  private WebElement btnAddVehicle;

  @iOSXCUITFindBy(id = "vehicle_info_result_list")
  private List<VehicleInfoComponent> vehiclesList;

  protected FanProfileAccessVehiclesComponent(WebElement element) {
    super(element);
  }
}
