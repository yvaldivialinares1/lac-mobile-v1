package com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class RemoveVehicleAlertComponent extends Widget {

  @iOSXCUITFindBy(id = "delete_vehicle_alert_delete_button")
  private WebElement btnConfirmRemove;

  @iOSXCUITFindBy(id = "alert_cancel_button")
  private WebElement btnCancelRemove;

  protected RemoveVehicleAlertComponent(WebElement element) {
    super(element);
  }
}
