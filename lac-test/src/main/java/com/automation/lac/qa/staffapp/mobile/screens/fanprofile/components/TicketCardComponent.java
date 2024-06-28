package com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label = 'ticket_card_background']/..")
public class TicketCardComponent extends Widget {

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name = 'ticket_type_icon']")
  private WebElement lblTicketType;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name = 'ticket_date_tag']")
  private WebElement lblEventDate;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name = 'ticket_card_event_name']")
  private WebElement lblEventName;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name = 'seat_type_info_label']")
  private WebElement lblSeatsInfo;

  public TicketCardComponent(WebElement element) {
    super(element);
  }
}
