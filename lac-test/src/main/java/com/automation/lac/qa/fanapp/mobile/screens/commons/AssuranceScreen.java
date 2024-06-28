package com.automation.lac.qa.fanapp.mobile.screens.commons;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AssuranceScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "text(\"Cancel\")")
  @iOSXCUITFindBy(iOSNsPredicate = "TBD")
  private WebElement btnCancel;

}