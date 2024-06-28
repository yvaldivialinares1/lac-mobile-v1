package com.automation.lac.qa.staffapp.mobile.screens.ticketing;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.ticketing.components.PurchaseBannerComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class TicketingScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "tickets_title")
  private WebElement lblHeaderTitle;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'fan_tab_item_' AND label CONTAINS 'All'")
  private WebElement btnAllEvents;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'fan_tab_item_' AND label CONTAINS 'Season'")
  private WebElement btnSeason;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'ticket_card_background']")
  private List<WebElement> lstTicketsCards;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name= 'event_type_tag']")
  private List<WebElement> lstTypesOfEvents;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name= 'event_date_tag']")
  private List<WebElement> lstEventsDates;

  private final PurchaseBannerComponent purchaseBannerComponent;

  public TicketingScreen() {
    purchaseBannerComponent = new PurchaseBannerComponent();
  }
}
