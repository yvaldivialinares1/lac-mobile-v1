package com.automation.lac.qa.staffapp.mobile.screens.access;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AccessScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(accessibility = "HeadingIntuit Dome AccessCategory")
  private WebElement intuitDomeAccessCategoryTitle;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther"
    + "[@name='access_Intuit_Dome_Access_category_section']/XCUIElementTypeButton[@name]")
  private List<WebElement> intuitDomeAccessCategoryQueues;

  @iOSXCUITFindBy(accessibility = "HeadingParkingCategory")
  private WebElement parkingCategoryTitle;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='access_Parking_category_section']"
    + "/XCUIElementTypeButton[@name]")
  private List<WebElement> parkingCategoryQueues;

  @iOSXCUITFindBy(accessibility = "HeadingClubsCategory")
  private WebElement clubsCategoryTitle;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='access_Clubs_category_section']"
    + "/XCUIElementTypeButton[@name]")
  private List<WebElement> clubsCategoryQueues;

  @iOSXCUITFindBy(accessibility = "HeadingStoresCategory")
  private WebElement storesCategoryTitle;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='access_Stores_category_section']"
    + "/XCUIElementTypeButton[@name]")
  private List<WebElement> storesCategoryQueues;
}