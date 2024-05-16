package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class SetPasswordScreen extends MobileBaseScreen {
  @iOSXCUITFindBy(accessibility = "id_btn_password_security_on")
  @AndroidFindBy(uiAutomator = "description(\"Show password\").instance(0)")
  private WebElement btnShowSetAPassword;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"id_input_password_field\"]"
    + "/XCUIElementTypeTextField")
  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'SET A PASSWORD')]/..")
  private WebElement inputSetAPassword;

  @iOSXCUITFindBy(accessibility = "id_btn_confirm_password_security_on")
  @AndroidFindBy(uiAutomator = "description(\"Show password\").instance(1)")
  private WebElement btnShowConfirmPassword;

  @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`label == \"CONFIRM PASSWORD, required "
    + "input\"`]/XCUIElementTypeTextField")
  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'CONFIRM PASSWORD')]/..")
  private WebElement inputConfirmPassword;

  @iOSXCUITFindBy(accessibility = "primary_brush_button")
  @AndroidFindBy(accessibility = "confirm my account")
  private WebElement btnConfirmMyAccount;
}
