package com.automation.lac.qa.staffapp.mobile.screens.home;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class HomeScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "navigation_header_logo")
  private WebElement headerLogo;

  @iOSXCUITFindBy(id = "search_fan_button")
  private WebElement btnFanSearch;
}