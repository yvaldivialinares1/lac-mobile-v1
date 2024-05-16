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
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_input_email_field\" AND "
    + "label == \"EMAIL ADDRESS, required input\" AND type == \"XCUIElementTypeTextField\"")
  WebElement txtEmailAddress;

  @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"CREATE ACCOUNT\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"primary_brush_button\"")
  WebElement btnCreateAccount;
}