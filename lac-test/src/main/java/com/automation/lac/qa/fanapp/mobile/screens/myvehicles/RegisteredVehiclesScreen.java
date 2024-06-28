package com.automation.lac.qa.fanapp.mobile.screens.myvehicles;

import com.automation.lac.qa.fanapp.mobile.screens.commons.components.SelectStateWidget;
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
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_add_vehicle_ds_button'")
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

  @AndroidFindBy(id = "ivClose")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'banner_message_close'")
  private WebElement btnCloseSuccessMessage;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add Vehicle']/..")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='modal_message_title']/..")
  private AddVehicleWidget addVehicleWidget;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Search']/../../..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Clippers + ID-STG'")
  private SelectStateWidget selectStateWidget;
}