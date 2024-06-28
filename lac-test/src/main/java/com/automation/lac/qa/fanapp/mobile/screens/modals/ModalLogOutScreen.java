package com.automation.lac.qa.fanapp.mobile.screens.modals;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class ModalLogOutScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "resourceId(\"btnPrimary\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_yes,_log_me_out_ds_button'")
  private WebElement btnYesLogMeOut;

  @AndroidFindBy(uiAutomator = "resourceId(\"btnSecondary\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'modal_message_secondary_button'")
  private WebElement btnCancel;
}
