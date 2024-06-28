package com.automation.lac.qa.fanapp.mobile.screens.tickets;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class TicketListScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@resource-id,'tvTicketsTab')]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'top_tab_bar_option')]"
    + "//XCUIElementTypeStaticText")
  private List<WebElement> lstCategory;

  @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@resource-id,'tvTicketTitle')]")
  @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[starts-with(@name,"
    + "'carousel_ticket_name_label')])")
  private WebElement ticketType;

  @AndroidFindBy(xpath = "//*[starts-with(@resource-id,'Single Game')]")
  @iOSXCUITFindBy(xpath = "//*[starts-with(@name,'carousel_ticket_image_singlegame')]")
  private WebElement singleGameCarousel;

  @AndroidFindBy(xpath = "//*[starts-with(@resource-id,'Season Tickets')]")
  @iOSXCUITFindBy(xpath = "//*[starts-with(@name,'carousel_ticket_image_seasontickets')][1]")
  private WebElement seasonTicketsGameCarousel;

  @AndroidFindBy(xpath = "//*[starts-with(@resource-id,'Club Ticket')]")
  @iOSXCUITFindBy(xpath = "//*[starts-with(@name,'carousel_ticket_image_clubticket')][1]")
  private WebElement clubTicketGameCarousel;

  @AndroidFindBy(xpath = "//*[starts-with(@resource-id,'General Ticket')]")
  @iOSXCUITFindBy(xpath = "//*[starts-with(@name,'carousel_ticket_image_generalticket')][1]")
  private WebElement generalTicketEventCarousel;

  @AndroidFindBy(accessibility = "Next arrow")
  @iOSXCUITFindBy(accessibility = "carousel_bottom_next_button")
  private WebElement btnCarouselNext;

  @AndroidFindBy(accessibility = "Previous arrow")
  @iOSXCUITFindBy(accessibility = "carousel_bottom_previous_button")
  private WebElement btnCarouselPrevious;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='tvTitle']")
  @iOSXCUITFindBy(accessibility = "navigation_bar_title_label")
  private WebElement ticketScreenHeader;

  @AndroidFindBy(id = "tvTicketsTabClippers games")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Clippers games' AND type CONTAINS 'Button'")
  private WebElement btnClippers;

  @AndroidFindBy(id = "tvTicketsTabEvents")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Events' AND type CONTAINS 'Button'")
  private WebElement btnEvents;

  @AndroidFindBy(id = "tvTicketsTabParking passes")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Parking passes' AND type CONTAINS 'Button'")
  private WebElement btnParkingPasses;

  @iOSXCUITFindBy(xpath = " (//XCUIElementTypeStaticText[starts-with(@name,"
    + "'ticket_list_row_opponent_name_label_')]//ancestor::XCUIElementTypeButton)[last()]")
  private WebElement lastTicketIos;

  @iOSXCUITFindBy(xpath = " (//XCUIElementTypeStaticText[starts-with(@name,"
    + "'event_list_row_date_label_')]//ancestor::XCUIElementTypeOther)[last()]")
  private WebElement lastEventTicketIos;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='boxSingleGameTicket0']")
  @iOSXCUITFindBy(accessibility = "ticket_list_row_0")
  private WebElement firstTicket;

  @AndroidFindBy(xpath = "//*[@resource-id='tvPrimaryCta']")
  @iOSXCUITFindBy(accessibility = "modal_view_cta_title_label")
  private WebElement btnMeetTheOutdoorPlaza;

  @AndroidFindBy(xpath = "//*[@resource-id='tvPrimaryCta']")
  @iOSXCUITFindBy(accessibility = "modal_view_cta_title_label")
  private WebElement btnSoldOutEventPopUpClose;

  @AndroidFindBy(xpath = "//*[@resource-id='boxEventTicket0']")
  @iOSXCUITFindBy(xpath = "//*[@name='ticket_list_row_0']//XCUIElementTypeStaticText")
  private WebElement firstEventTicket;

  @AndroidFindBy(xpath = "//*[@resource-id='tvSingleGameErrorViewTitle']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='error_view_error_title_label']")
  private WebElement errorViewTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='tvSingleGameErrorViewSubTitle']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name="
    + "'error_view_error_description_label']")
  private WebElement errorViewSubTitle;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,'date_')]")
  private List<WebElement> lstCalenderDatesIos;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,'date_')]")
  private List<WebElement> innerCalenderDatesiOS;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='tvTicketsTab0']/..")
  @iOSXCUITFindBy(accessibility = "top_tab_bar_bottom_brush_image")
  private WebElement imgTopBarBottomBrush;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvEventTicketTitle']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'ticket_list_row_')]"
    + "//XCUIElementTypeStaticText")
  private List<WebElement> lstEventTicketTitle;

  @AndroidFindBy(xpath =
    "//android.widget.TextView[@resource-id='tvSingleGameTicketOpponentTeamName']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,"
    + "'ticket_list_row_opponent_name_label')]/..")
  private List<WebElement> lstOpponentTeamNameFromGameTickets;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='boxSingleGameTicket0']")
  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeOther[@name='ticket_list_row_0']//XCUIElementTypeStaticText")
  private WebElement firstOpponentTeamNameFromGameTickets;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvSingleGameTicketGameInfoDate']")
  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeStaticText[starts-with(@name,'ticket_list_row_date_label_')]")
  private List<WebElement> lstGameTicketDates;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='rowEventTicketDate']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'ticket_list_row_')]"
    + "//XCUIElementTypeStaticText")
  private List<WebElement> lstEventTicketDates;

  @AndroidFindBy(accessibility = "Back")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Back']")
  private WebElement btnBack;

  @AndroidFindBy(xpath = "//android.widget.TextView[starts-with"
    + "(@resource-id,'boxSingleGameTicket')]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'ticket_list_row')]/"
    + "XCUIElementTypeStaticText")
  private List<WebElement> lstGameTickets;

  @AndroidFindBy(xpath = "//android.widget.TextView[starts-with"
    + "(@resource-id,'boxEventTicket')]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'ticket_list_row')]/"
    + "XCUIElementTypeStaticText")
  private List<WebElement> lstEventTickets;

  @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@resource-id,'boxParkingPasses')]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'parking_pass_list_row')]"
    + "/XCUIElementTypeStaticText")
  private List<WebElement> lstParkingPassesTickets;

  @AndroidFindBy(accessibility = "Reload content")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='Reload content']")
  private WebElement btnReloadContent;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='boxEventTicket0']/..")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'ticket_list_row')]/..")
  private WebElement scrollEventTicketView;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='boxSingleGameTicket0']/..")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'ticket_list_row')]/..")
  private WebElement scrollGameTicketView;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='boxParkingPasses0']/..")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,'ticket_list_row')]/..")
  private WebElement scrollParkingTicketView;

  protected String androidEventDateXpath = "//android.widget.TextView[contains(@text,'%s')]";

  protected String iosEventDateXpath = "//XCUIElementTypeStaticText[contains(@value,'%s')]";
}