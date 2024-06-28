package com.automation.lac.qa.fanapp.mobile.screens.tickets;

import com.automation.lac.qa.fanapp.mobile.screens.tickets.component.SuggestedSeatComponent;
import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class SeatsAvailabilityScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath = "//*[@resource-id='tvTitle']")
  @iOSXCUITFindBy(accessibility = "navigation_bar_title_label")
  private WebElement selectSeatScreenTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='btFindTickets']")
  @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText"
    + "[`name == 'game_details_cta_title_label']")
  private WebElement findTicketsButton;

  @AndroidFindBy(xpath = "//*[@resource-id='seatSelectionTicketInfoHeaderSeatSelected']")
  @iOSXCUITFindBy(accessibility = "ticket_seat_list_count_preview_title")
  private WebElement seatSelectionTicketInfoHeaderSeatSelected;

  @AndroidFindBy(xpath = "//*[@resource-id='seatSelectionTicketInfoHeaderSeatSelectedBracket']")
  @iOSXCUITFindBy(accessibility = "ticket_seat_list_count_preview_description")
  private WebElement seatSelectionTicketInfoHeaderSeatSelectedBracket;

  @AndroidFindBy(xpath = "//*[@resource-id='seatSelectionTicketInfoEdit']")
  @iOSXCUITFindBy(accessibility = "ticket_seat_list_count_preview_edit_button")
  private WebElement seatSelectionTicketInfoEditButton;

  @AndroidFindBy(id = "seatSelectionMainHeaderImage")
  @iOSXCUITFindBy(accessibility = "ticket_seat_list_header")
  private WebElement seatSelectionMainHeaderImage;

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Sort seats dropdown')]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='ticket_seat_list_sort_button']"
    + "//XCUIElementTypeStaticText")
  private WebElement sortSeatsDropdown;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Price: High to Low']")
  @iOSXCUITFindBy(accessibility = "ticket_seat_sort_popup_option_1")
  private WebElement sortSeatsByHighToLow;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Price: Low to High']")
  @iOSXCUITFindBy(accessibility = "ticket_seat_sort_popup_option_0")
  private WebElement sortSeatsByLowToHigh;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Show Seats Together First']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='Show Seats Together First']")
  private WebElement sortSeatsBySeatTogether;

  @AndroidFindBy(uiAutomator = "resourceId(\"seatSelectionSeatInfoContainer\")")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='seat_list_card_bg_small']"
    + "//parent::XCUIElementTypeOther")
  private List<SuggestedSeatComponent> listSuggestedSeat;

  @AndroidFindBy(id = "bannerMessageRow")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'banner_message_view'")
  private WebElement lblInformationBanner;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='seatSelectionMainHeaderRow']"
    + "/following-sibling::android.view.View")
  private WebElement scrollSeats;
}