package com.automation.lac.qa.staffapp.mobile.screens.notifications.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class NotificationBannerComponent extends Widget {

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[1]")
  private WebElement inputTitle;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[2]")
  private WebElement inputDescription;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='SALES']")
  private WebElement btnCategory;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeCollectionView//XCUIElementTypeButton")
  private List<WebElement> lstBtnCategories;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch/XCUIElementTypeSwitch")
  private WebElement swtReadUnread;

  @iOSXCUITFindBy(id = "ADD")
  private WebElement btnAdd;

  protected NotificationBannerComponent(WebElement element) {
    super(element);
  }
}
