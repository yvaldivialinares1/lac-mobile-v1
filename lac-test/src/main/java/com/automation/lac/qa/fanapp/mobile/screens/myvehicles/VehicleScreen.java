package com.automation.lac.qa.fanapp.mobile.screens.myvehicles;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class VehicleScreen extends MobileBaseScreen {

  @AndroidFindBy(accessibility = "Vehicle Nickname")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='id_input_vehicle_nickname_id_field']")
  private WebElement lblVehicleNickname;

  @AndroidFindBy(accessibility = "License Plate, required")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'LICENSE PLATE, required' "
    + "AND type == 'XCUIElementTypeOther'")
  private WebElement lblLicensePlate;

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains('State, required')")
  @iOSXCUITFindBy(iOSNsPredicate = "label == 'STATE*'")
  private WebElement lblState;

  @AndroidFindBy(uiAutomator = "new UiSelector().descriptionMatches"
    + "(\"\\b(?!Search)[A-Z][a-zA-Z\\s]{4,}\\b\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH 'id_state'")
  private List<WebElement> lstStates;

  @AndroidFindBy(accessibility = "Make")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'MAKE' AND type == 'XCUIElementTypeOther'")
  private WebElement lblMake;

  @AndroidFindBy(accessibility = "Model")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'MODEL' AND type == 'XCUIElementTypeOther'")
  private WebElement lblModel;

  @AndroidFindBy(accessibility = "Color")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'COLOR' AND type == 'XCUIElementTypeOther'")
  private WebElement lblColor;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='CANCEL']/..")
  @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeButton' AND name == 'CANCEL'")
  private WebElement btnCancel;
}