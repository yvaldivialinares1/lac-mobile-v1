package com.automation.lac.qa.staffapp.mobile.screens.notifications.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class FilterBannerComponent extends Widget {

  @iOSXCUITFindBy(id = "Cancel")
  private WebElement btnCancel;

  @iOSXCUITFindBy(id = "Clear All")
  private WebElement btnClearAll;

  @iOSXCUITFindBy(id = "filters")
  private WebElement filtersTitle;

  @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value CONTAINS 'button'"
    + " OR type == 'XCUIElementTypeButton' AND value CONTAINS 'checkbox'")
  private List<WebElement> btnFilterCategories;

  @iOSXCUITFindBy(id = "APPLY FILTERS")
  private WebElement btnApplyFilters;

  protected FilterBannerComponent(WebElement element) {
    super(element);
  }
}
