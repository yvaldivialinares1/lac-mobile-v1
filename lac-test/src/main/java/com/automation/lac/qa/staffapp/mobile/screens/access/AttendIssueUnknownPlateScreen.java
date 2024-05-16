package com.automation.lac.qa.staffapp.mobile.screens.access;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class AttendIssueUnknownPlateScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(accessibility = "search_icon")
  private WebElement searchInputSearchIcon;

  @iOSXCUITFindBy(accessibility = "search_bar_input")
  private WebElement manualSearchInputField;

  @iOSXCUITFindBy(accessibility = "search_bar_hint_text")
  private WebElement manualSearchBarHintText;

  @iOSXCUITFindBy(accessibility = "clear_button")
  private WebElement clearManualSearchInputButton;

  @iOSXCUITFindBy(accessibility = "qr_code_search_button")
  private WebElement navigationHeaderSearchInputQrCodeSearchButton;

  @iOSXCUITFindBy(accessibility = "biometrics_search_button")
  private WebElement navigationHeaderSearchInputBioMetricSearchButton;

  @iOSXCUITFindBy(accessibility = "SELECT A FAN ACCOUNT TO REGISTER THE VEHICLE")
  private WebElement titleSelectFanAccountToRegisterVehicle;

  @iOSXCUITFindBy(accessibility = "Does the fan not have an account?")
  private WebElement lblFanNotHaveAccount;

  @iOSXCUITFindBy(accessibility = "attend_issue_container_secondary_title")
  private WebElement attendIssueSecondaryTitle;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='user_name']")
  private WebElement attendIssueFanNameList;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='profile_photo']")
  private WebElement attendIssueFanProfilePhoto;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='email_id']")
  private WebElement attendIssueFanEmailList;

  @iOSXCUITFindBy(accessibility = "attend_issue_primary_button")
  private WebElement attendIssuePrimaryButton;

  /**
   * Get Fan Account Result by index
   */
  public WebElement getFanAccountResultByIndex(String recordIndex) {
    String locator = "//XCUIElementTypeOther[@name='fan_info_result_list_" + recordIndex + "']";
    return getDriver().findElement(By.xpath(locator));
  }

  /**
   * Get staff profile by index
   */
  public WebElement getStaffProfileByIndex(String recordIndex) {
    String locator = "//XCUIElementTypeOther[@name='fan_info_result_list_" + recordIndex + "']"
            + "//XCUIElementTypeImage[@name='navigation_staff_profile']";
    return getDriver().findElement(By.xpath(locator));
  }

  /**
   * Get profile name initials by index
   */
  public WebElement getProfileNameInitialsByIndex(String recordIndex) {
    String locator = "//XCUIElementTypeOther[@name='fan_info_result_list_" + recordIndex + "']"
            + "//XCUIElementTypeStaticText[@name='profile_name_initials']";
    return getDriver().findElement(By.xpath(locator));
  }

  /**
   * Get first and last name by index
   */
  public WebElement getFirstLastNameByIndex(String recordIndex) {
    String locator = "//XCUIElementTypeOther[@name='fan_info_result_list_" + recordIndex + "']"
            + "//XCUIElementTypeStaticText[@name='first_last_name_label']";
    return getDriver().findElement(By.xpath(locator));
  }

  /**
   * Get chosen name by index
   */
  public WebElement getChosenNameByIndex(String recordIndex) {
    String locator = "//XCUIElementTypeOther[@name='fan_info_result_list_" + recordIndex + "']"
            + "//XCUIElementTypeStaticText[@name='chosen_name_label']";
    return getDriver().findElement(By.xpath(locator));
  }

  /**
   * Get email label by index
   */
  public WebElement getEmailLabelByIndex(String recordIndex) {
    String locator = "//XCUIElementTypeOther[@name='fan_info_result_list_" + recordIndex + "']"
            + "//XCUIElementTypeStaticText[@name=\"phone_label\"]";
    return getDriver().findElement(By.xpath(locator));
  }

  /**
   * Get phone label by index
   */
  public WebElement getPhoneLabelByIndex(String recordIndex) {
    String locator = "//XCUIElementTypeOther[@name='fan_info_result_list_" + recordIndex + "']"
            + "//XCUIElementTypeStaticText[@name='phone_label']";
    return getDriver().findElement(By.xpath(locator));
  }
}
