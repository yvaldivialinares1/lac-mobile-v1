package com.automation.lac.qa.fanapp.mobile.screens.identitypass;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class MyIdentityPassScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "resourceId(\"btnADD IDENTITY PASS\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'ADD IDENTITY PASS' "
    + "AND type == 'XCUIElementTypeButton'")
  private WebElement btnAddIdentityPass;

  @AndroidFindBy(uiAutomator = "resourceId((\"btnGoogleWallet\"))")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'identity_pass_add_to_wallet_button_title' "
    + "AND label == 'Add to Apple Wallet' AND type == 'XCUIElementTypeButton'")
  private WebElement btnAddGoogleWallet;

}