package com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class RemoveAgeVerificationAlertComponent extends Widget {

  @iOSXCUITFindBy(id = "remove_confirmation_button_title")
  private WebElement btnConfirmRemove;

  @iOSXCUITFindBy(id = "alert_cancel_button")
  private WebElement btnCancelRemove;

  protected RemoveAgeVerificationAlertComponent(WebElement element) {
    super(element);
  }
}
