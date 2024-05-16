package com.automation.lac.qa.fanapp.mobile.screens.myvehicles;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class NoRegisteredVehiclesScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "textContains('VEHICLE ADDED YET')")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'VEHICLE ADDED YET'")
  private WebElement txtNoVehicleTitle;

  @AndroidFindBy(uiAutomator = "textContains('Vehicle registration helps')")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Vehicle registration helps'")
  private WebElement txtNoVehicleMessage;

  @AndroidFindBy(accessibility = "Add vehicle")
  @iOSXCUITFindBy(accessibility = "primary_brush_button")
  private WebElement btnAddVehicle;

  @AndroidFindBy(uiAutomator = "descriptionContains('Back')")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND type == 'XCUIElementTypeButton'")
  private WebElement btnBack;
}