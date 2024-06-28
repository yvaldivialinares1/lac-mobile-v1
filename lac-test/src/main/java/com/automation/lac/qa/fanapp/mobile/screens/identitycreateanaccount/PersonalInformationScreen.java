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

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'FIRST NAME*, required')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_first_name_field' AND "
    + "type == 'XCUIElementTypeTextField'")
  private WebElement inputFirstName;

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'LAST NAME*, required')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_last_name_field' AND "
    + "type == 'XCUIElementTypeTextField'")
  private WebElement inputLastName;

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'DATE OF BIRTH*')]/..")
  @iOSXCUITFindBy(accessibility = "Date Picker")
  private WebElement inputDateOfBirth;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"COUNTRY CODE*\")")
  @iOSXCUITFindBy(accessibility = "id_dropdown_input_country_code_field")
  private WebElement inputCountryCode;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Search\"]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Search'")
  private WebElement inputSearch;

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'PHONE NUMBER*')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_phone_number_field' AND "
    + "type == 'XCUIElementTypeTextField'")
  private WebElement inputPhoneNumber;

  @AndroidFindBy(id = "btnCONTINUE")
  @iOSXCUITFindBy(accessibility = "primary_rectangle_button")
  private WebElement btnContinue;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_label_wizard_title'")
  private WebElement lblPersonalInformation;

  @AndroidFindBy(id = "tfPREFERRED NAME")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_nick_name_field' AND "
    + "type == 'XCUIElementTypeTextField'")
  WebElement inputPreferredName;

  protected static final String XPATH_EXPECTED_ANDROID =
    "//android.view.View[contains(@content-desc,'%s')]";

  protected static final String XPATH_EXPECTED_IOS =
    "//XCUIElementTypeOther[contains(@label, '%s')]";

  public WebElement listElementToSelect(String expectedOption) {
    String platformXpath = isAndroid() ? XPATH_EXPECTED_ANDROID : XPATH_EXPECTED_IOS;
    return getElement(By.xpath(String.format(platformXpath, expectedOption)));
  }
}