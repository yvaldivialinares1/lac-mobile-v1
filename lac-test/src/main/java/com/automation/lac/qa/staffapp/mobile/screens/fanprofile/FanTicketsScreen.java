package com.automation.lac.qa.staffapp.mobile.screens.fanprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.TicketCardComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class FanTicketsScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "header_title")
  private WebElement lblHeaderTitle;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name = 'buy_a_ticket_button']")
  private WebElement btnBuyATicket;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'fan_tab_item_' AND label CONTAINS 'Upcoming'")
  private WebElement btnUpcomingTab;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'fan_tab_item_' AND label CONTAINS 'Past'")
  private WebElement btnPastTab;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name, 'ticket_filter')]")
  private List<WebElement> lstFilterOptions;

  private List<TicketCardComponent> lstTicketCardComponents;
}
