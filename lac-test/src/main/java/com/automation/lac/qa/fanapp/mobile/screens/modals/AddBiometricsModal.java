package com.automation.lac.qa.fanapp.mobile.screens.modals;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AddBiometricsModal extends MobileBaseScreen {
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"modal_message_primary_button\" AND "
    + "type == \"XCUIElementTypeButton\"")
  WebElement btnEnablePhoneBiometrics;

  @iOSXCUITFindBy(iOSNsPredicate = "name == \"modal_message_secondary_button\" AND "
    + "type == \"XCUIElementTypeButton\"")
  WebElement btnIWillDoItLater;
}
