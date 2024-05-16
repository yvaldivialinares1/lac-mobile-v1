package com.automation.lac.qa.fanapp.mobile.screens.myvehicles;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class VehicleDetailsScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains('Vehicle Nickname')")
  @iOSXCUITFindBy(accessibility = "id_label_my_vehicle_details_nickname_title")
  private WebElement lblVehicleNickname;

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains('License Plate')")
  @iOSXCUITFindBy(accessibility = "id_label_my_vehicle_details_license_title")
  private WebElement lblLicensePlate;

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains('State')")
  @iOSXCUITFindBy(accessibility = "id_label_my_vehicle_details_state_title")
  private WebElement lblState;

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains('Make')")
  @iOSXCUITFindBy(accessibility = "id_label_my_vehicle_details_make_title")
  private WebElement lblMake;

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains('Model')")
  @iOSXCUITFindBy(accessibility = "id_label_my_vehicle_details_model_title")
  private WebElement lblModel;

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains('Color')")
  @iOSXCUITFindBy(accessibility = "id_label_my_vehicle_details_color_title")
  private WebElement lblColor;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit']/..")
  @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name == 'Edit'")
  private WebElement btnEditVehicle;

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains('Delete vehicle')")
  @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name == 'Delete vehicle'")
  private WebElement btnDeleteVehicle;

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains('Back')")
  @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name == 'Back'")
  private WebElement btnBack;

  @AndroidFindBy(uiAutomator = "new UiSelector().resourceId('tvModalTitle')")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'modal_message_title'")
  private WebElement modalDeleteVehicle;

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains('delete')")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'modal_message_primary_button'")
  private WebElement btnConfirmDelete;

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains('cancel')")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'modal_message_secondary_button'")
  private WebElement btnCancelDelete;

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains"
    + "('Your vehicle has been edited')")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'banner_message_view' AND "
    + "label == 'Your vehicle has been edited.'")
  private WebElement vehicleEditedSuccessMessage;

  @AndroidFindBy(id = "ivClose")
  @iOSXCUITFindBy(iOSNsPredicate = "label == 'Close'")
  private WebElement closeSuccessMessage;
}