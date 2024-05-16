package com.automation.lac.qa.fanapp.mobile.screens.teammateaccounts;

import static com.automation.lac.qa.fanapp.mobile.utils.DeviceActions.getElement;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class AddTeammateAccountScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath = "//android.view.View[matches(@content-desc, '^first name', 'i')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'input_first_name' AND type CONTAINS 'TextField'")
  private WebElement inputFirstName;

  @AndroidFindBy(xpath = "//android.view.View[matches(@content-desc, '^last name', 'i')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'input_last_name' AND type CONTAINS 'TextField'")
  private WebElement inputLastName;

  @AndroidFindBy(uiAutomator = "resourceId(\"tfPreferred Name\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'preferred_name' AND type CONTAINS 'TextField'")
  private WebElement inputPreferredName;

  @AndroidFindBy(xpath = "//android.view.View[starts-with(@content-desc, 'date of birth')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Date Picker'")
  private WebElement inputDateOfBirth;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Show year picker'")
  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Switch to selecting a year']/"
    + "preceding-sibling::android.widget.TextView")
  private WebElement txtCurrentSelectionDate;

  @AndroidFindBy(accessibility = "Switch to selecting a year")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Show year picker'")
  private WebElement btnSwitchToYearSelection;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Change to next month\")")
  private WebElement btnNextMonth;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Change to previous month\")")
  @iOSXCUITFindBy(accessibility = "Previous Month")
  private WebElement btnPreviousMonth;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Hide year picker'")
  private WebElement hideYearPicker;

  @AndroidFindBy(uiAutomator = "text(\"CONFIRM\")")
  private WebElement btnConfirm;

  @iOSXCUITFindBy(accessibility = "PopoverDismissRegion")
  private WebElement btnPopoverDismissCalendar;

  @AndroidFindBy(uiAutomator = "resourceId(\"btnAdd Teammate Account\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'ADD TEAMMATE' AND type CONTAINS 'Button'")
  private WebElement btnAddTeammateAccount;

  protected static final String YEAR_XPATH_ANDROID =
    "//android.widget.TextView[contains(@text,'%s')]/android.view.View";

  protected static final String DAY_XPATH_ANDROID =
    "//android.widget.TextView[contains(@text,'%s')]/android.view.View";

  protected static final String XPATH_WHEEL_IOS = "//XCUIElementTypePickerWheel[@value='%s']";

  protected static final String XPATH_DAY_IOS = "//XCUIElementTypeStaticText[@name='%s']";

  public WebElement getMonthIos(String defaultDate) {
    return getElement(
      By.xpath(String.format(XPATH_WHEEL_IOS, defaultDate.split(" ")[0].trim())));
  }

  public WebElement getYearIos(String defaultDate) {
    return getElement(
      By.xpath(String.format(XPATH_WHEEL_IOS, defaultDate.split(" ")[1].trim())));
  }

  public WebElement getDayIos(String day) {
    return getElement(By.xpath(String.format(XPATH_DAY_IOS, day)));
  }
}