package com.automation.lac.qa.fanapp.mobile.screens.purchase;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class OrderDetailsScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(xpath = "//*[@name='navigation_bar_title_label']")
  @AndroidFindBy(xpath = "//android.view.View[@resource-id='tvTitle']/"
    + "preceding-sibling::android.view.View")
  private WebElement orderDetailPageTitle;

  @iOSXCUITFindBy(accessibility = "garage_selection_which_garage_preference_title")
  @AndroidFindBy(xpath = "//*[@text='Add Parking Passes?']")
  private WebElement parkingPassesLabel;

  @iOSXCUITFindBy(accessibility = "see_garage_location_button")
  @AndroidFindBy(xpath = "//*[@resource-id='tvMapDetailLink']")
  private WebElement seeGarageLocationLink;

  @iOSXCUITFindBy(accessibility = "basic_information_item_description")
  @AndroidFindBy(xpath = "//*[@resource-id='tvMapDetailLink']/"
    + "preceding-sibling::android.widget.TextView")
  private WebElement garageAddressLabel;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'basic_information')]")
  @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@resource-id,'basicInfoText')]")
  private List<WebElement> additionalMessages;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name="
    + "'order_details_number_of_seats_label']")
  @AndroidFindBy(xpath = "(//android.widget.TextView[@resource-id='tvTotalSeatCount'])[1]")
  private WebElement ticketSeatCount;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvEventTittle']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='order_details_event_name_label']")
  private WebElement ticketName;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvEventDate']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='order_details_date_label']")
  private WebElement ticketDateTime;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvExpandCardTitle0']")
  @iOSXCUITFindBy(accessibility = "order_details_section_title_label_0")
  private WebElement seatTitle;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvExpandCardSubTitle0']")
  @iOSXCUITFindBy(accessibility = "order_details_section_sub_title_label_0")
  private WebElement seatRowSectionDetail;

  @AndroidFindBy(xpath =
    "//android.widget.TextView[starts-with(@resource-id,'tvExpandCardTitle')]")
  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeStaticText[starts-with(@name,'order_details_section_title_label')]")
  private List<WebElement> listOfSeatTitle;

  @AndroidFindBy(xpath =
    "//android.widget.TextView[starts-with(@resource-id,'tvExpandCardSubTitle')]")
  @iOSXCUITFindBy(xpath =
    "//XCUIElementTypeStaticText[starts-with(@name,'order_details_section_sub_title_label_')]")
  private List<WebElement> listOfSeatRowSectionDetail;

  @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,"
    + "'tvExpandCardSubTitle')]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,"
    + "'order_details_section_sub_title_label')]")
  private List<WebElement> seatRowSectionDetailsForMultiParking;

  @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='tvExpandCardExpandCollapseBtn0']")
  @iOSXCUITFindBy(accessibility = "order_details_expand_collapse_image_0")
  private WebElement btnSeatExpand;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[starts-with(@name,"
    + "'order_details_expand_collapse_image')]")
  @AndroidFindBy(xpath = "//android.widget.Button[starts-with(@resource-id,"
    + "'tvExpandCardExpandCollapseBtn')]")
  private List<WebElement> totalSeatExpandButton;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,"
    + "'order_details_sub_total_label_0')]")
  @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,"
    + "'tvExpandCardSubtotal')]")
  private List<WebElement> seatPriceComponents;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,"
    + "'order_details_price_component_value_label_0_subtotal')]")
  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id="
    + "'tvExpandCardSubtotal0']")
  private WebElement seatSubtotalValue;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvTotalTickets']")
  @iOSXCUITFindBy(accessibility = "order_details_total_ticket_label")
  private WebElement lblTotalSeatCount;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvTotalCharges']")
  @iOSXCUITFindBy(accessibility = "order_details_total_price_label")
  private WebElement lblTotalCharges;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='btnGoToPay']")
  @iOSXCUITFindBy(iOSNsPredicate = "name = 'seat_order_cta_title_label' AND type CONTAINS 'Button'")
  private WebElement btnGoToPay;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='tvSubTitle']")
  @iOSXCUITFindBy(accessibility = "navigation_bar_subtitle_label")
  private WebElement orderDetailScreenSubTitle;

  @iOSXCUITFindBy(accessibility = "modal_message_title")
  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvModalTitle']")
  private WebElement modalTitleForGarage;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,"
    + "'order_details_price_component_value_label_')]")
  @AndroidFindBy(xpath = "//android.widget.TextView[starts-with"
    + "(@resource-id,'tvExpandCardPriceValue_')]")
  private List<WebElement> seatPriceForSection;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,"
    + "'order_details_price_component_title_label')]")
  @AndroidFindBy(xpath = "//android.widget.TextView[starts-with"
    + "(@resource-id,'tvExpandCardPriceLabel_')]")
  private List<WebElement> seatLabels;

  @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Delete button']")
  @iOSXCUITFindBy(accessibility = "order_details_delete_button")
  private WebElement btnDelete;

  protected String getRowSectionDetails() {
    if (isAndroid())
      return "//android.widget.TextView[starts-with(@resource-id,'tvExpandCardSubTitle%s')]";
    else return
      "//XCUIElementTypeStaticText[starts-with(@name,'order_details_section_sub_title_label_%s')]";
  }

}