package com.automation.lac.qa.staffapp.mobile.screens.commons;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.commons.components.FanSearchResultComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class FanSearchScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(accessibility = "search_bar_input")
  private WebElement frmInputManualSearch;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'fan_info_result_list_')]")
  private List<FanSearchResultComponent> searchResultsList;
}