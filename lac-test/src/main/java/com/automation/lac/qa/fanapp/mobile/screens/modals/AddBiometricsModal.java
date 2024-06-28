package com.automation.lac.qa.fanapp.mobile.screens.modals;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AddBiometricsModal extends MobileBaseScreen {

  @AndroidFindBy(id = "TBD")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_primary_request_biometric_modal'")
  private WebElement lblBiometricsModal;

  @AndroidFindBy(id = "TBD")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_enable_phone_biometrics_ds_button'")
  private WebElement btnEnablePhoneBiometrics;

  @AndroidFindBy(id = "TBD")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'modal_message_secondary_button'")
  private WebElement btnIWillDoItLater;
}
