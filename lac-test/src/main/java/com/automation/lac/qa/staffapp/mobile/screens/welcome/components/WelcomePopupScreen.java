package com.automation.lac.qa.staffapp.mobile.screens.welcome.components;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class WelcomePopupScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Continue']")
  private WebElement btnContinue;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
  private WebElement btnCancel;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Allow']")
  private WebElement btnAllow;
}