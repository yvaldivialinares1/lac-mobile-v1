package com.automation.lac.qa.fanapp.mobile.screens.myvehicles;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AddVehicleWidget extends Widget {

  public AddVehicleWidget(WebElement element) {
    super(element);
  }

  @AndroidFindBy(uiAutomator = "textContains('indicates a required field')")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'indicates a required field'")
  private WebElement msgRequiredFields;

  @AndroidFindBy(uiAutomator = "resourceId(\"tfNickName\")")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'NICKNAME' AND type CONTAINS 'TextField'")
  private WebElement inputVehicleNickname;

  @AndroidFindBy(id = "tfLicensePlate")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'LICENSE PLATE' AND type CONTAINS 'TextField'")
  private WebElement inputLicensePlate;

  @AndroidFindBy(uiAutomator = "textContains('Plate already added')")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'Plate already added'")
  private WebElement lblLicensePlateError;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"State, required\")")
  @iOSXCUITFindBy(iOSNsPredicate = "label == 'STATE*'")
  private WebElement ddlState;

  @AndroidFindBy(id = "tfMake")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'MAKE' AND type == 'XCUIElementTypeTextField'")
  private WebElement inputMake;

  @AndroidFindBy(id = "tfModel")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'MODEL' AND type == 'XCUIElementTypeTextField'")
  private WebElement inputModel;

  @AndroidFindBy(id = "tfColor")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'COLOR' AND type == 'XCUIElementTypeTextField'")
  private WebElement inputColor;

  @AndroidFindBy(id = "btnAdd vehicle")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_add_vehicle_ds_button'")
  private WebElement btnAddVehicle;
}