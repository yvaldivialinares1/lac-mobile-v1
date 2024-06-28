package com.automation.lac.qa.fanapp.mobile.screens.myvehicles;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class EditVehicleScreen extends VehicleScreen {

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains('Edit, Screen')")
  @iOSXCUITFindBy(accessibility = "navigation_bar_title_label")
  private WebElement txtScreenTitle;

  @AndroidFindBy(uiAutomator = "new UiSelector().resourceId('tfNickName')")
  @iOSXCUITFindBy(iOSNsPredicate =
    "name == 'id_input_vehicle_nickname_id_field' AND type == 'XCUIElementTypeTextField'")
  private WebElement frmInputVehicleNickname;

  @AndroidFindBy(uiAutomator = "new UiSelector().resourceId('tfLicensePlate')")
  @iOSXCUITFindBy(iOSNsPredicate =
    "name == 'id_input_vehicle_license_id_field' AND type == 'XCUIElementTypeTextField'")
  private WebElement frmInputLicensePlate;

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Search')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Search state'")
  private WebElement frmInputSearchState;

  @AndroidFindBy(uiAutomator = "new UiSelector().resourceId('tfMake')")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_vehicle_make_id_field' "
    + "AND type == 'XCUIElementTypeTextField'")
  private WebElement frmInputMake;

  @AndroidFindBy(uiAutomator = "new UiSelector().resourceId('tfModel')")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_vehicle_model_id_field' "
    + "AND type == 'XCUIElementTypeTextField'")
  private WebElement frmInputModel;

  @AndroidFindBy(uiAutomator = "new UiSelector().resourceId('tfColor')")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_vehicle_color_id_field' "
    + "AND type == 'XCUIElementTypeTextField'")
  private WebElement frmInputColor;

  @AndroidFindBy(xpath = "//android.widget.TextView[contains(@content-desc,'Save changes')]/..")
  @iOSXCUITFindBy(accessibility = "primary_rectangle_button")
  private WebElement btnSaveChanges;

  @AndroidFindBy(uiAutomator = "new UiSelector().resourceId('modalContentView')")
  @iOSXCUITFindBy(accessibility = "img_cancel_edition_clippers_fan")
  private WebElement mdlCancelEdit;

  @AndroidFindBy(uiAutomator = "new UiSelector().resourceId('tvPrimaryCta')")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'modal_message_primary_button'")
  private WebElement btnContinueEditing;

  @AndroidFindBy(uiAutomator = "new UiSelector().resourceId('tvSecondaryCta')")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'modal_message_secondary_button'")
  private WebElement btnConfirmCancel;
}