package com.automation.lac.qa.fanapp.mobile.screens.myvehicles;

import com.automation.lac.qa.fanapp.mobile.screens.commons.components.SelectStateWidget;
import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class EmptyVehiclesScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "textContains('VEHICLE ADDED YET')")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'VEHICLE ADDED YET'")
  private WebElement txtNoVehicleTitle;

  @AndroidFindBy(uiAutomator = "textContains('Vehicle registration helps')")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Vehicle registration helps'")
  private WebElement txtNoVehicleMessage;

  @AndroidFindBy(accessibility = "Add vehicle")
  @iOSXCUITFindBy(accessibility = "primary_rectangle_button")
  private WebElement btnAddVehicle;

  @AndroidFindBy(uiAutomator = "descriptionContains('Back')")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND type == 'XCUIElementTypeButton'")
  private WebElement btnBack;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add Vehicle']/..")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='modal_message_title']/..")
  private AddVehicleWidget addVehicleWidget;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Search']/../../..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Clippers + ID-STG'")
  private SelectStateWidget selectStateWidget;
}