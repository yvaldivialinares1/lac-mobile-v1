package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount;

import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class PersonalInformationScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'first name')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_first_name_field' AND "
    + "type == 'XCUIElementTypeTextField'")
  private WebElement inputFirstName;

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'last name')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_last_name_field' AND "
    + "type == 'XCUIElementTypeTextField'")
  private WebElement inputLastName;

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'date of birth')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Date Picker'")
  private WebElement inputDateOfBirth;

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'tap to switch back to "
    + "selecting a day')]/parent::android.view.View/following-sibling::android.view.View")
  @iOSXCUITFindBy(iOSNsPredicate = "TBD")
  private WebElement navigateToYearArea;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"country code\")")
  @iOSXCUITFindBy(accessibility = "id_dropdown_input_country_code_field")
  private WebElement inputCountryCode;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Search\"]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Search'")
  private WebElement inputSearch;

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'phone number')]/..")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"id_input_phone_number_field\"]")
  private WebElement inputPhoneNumber;

  @AndroidFindBy(uiAutomator = "textContains(\"CONTINUE\")")
  @iOSXCUITFindBy(accessibility = "primary_brush_button")
  private WebElement btnContinue;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Show year picker'")
  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Switch to selecting a year']/"
    + "preceding-sibling::android.widget.TextView")
  private WebElement txtCurrentSelectionDate;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Change to next month\")")
  private WebElement btnNextMonth;

  @iOSXCUITFindBy(accessibility = "Previous Month")
  @AndroidFindBy(uiAutomator = "descriptionContains(\"Change to previous month\")")
  private WebElement btnPreviousMonth;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Switch to selecting\")")
  private WebElement datePickerYear;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"tap to switch back to selecting a day\")")
  private WebElement switchBackSelectDay;

  protected static final String YEAR_XPATH_ANDROID =
    "//android.widget.TextView[contains(@text,'%s')]/android.view.View";

  protected static final String DAY_XPATH_ANDROID =
    "//android.widget.TextView[contains(@text,'%s')]/android.view.View";

  protected static final String XPATH_WHEEL_IOS = "//XCUIElementTypePickerWheel[@value='%s']";

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Hide year picker'")
  private WebElement hideYearPicker;

  @AndroidFindBy(uiAutomator = "textContains(\"CONFIRM\")")
  private WebElement btnConfirm;

  @iOSXCUITFindBy(accessibility = "PopoverDismissRegion")
  private WebElement btnPopoverDismissCalendar;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_label_wizard_title'")
  private WebElement lblPersonalInformation;

  protected static final String XPATH_EXPECTED_ANDROID =
    "//android.view.View[contains(@content-desc,'%s')]";

  protected static final String XPATH_EXPECTED_IOS =
    "//XCUIElementTypeOther[contains(@label, '%s')]";

  protected static final String XPATH_DAY_IOS = "//XCUIElementTypeButton[contains(@name, '%s')]";

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

  public WebElement listElementToSelect(String expectedOption) {
    String platformXpath = isAndroid() ? XPATH_EXPECTED_ANDROID : XPATH_EXPECTED_IOS;
    return getElement(By.xpath(String.format(platformXpath, expectedOption)));
  }
}