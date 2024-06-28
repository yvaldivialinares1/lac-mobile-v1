package com.automation.lac.qa.fanapp.mobile.screens.phonenumbervalidation;

import com.automation.lac.qa.fanapp.mobile.screens.commons.components.SelectCountryWidget;
import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class VerifyPhoneNumberScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "textContains(\"VERIFY YOUR PHONE NUMBER\")")
  @iOSXCUITFindBy(iOSNsPredicate = "label CONTAINS 'PHONE NUMBER VALIDATION'")
  private WebElement lblVerifyYourPhone;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"country code\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_dropdown_input_country_code_field'")
  private WebElement ddlCountry;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='phone number*, required']/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_phone_number_field' "
    + "AND type CONTAINS 'TextField'")
  private WebElement inputPhoneNumber;

  @AndroidFindBy(id = "btnYES, VERIFY MY PHONE NUMBER")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_yes,_verify_my_phone_number_ds_button'")
  private WebElement btnVerifyMyNumber;

  @AndroidFindBy(xpath = "//android.view.View[@content-desc='Search']/../../..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Clippers + ID-STG'")
  private SelectCountryWidget selectCountryWidget;
}