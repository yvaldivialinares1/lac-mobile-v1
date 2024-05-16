package com.automation.lac.qa.fanapp.mobile.screens.myvehicles;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class RegisteredVehiclesScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Double tap to activate\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH 'id_btn_' AND NOT name CONTAINS 'button'")
  private List<WebElement> lstMyVehicles;

  @AndroidFindBy(accessibility = "Add vehicle")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'ADD VEHICLE' AND type == 'XCUIElementTypeButton'")
  private WebElement btnAddVehicle;

  @AndroidFindBy(accessibility = "Back")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND type == 'XCUIElementTypeButton'")
  private WebElement btnBack;

  @AndroidFindBy(accessibility = "Your vehicle has been added")
  @iOSXCUITFindBy(iOSNsPredicate = "label == 'Your vehicle has been added.'")
  private WebElement lblVehicleAddedSuccessMessage;

  @AndroidFindBy(accessibility = "Your vehicle was deleted successfully.")
  @iOSXCUITFindBy(iOSNsPredicate = "label == 'Your vehicle was deleted successfully.'")
  private WebElement lblVehicleDeletedSuccessMessage;

  @AndroidFindBy(accessibility = "Close")
  @iOSXCUITFindBy(iOSNsPredicate = "label == 'Close'")
  private WebElement btnCloseSuccessMessage;
}