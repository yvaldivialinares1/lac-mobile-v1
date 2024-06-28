package com.automation.lac.qa.staffapp.mobile.screens.fanprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.RemoveVehicleAlertComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

/**
 * Responsible to add/edit vehicle screen.
 */
@Getter
public class ManageVehicleScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "vehicle_details_close_button")
  private WebElement btnClose;

  @iOSXCUITFindBy(id = "vehicle_nickname_inputfield")
  private WebElement frmInputVehicleNickName;

  @iOSXCUITFindBy(id = "license_nickname_value")
  private WebElement vehicleNickNameValue;

  @iOSXCUITFindBy(id = "vehicle_make_inputfield")
  private WebElement frmInputVehicleMake;

  @iOSXCUITFindBy(id = "make_value")
  private WebElement vehicleMakeValue;

  @iOSXCUITFindBy(id = "vehicle_model_inputfield")
  private WebElement frmInputVehicleModel;

  @iOSXCUITFindBy(id = "model_value")
  private WebElement vehicleModelValue;

  @iOSXCUITFindBy(id = "vehicle_color_inputfield")
  private WebElement frmInputVehicleColor;

  @iOSXCUITFindBy(id = "color_value")
  private WebElement vehicleColorValue;

  @iOSXCUITFindBy(id = "vehicle_state_inputfield")
  private WebElement frmInputVehicleState;

  @iOSXCUITFindBy(id = "state_value")
  private WebElement vehicleStateValue;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'state_name'")
  private List<WebElement> dropDownStates;

  @iOSXCUITFindBy(id = "vehicle_licensePlate_inputfield")
  private WebElement frmInputVehiclePlate;

  @iOSXCUITFindBy(id = "license_plate_value")
  private WebElement vehiclePlateValue;

  @iOSXCUITFindBy(iOSNsPredicate =
    "name == 'fan_identity_add_vehicle_text' AND type == 'XCUIElementTypeButton'")
  private WebElement btnAddVehicle;

  @iOSXCUITFindBy(id = "vehicleDetails_edit_button")
  private WebElement btnEditVehicle;

  @iOSXCUITFindBy(id = "edit_vehicle_submit_button")
  private WebElement btnApplyChanges;

  @iOSXCUITFindBy(id = "Remove vehicle")
  private WebElement btnRemoveVehicle;

  private RemoveVehicleAlertComponent removeVehicleAlertComponent;
}
