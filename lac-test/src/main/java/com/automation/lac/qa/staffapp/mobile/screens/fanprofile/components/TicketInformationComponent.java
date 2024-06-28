package com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components;

import com.automation.lac.qa.pages.MobileBaseComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class TicketInformationComponent extends MobileBaseComponent {

  @iOSXCUITFindBy(id = "ticket_information_title")
  private WebElement lblTitle;

  @iOSXCUITFindBy(id = "close_button")
  private WebElement btnClose;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'fan_tab_item_' AND label CONTAINS 'Upcoming'")
  private WebElement btnUpcomingTab;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'fan_tab_item_' AND label CONTAINS 'Past'")
  private WebElement btnPastTab;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name, 'ticket_filter')]")
  private List<WebElement> lstFilterOptions;

  private List<TicketCardComponent> lstTicketCardComponents;
}
