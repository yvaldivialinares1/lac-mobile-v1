package com.automation.lac.qa.fanapp.mobile.screens.tickets;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class TicketDetailScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='bvAmenityContainer']"
          + "//android.view.View[contains(@content-desc,'experience of')]")
  @iOSXCUITFindBy(xpath = "//*[contains(@name,'experience of')]"
          + "//*[@name='game_details_amenities_title_label']")
  private List<WebElement> amenitiesList;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='bvAmenityContainer'][last()]"
          + "//android.view.View[@resource-id='tvAmenitiesItem']")
  @iOSXCUITFindBy(xpath = "(//*[contains(@name,'experience of')]"
          + "//*[@name='game_details_amenities_title_label'])[last()]")
  private WebElement lastAmenities;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='bvAmenityContainer'][position()=1]"
          + "//android.view.View[@resource-id='tvAmenitiesItem']")
  @iOSXCUITFindBy(xpath = "(//*[contains(@name,'experience of')]"
          + "//*[@name='game_details_amenities_title_label'])[position()=1]")
  private WebElement firstAmenities;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='tvTitle']"
          + "/preceding-sibling::android.view.View")
  @iOSXCUITFindBy(accessibility = "navigation_bar_title_label")
  private WebElement navigationBarTitle;

  @AndroidFindBy(xpath =
          "//android.view.View[@resource-id='ivBannerGameDetail']/preceding-sibling::\t\n"
                  + "android.view.View")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'game_detail_header_view']"
          + "/XCUIElementTypeStaticText")
  private WebElement gameNameDateAndTime;

  @AndroidFindBy(xpath = "//*[@resource-id='tvModalDescription']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='modal_message_description']")
  private WebElement amenitiesInfoDescription;

  @AndroidFindBy(xpath = "//*[@resource-id='tvModalTitle']")
  @iOSXCUITFindBy(accessibility = "modal_message_title")
  private WebElement amenitiesInfoTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='tvPrimaryCta']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='modal_message_primary_button']")
  private WebElement amenitiesInfoCloseButton;

  @AndroidFindBy(xpath = "//*[@resource-id='tvExperienceTheExtraordinaryTitle']")
  @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText"
          + "[@name='game_details_amenities_title_label'])[position()=1]")
  private WebElement amenitiesTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='tvExperienceTheExtraordinarySubTitle']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText"
          + "[@name='game_details_amenities_subtitle_label']")
  private WebElement amenitiesSubTitle;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='game_details_time_label']")
  private WebElement singleGameDetailCardTime;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='game_details_date_label']")
  private WebElement singleGameDetailCardDate;

  @AndroidFindBy(xpath = "//*[@resource-id='tvDesc']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='game_details_description_label']")
  private WebElement singleGameDetailCardDescription;

  @iOSXCUITFindBy(accessibility = "game_details_ticket_status_label")
  private WebElement gameDetailStatus;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='SOLD OUT']")
  private WebElement soldOutGameStatus;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Few seats available']")
  private WebElement fewSeatsGameStatus;

  @AndroidFindBy(xpath = "//*[@resource-id='tvTitle']")
  @iOSXCUITFindBy(accessibility = "navigation_bar_title_label")
  private WebElement findTicketScreenTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='android:id/content']")
  @iOSXCUITFindBy(accessibility = "LaClippers")
  private WebElement contentView;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='ivBannerGameDetail']"
          + "/preceding-sibling::android.view.View")
  private WebElement singleGameDateTimeAndDesc;

  @iOSXCUITFindBy(accessibility = "modal_view_title_label")
  @AndroidFindBy(xpath = "//*[@resource-id='tvModalTitle']")
  private WebElement soldOutReminderTitle;

  @iOSXCUITFindBy(accessibility = "modal_view_subtitle_label")
  @AndroidFindBy(xpath = "//*[@resource-id='tvModalDescription']")
  private WebElement soldOutReminderDescription;

  @iOSXCUITFindBy(accessibility = "modal_view_cta_title_label")
  @AndroidFindBy(xpath = "//*[@resource-id='tvPrimaryCta']")
  private WebElement soldOutReminderJoinAtPlazaButton;

  @iOSXCUITFindBy(accessibility = "modal_view_cancel_button_title_label")
  @AndroidFindBy(xpath = "//*[@resource-id='tvSecondaryCta']")
  private WebElement soldOutReminderContinueButton;

  @AndroidFindBy(xpath = "//*[@resource-id='tvParkingPassesQuestion']")
  @iOSXCUITFindBy(accessibility = "sold_out_game_details_visiting_outdoor_plaza_by_vehicle_label")
  private WebElement soldOutGameDetailBuyParkingPassesHeader;

  @iOSXCUITFindBy(accessibility = "sold_out_game_details_buy_parking_pass_title_label")
  @AndroidFindBy(xpath = "//*[@resource-id='tvBuyParkingPasses']")
  private WebElement soldOutGameDetailBuyParkingPasses;

  @iOSXCUITFindBy(accessibility = "game_details_location_title1_label")
  @AndroidFindBy(xpath = "//*[@resource-id='tvLocationInfoHeader']")
  private WebElement soldOutGameDetailLocationHeader;

  @iOSXCUITFindBy(accessibility = "game_details_location_title2_label")
  @AndroidFindBy(xpath = "//*[@resource-id='tvLocationInfoTitle']")
  private WebElement soldOutGameDetailLocationTitle;

  @iOSXCUITFindBy(accessibility = "sold_out_game_details_cant_join_us_label")
  @AndroidFindBy(xpath = "//*[@resource-id='tvClippersVisionHeader']")
  private WebElement soldOutGameDetailVisionHeader;

  @iOSXCUITFindBy(accessibility = "sold_out_game_details_watch_it_at_home_label")
  @AndroidFindBy(xpath = "//*[@resource-id='tvClippersVisionTitle']")
  private WebElement soldOutGameDetailVisionTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='tvClippersVisionDesc']")
  @iOSXCUITFindBy(accessibility = "sold_out_game_details_find_your_favorite_description_label")
  private WebElement soldOutGameDetailVisionDesc;

  @AndroidFindBy(xpath = "//*[@resource-id='tvClippersVisionLink']")
  @iOSXCUITFindBy(accessibility = "sold_out_game_details_go_to_clipper_vision_title_label")
  private WebElement soldOutGamedetailVisionLink;

  @iOSXCUITFindBy(accessibility = "top_tab_bar_option_2")
  @AndroidFindBy(xpath = "//*[@resource-id='tvTicketsTab2']")
  private WebElement parkingPassesButton;

  @iOSXCUITFindBy(accessibility = "game_details_title1_label")
  @AndroidFindBy(xpath = "//*[@resource-id='tvCmsTitle1']")
  private WebElement soldOutGameDetailTitle1;

  @iOSXCUITFindBy(accessibility = "game_details_title2_label")
  @AndroidFindBy(xpath = "//*[@resource-id='tvCmsTitle2']")
  private WebElement soldOutGameDetailTitle2;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Back']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Back']")
  private WebElement backButton;

  @AndroidFindBy(xpath = "//*[@resource-id='tvLocationInfoAddressSubTitle']")
  @iOSXCUITFindBy(xpath =
          "//XCUIElementTypeStaticText[@name='game_details_location_address_label_2']")
  private WebElement locationAddressDetails;

  @AndroidFindBy(xpath = "//*[@resource-id='tvLocationInfoAddressTitle']")
  @iOSXCUITFindBy(accessibility = "game_details_location_address_label_1")
  private WebElement locationAddress;

  @AndroidFindBy(xpath = "//*[@resource-id='ivLocationInfoNavigationImage']")
  @iOSXCUITFindBy(accessibility = "Open navigation app button")
  private WebElement iconShareLocation;

  @iOSXCUITFindBy(accessibility = "Cancel")
  private WebElement cancelOption;

  @AndroidFindBy(xpath = "//*[@resource-id='tvGameSpiritOfNightTitle']")
  @iOSXCUITFindBy(accessibility = "game_details_spirit_of_night_title_label")
  private WebElement spiritOfNightTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='tvGameSpiritOfNightSubTitle']")
  @iOSXCUITFindBy(accessibility = "game_details_spirit_of_night_subtitle_label")
  private WebElement spiritOfNightSubTitleGame;

  @AndroidFindBy(xpath = "//*[@resource-id='tvEventSpiritOfNightSubTitle']")
  @iOSXCUITFindBy(accessibility = "game_details_spirit_of_night_subtitle_label")
  private WebElement spiritOfNightSubTitleEvent;

  @AndroidFindBy(xpath = "//*[@resource-id='tvEventSpiritOfNightDescription']")
  @iOSXCUITFindBy(accessibility = "game_details_spirit_of_night_description_label")
  private WebElement spiritOfNightDescEvent;

  @AndroidFindBy(xpath = "//*[@resource-id='tvGameSpiritOfNightSubTitle']")
  @iOSXCUITFindBy(accessibility = "game_details_spirit_of_night_subtitle_label")
  private WebElement spiritOfNightDescGame;

  @AndroidFindBy(xpath = "//*[@resource-id='tvStepperValidation']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='select_seat_count_chip_index_0']")
  private WebElement maximumSeatTitle;

  @AndroidFindBy(accessibility = "Back")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='Back']")
  private WebElement selectSeatCountScreenBackButton;

  @AndroidFindBy(xpath = "//*[@resource-id='ivClose']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='banner_message_view']")
  private WebElement maximumAllowedSeatCountBannerCloseButton;

  @AndroidFindBy(xpath = "//*[@resource-id='btFindTickets']")
  @iOSXCUITFindBy(accessibility = "game_details_cta_title_label")
  private WebElement btnFindTicketsGame;

  @AndroidFindBy(xpath = "//*[@resource-id='tvMaxTickets']")
  @iOSXCUITFindBy(accessibility = "game_details_maximum_allowed_label")
  private WebElement maxTicketTextOnDetailScreen;

  @iOSXCUITFindBy(accessibility = "game_details_cta_background")
  @AndroidFindBy(xpath = "//*[@resource-id='tvPrimaryCta']")
  private WebElement stayButtonForSeatSelection;

  @iOSXCUITFindBy(accessibility = "modal_message_secondary_button")
  @AndroidFindBy(xpath = "//*[@resource-id='tvSecondaryCta']")
  private WebElement leaveThePurchaseButtonForSeatSelection;

  @iOSXCUITFindBy(accessibility = "modal_message_title")
  @AndroidFindBy(xpath = "//*[@resource-id='tvModalTitle']")
  private WebElement cancelButtonPopUpTitle;

  @iOSXCUITFindBy(accessibility = "modal_message_description")
  @AndroidFindBy(xpath = "//*[@resource-id='tvModalDescription']")
  private WebElement cancelButtonPopUpDescription;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='tvTitle']")
  @iOSXCUITFindBy(accessibility = "navigation_bar_title_label")
  private WebElement ticketTopTitleOnSeatSelection;

  @AndroidFindBy(xpath = "//*[@resource-id='tvLocationInfoHeader']")
  @iOSXCUITFindBy(xpath =
          "//XCUIElementTypeStaticText[@name='game_details_location_title1_label']")
  private WebElement locationTitle;

  @iOSXCUITFindBy(xpath =
          "//XCUIElementTypeStaticText[@name='game_details_location_title2_label']")
  @AndroidFindBy(xpath = "//*[@resource-id='tvLocationInfoTitle']")
  private WebElement locationSubTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='tvLocationInfoDescription']")
  @iOSXCUITFindBy(accessibility = "event_detail_location_description_label")
  private WebElement locationInfo;

  @AndroidFindBy(xpath = "//*[@resource-id='ivBannerGameDetail']")
  @iOSXCUITFindBy(accessibility = "navigation_bar_title_label")
  private WebElement eventTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='btFindTickets']")
  @iOSXCUITFindBy(accessibility = "event_details_cta_title_label")
  private WebElement btnFindTicketsEvent;

  @AndroidFindBy(xpath = "//*[contains(@resource-id,'boxEventTicket')][contains(@text,'sold out')]")
  @iOSXCUITFindBy(xpath = "//*[contains(@name,'ticket_list_row')]"
          + "//XCUIElementTypeStaticText[contains(@value,'sold out')]")
  private WebElement soldOutEventTicket;

  @AndroidFindBy(xpath = "//*[@resource-id='tvModalTitle']")
  @iOSXCUITFindBy(accessibility = "modal_view_title_label")
  private WebElement reminderTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='tvModalDescription']")
  @iOSXCUITFindBy(accessibility = "modal_view_subtitle_label")
  private WebElement reminderSubTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='tvPrimaryCta']")
  @iOSXCUITFindBy(accessibility = "modal_view_cta_title_label")
  private WebElement reminderCloseButton;

  @AndroidFindBy(xpath = "//*[@resource-id='tvSecondaryCta']")
  @iOSXCUITFindBy(accessibility = "modal_view_cancel_button_title_label")
  private WebElement reminderTurnOnNotificationButton;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='ivBannerGameDetail']"
          + "/preceding-sibling::android.view.View")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'event_detail_header_view']"
          + "/XCUIElementTypeStaticText")
  private WebElement eventNameDateAndTime;

  @AndroidFindBy(xpath = "//*[@resource-id='tvDesc']")
  @iOSXCUITFindBy(accessibility = "event_details_description_label")
  private WebElement eventDesc;

  @iOSXCUITFindBy(accessibility = "event_details_ticket_status_label")
  private WebElement eventStatusLabel;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='SOLD OUT']")
  private WebElement soldOutLabel;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Few seats available']")
  private WebElement fewSeatsLabel;

  @AndroidFindBy(accessibility = "CancelButton")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Cancel']")
  private WebElement selectSeatCountScreenCancelButton;
}
