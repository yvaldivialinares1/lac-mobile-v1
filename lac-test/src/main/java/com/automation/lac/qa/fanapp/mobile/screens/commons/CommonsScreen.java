package com.automation.lac.qa.fanapp.mobile.screens.commons;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class CommonsScreen extends MobileBaseScreen {
  @AndroidFindBy(uiAutomator = "descriptionContains(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"Back\" AND type == \"XCUIElementTypeButton\"")
  private WebElement btnBack;
}