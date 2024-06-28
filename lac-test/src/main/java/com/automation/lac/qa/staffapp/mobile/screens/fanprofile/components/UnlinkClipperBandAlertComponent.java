package com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class UnlinkClipperBandAlertComponent extends Widget {

  @iOSXCUITFindBy(id = "unlink_clipperband_alert_button")
  private WebElement btnConfirmUnlink;

  @iOSXCUITFindBy(id = "alert_cancel_button")
  private WebElement btnCancelRemove;

  protected UnlinkClipperBandAlertComponent(WebElement element) {
    super(element);
  }
}
