package com.automation.lac.qa.fanapp.mobile.screens.myvehicles;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class AddVehicleScreen extends VehicleScreen {

  @AndroidFindBy(uiAutomator = "textContains('indicates a required field')")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'indicates a required field'")
  private WebElement msgRequiredFields;

  @AndroidFindBy(uiAutomator = "resourceId(\"tfNickName\")")
  @iOSXCUITFindBy(iOSNsPredicate =
    "label == 'VEHICLE NICKNAME, input' AND type == 'XCUIElementTypeTextField'")
  private WebElement inputVehicleNickname;

  @AndroidFindBy(uiAutomator = "resourceId(\"tfLicensePlate\")")
  @iOSXCUITFindBy(iOSNsPredicate =
    "label CONTAINS 'LICENSE PLATE, required' AND type == 'XCUIElementTypeTextField'")
  private WebElement inputLicensePlate;

  @AndroidFindBy(uiAutomator = "textContains('Plate already added')")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'Plate already added'")
  private WebElement lblLicensePlateError;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"State, required\")")
  @iOSXCUITFindBy(iOSNsPredicate = "label == 'STATE*'")
  WebElement ddlState;

  @AndroidFindBy(xpath = "//android.view.View[starts-with(@content-desc, 'Search')]/..")
  @iOSXCUITFindBy(accessibility = "Search state")
  private WebElement inputSearchState;

  @AndroidFindBy(uiAutomator = "resourceId(\"tfMake\")")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'MAKE' AND type == 'XCUIElementTypeTextField'")
  private WebElement inputMake;

  @AndroidFindBy(uiAutomator = "resourceId(\"tfModel\")")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'MODEL' AND type == 'XCUIElementTypeTextField'")
  private WebElement inputModel;

  @AndroidFindBy(uiAutomator = "resourceId(\"tfColor\")")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'COLOR' AND type == 'XCUIElementTypeTextField'")
  private WebElement inputColor;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD VEHICLE']/..")
  @iOSXCUITFindBy(accessibility = "Add vehicle")
  private WebElement btnAddVehicle;

  protected WebElement btnExpectedState(String state) {
    String myXpath;
    if (isAndroid()) {
      myXpath = "//android.view.View[@content-desc='%s']";
    } else {
      myXpath = "//XCUIElementTypeOther[contains(@label, '%s')]";
    }
    return getDriver().findElement(By.xpath(String.format(myXpath, state)));
  }
}