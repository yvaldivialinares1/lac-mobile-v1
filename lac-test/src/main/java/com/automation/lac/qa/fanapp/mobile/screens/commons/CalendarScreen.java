package com.automation.lac.qa.fanapp.mobile.screens.commons;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class CalendarScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "textContains(\"Select date\")")
  private WebElement lblSelectDate;

  @AndroidFindBy(uiAutomator = "textContains(\"Today\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Today'")
  private WebElement lblToday;

  @AndroidFindBy(uiAutomator = "description(\"Switch to selecting a year\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Show year picker'")
  private WebElement btnSwitchToYearSelection;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"tap to switch back to selecting a day\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Hide year picker'")
  private WebElement btnSwitchToDaySelection;

  @AndroidFindBy(uiAutomator = "description(\"Change to previous month\")")
  private WebElement btnPreviousMonth;

  @AndroidFindBy(uiAutomator = "description(\"Change to next month\")")
  private WebElement btnNextMonth;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select date']"
    + "/following-sibling::android.view.View/child::android.view.View[2]")
  private WebElement scrollableCalendar;

  @AndroidFindBy(uiAutomator = "text(\"CONFIRM\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'PopoverDismissRegion'")
  private WebElement btnCloseCalendar;

  protected String androidYearXpath =
    "//android.widget.TextView[@text='Navigate to year %s']/android.widget.Button";

  protected String androidDayXpath =
    "//android.widget.TextView[contains(@text,'%s')]/android.widget.Button";

  protected String androidMonthViewXpath =
    "//android.widget.TextView[contains(@content-desc, '%s')]";

  protected String iosWheelXpath = "//XCUIElementTypePickerWheel[@value='%s']";

  protected String iosDayXpath = "//XCUIElementTypeButton[contains(@name, '%s')]";
}