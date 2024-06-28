package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class StartYourJourneyScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath =
    "//android.widget.TextView[contains(normalize-space(@text), 'EMAIL ADDRESS')]/../..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_email_field' AND type CONTAINS 'TextField'")
  private WebElement txtEmailAddress;

  @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"CREATE ACCOUNT\")")
  @iOSXCUITFindBy(accessibility = "id_btn_create_account_ds_button")
  private WebElement btnCreateAccount;
}