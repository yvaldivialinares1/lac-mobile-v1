package com.automation.lac.qa.fanapp.mobile.screens.paymentmethods;

import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class AddPaymentMethodScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "resourceId(\"addCardNumber\")"
    + ".childSelector(className(\"android.widget.EditText\"))")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_input_add_card_number_field\" AND "
    + "label == \"CARD NUMBER, required input\" AND type == \"XCUIElementTypeTextField\"")
  private WebElement txtCardNumber;

  @AndroidFindBy(uiAutomator = "resourceId(\"addCardExpiryDate\")"
    + ".childSelector(className(\"android.widget.EditText\"))")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_input_add_card_expiry_date_field\" AND "
    + "label == \"EXPIRY DATE, required input\" AND type == \"XCUIElementTypeTextField\"")
  private WebElement txtExpiryDate;

  @AndroidFindBy(uiAutomator = "resourceId(\"addCardSecurityCode\")"
    + ".childSelector(className(\"android.widget.EditText\"))")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_input_add_card_security_code_field\" AND "
    + "label == \"SECURITY CODE, required input\" AND type == \"XCUIElementTypeTextField\"")
  private WebElement txtSecurityCode;

  @AndroidFindBy(uiAutomator = "resourceId(\"addCardNameOnCard\")"
    + ".childSelector(className(\"android.widget.EditText\"))")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_input_add_card_name_on_card_field\" AND "
    + "label == \"NAME ON CARD, required input\" AND type == \"XCUIElementTypeTextField\"")
  private WebElement txtNameOnCard;

  @AndroidFindBy(uiAutomator = "resourceId(\"addCardButton\")")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name,'id_btn_')]/XCUIElementTypeButton")
  private WebElement btnContinueOrAddCard;

  @AndroidFindBy(uiAutomator = "description(\"Select to autofill with my existing data\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Autofill with my existing data'")
  private WebElement chkAutofill;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_address_line_1_field' AND "
    + "type == 'XCUIElementTypeTextField'")
  @AndroidFindBy(uiAutomator = ".resourceId(\"tfStreetAddress\")"
    + ".childSelector(className(\"android.widget.EditText\"))")
  private WebElement txtStreetAddress;

  @iOSXCUITFindBy(accessibility = "id_dropdown_input_country_field")
  @AndroidFindBy(uiAutomator = ".resourceId(\"dlCountry\")")
  private WebElement dpdCountry;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_dropdown_input_state_field'")
  @AndroidFindBy(uiAutomator = ".resourceId(\"dlState\")")
  private WebElement dpdState;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_city_field' AND type == "
    + "'XCUIElementTypeTextField'")
  @AndroidFindBy(uiAutomator = ".resourceId(\"tfCity\")"
    + ".childSelector(className(\"android.widget.EditText\"))")
  private WebElement txtCity;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_zipcode_field' AND "
    + "type == 'XCUIElementTypeTextField'")
  @AndroidFindBy(uiAutomator = ".resourceId(\"tfZipcode\")"
    + ".childSelector(className(\"android.widget.EditText\"))")
  private WebElement txtZipCode;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Search\"]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Search'")
  private WebElement txtSearch;

  @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH 'id_country'")
  private List<WebElement> lblCountryList;

  @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH 'id_state'")
  private List<WebElement> lblStatesList;

  protected static final String XPATH_EXPECTED_OPTION = "//android.view.View[@content-desc='%s']";

  protected WebElement selectOption(String expectedOption) {
    return getElement(By.xpath(String.format(XPATH_EXPECTED_OPTION, expectedOption)));
  }

}