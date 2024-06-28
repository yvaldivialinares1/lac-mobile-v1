package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class CheckYourEmailScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(accessibility = "banner_message_text")
  @AndroidFindBy(uiAutomator = "resourceId(\"bannerMessageRow\")")
  private WebElement txtBannerMessageText;

  @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeTextView'")
  @AndroidFindBy(uiAutomator = "descriptionStartsWith(\"Code input\")")
  private WebElement inputNumberCode;

  @iOSXCUITFindBy(accessibility = "id_btn_confirm_my_account_ds_button")
  @AndroidFindBy(accessibility = "CONFIRM MY ACCOUNT")
  private WebElement btnContinue;
}