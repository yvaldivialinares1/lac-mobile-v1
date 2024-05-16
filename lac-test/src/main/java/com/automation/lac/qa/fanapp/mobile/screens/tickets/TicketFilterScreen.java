package com.automation.lac.qa.fanapp.mobile.screens.tickets;


import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;


@Getter
public class TicketFilterScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath = "//android.view.View[starts-with(@content-desc,'filter')]//..")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther//XCUIElementTypeButton"
          + "[starts-with(@name, 'ticket_filter_option_label')]")
  private List<WebElement> lstFilter;

  @AndroidFindBy(xpath = "//*[@resource-id='All']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='ticket_filter_option_all']")
  private WebElement byAll;

  @AndroidFindBy(xpath = "//*[@resource-id='By date']")
  @iOSXCUITFindBy(accessibility = "ticket_filter_option_bydate")
  private WebElement byDate;

  @AndroidFindBy(xpath = "//*[@resource-id='By category']")
  @iOSXCUITFindBy(accessibility = "ticket_filter_option_bycategory")
  private WebElement byCategoryFilter;

  @AndroidFindBy(xpath = "//*[@resource-id='This weekend']")
  @iOSXCUITFindBy(accessibility = "ticket_filter_option_thisweekend")
  private WebElement thisWeekendFilter;

  @AndroidFindBy(xpath = "(//android.widget.TextView["
          + "@resource-id='tvCategoryFilterList'])[1]")
  @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[starts-with"
          + "(@name,'ticket_filter_by_category_name_')])[1]")
  private WebElement firstCategory;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvCategoryFilterList']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with"
          + "(@name,'ticket_filter_by_category_name_')]")
  private List<WebElement> ltnCategory;

  @AndroidFindBy(xpath = "//*[@resource-id='By opponent']")
  @iOSXCUITFindBy(accessibility = "ticket_filter_option_byopponent")
  private WebElement byOpponent;

  @AndroidFindBy(xpath = "//*[@resource-id='ivSearchFilter']")
  @iOSXCUITFindBy(accessibility = "search")
  private WebElement btnSearchIcon;

  @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='tfSearch']")
  @iOSXCUITFindBy(accessibility = "ticket_search_textfield")
  private WebElement txtTicketSearch;

  @AndroidFindBy(xpath = "//*[@resource-id='tfSearch']//android.widget.TextView")
  private WebElement ticketSearchTextBoxDefaultText;
  @AndroidFindBy(xpath = "(//*[@resource-id='tfSearch']/android.view.View)[1]")
  private WebElement ticketSearchBoxSuggestedText;

  @AndroidFindBy(xpath = "//*[@resource-id='ivSearchCancel']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
  private WebElement btnCancelSearch;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='ivSearchClear']")
  @iOSXCUITFindBy(accessibility = "clear search text")
  private WebElement btnClearSearch;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvOpponentFilterList']")
  @iOSXCUITFindBy(xpath =
          "//XCUIElementTypeStaticText[starts-with(@name,'ticket_filter_by_opponent_team_name_')]")
  private List<WebElement> lstOpponentTeamFilter;

  @AndroidFindBy(xpath = "//*[@resource-id='Half Season A 20']")
  @iOSXCUITFindBy(accessibility = "ticket_filter_by_opponent_team_name_6")
  private WebElement opponentTeamFilterHalfSeasonA20;

  @AndroidFindBy(xpath = "//*[@resource-id='ivCalendar']")
  @iOSXCUITFindBy(accessibility = "full Calendar")
  private WebElement btnByDateInnerCalendar;

  @AndroidFindBy(xpath = "//android.widget.TextView[starts-with"
          + "(@resource-id, 'boxMonthCalendar_') and"
          + " contains(@text, 'disable') and contains(@focusable, 'false') ]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name, 'date_') and "
          + "contains(@value, 'disabled') and contains(@accessible, 'true')]")
  private List<WebElement> byDateInnerCalendarDisabledDates;

  @AndroidFindBy(xpath = "//android.widget.TextView[starts-with(@resource-id, 'boxMonthCalendar_')"
          + " and contains(@enabled, 'true') and contains(@text, 'enable')]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[starts-with(@name, 'date_') and contains"
          + "(@accessible, 'true') and (contains(@value, 'available') "
          + "or contains(@value, 'selected'))]")
  private List<WebElement> byDateInnerCalendarEnabledDates;

  @AndroidFindBy(xpath = "//*[@resource-id='tvMonthTitle']")
  @iOSXCUITFindBy(accessibility = "cal_month_year")
  private WebElement byDateMonthTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='ivClose']")
  @iOSXCUITFindBy(xpath = " //XCUIElementTypeButton[@name='close Calendar']")
  private WebElement byDateCalendarCloseButton;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,'date_')]")
  private List<WebElement> lstCalenderDatesIos;

  @AndroidFindBy(xpath =
          "//android.widget.TextView[@resource-id='tvSingleGameTicketOpponentTeamName']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,"
          + "'ticket_list_row_opponent_name_label')]/..")
  private List<WebElement> lstOpponentTeamNameFromGameTickets;

  @AndroidFindBy(xpath =
          "//android.view.View[@resource-id='ivSearchFilter' or @resource-id='All' "
                  + "or @resource-id='By date' or @resource-id='This weekend'"
                  + " or @resource-id='By opponent']")
  @iOSXCUITFindBy(xpath =
          "//XCUIElementTypeButton[starts-with(@name,'ticket_filter_option_label')]")
  private List<WebElement> lstMainFilter;

  @AndroidFindBy(xpath = "//android.view.View[starts-with(@resource-id,'boxRowCalendar')]")
  @iOSXCUITFindBy(xpath = "//*[contains(@name, 'day_') and contains(@name, '-date_')]/..")
  private List<WebElement> lstByDateFilterCalendarDateWeek;

  @AndroidFindBy(xpath = "//android.view.View[starts-with(@resource-id,'boxRowCalendar')]")
  @iOSXCUITFindBy(xpath = "//*[contains(@name, 'day_') and contains(@name, '-date_')]/..")
  private List<WebElement> lstByDateFilterCalendarDate;

  @AndroidFindBy(xpath = "//*[@resource-id='ivArrowRight']")
  @iOSXCUITFindBy(accessibility = "next month")
  private WebElement btnByDateFilterNextMonth;

  @AndroidFindBy(xpath = "//*[@resource-id='ivArrowLeft']")
  @iOSXCUITFindBy(accessibility = "previous month")
  private WebElement btnByDateFilterPreviousMonth;

  @AndroidFindBy(xpath = "//*[@resource-id='tvMonthTitle']")
  @iOSXCUITFindBy(accessibility = "cal_month_year")
  private WebElement defaultMonthOnByDateFilter;

  @AndroidFindBy(xpath = "//*[@resource-id='By events']")
  @iOSXCUITFindBy(accessibility = "ticket_filter_option_byevents")
  private WebElement byEvent;

  @AndroidFindBy(xpath = "//*[@resource-id='By games']")
  @iOSXCUITFindBy(accessibility = "ticket_filter_option_bygames")
  private WebElement byGame;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='My upcoming visits']")
  @iOSXCUITFindBy(accessibility = "ticket_filter_option_myupcomingvisits")
  private WebElement upComingVisitFilter;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='columnCategoryFilterList0']")
  @iOSXCUITFindBy(accessibility = "ticket_filter_by_event_type0")
  private WebElement upComingVisitGameFilter;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='columnCategoryFilterList1']")
  @iOSXCUITFindBy(accessibility = "ticket_filter_by_event_type1")
  private WebElement upComingVisitEventFilter;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='My Upcoming Visits' "
          + "or @resource-id='By games']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[starts-with(@name,"
          + "'ticket_filter_option_label')]")
  private List<WebElement> mainFilterParkingList;

}