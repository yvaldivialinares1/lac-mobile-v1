package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount;

import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class AddressInformationScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_address_line_1_field' AND "
    + "type == 'XCUIElementTypeTextField'")
  @AndroidFindBy(uiAutomator = "resourceId(\"tfADDRESS LINE 1\")")
  private WebElement inputAddressLineOne;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_address_line_2_field'"
    + " AND type == 'XCUIElementTypeTextField'")
  @AndroidFindBy(uiAutomator = "resourceId(\"tfAPP, SUITE, UNIT, BUILDING, FLOOR, "
    + "ETC\")")
  private WebElement inputAppFloor;

  @iOSXCUITFindBy(accessibility = "id_dropdown_input_country_field")
  @AndroidFindBy(uiAutomator = "descriptionContains(\"COUNTRY*\")")
  private WebElement inputCountry;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_dropdown_input_state_field'")
  @AndroidFindBy(uiAutomator = "descriptionContains(\"STATE/PROVINCE/REGION\")")
  private WebElement inputState;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_city_field' AND type == "
    + "'XCUIElementTypeTextField'")
  @AndroidFindBy(uiAutomator = "resourceId(\"tfCITY\")")
  private WebElement inputCity;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_zipcode_field' AND "
    + "type == 'XCUIElementTypeTextField'")
  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc, 'ZIPCODE')]/..")
  private WebElement inputZipCode;

  @iOSXCUITFindBy(accessibility = "id_btn_continue_ds_button")
  @AndroidFindBy(accessibility = "CONTINUE")
  private WebElement btnContinue;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Search\"]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Search'")
  private WebElement inputSearch;

  @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH 'id_country'")
  private List<WebElement> labelCountryList;

  @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH 'id_state'")
  private List<WebElement> labelStatesList;

  protected static final String XPATH_EXPECTED_OPTION = "//android.view.View[@content-desc=\"%s\"]";

  public WebElement selectOption(String expectedOption) {
    return getElement(By.xpath(String.format(XPATH_EXPECTED_OPTION, expectedOption)));
  }
}
