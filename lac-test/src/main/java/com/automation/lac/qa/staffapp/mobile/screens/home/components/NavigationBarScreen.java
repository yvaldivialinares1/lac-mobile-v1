package com.automation.lac.qa.staffapp.mobile.screens.home.components;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class NavigationBarScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Home'")
  private WebElement btnHome;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Sales'")
  private WebElement btnSales;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Access'")
  private WebElement btnAccess;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Incidents'")
  private WebElement btnIncidents;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Wayfinding'")
  private WebElement btnWayFinding;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'More'")
  private WebElement btnMore;
}