package com.automation.lac.qa.staffapp.mobile.screens.payment.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name = 'add_new_card_title_identifier']/..")
public class AddPaymentMethodModalComponent extends Widget {

  @iOSXCUITFindBy(id = "add_new_card_title_identifier")
  private WebElement lblTitle;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'add_new_card_discard_button_identifier'"
    + " OR name == 'add_new_card_cancel_button_identifier'")
  private WebElement btnDiscard;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_add_new_card_number_field'"
    + " AND type == 'XCUIElementTypeTextField'")
  private WebElement frmInputCardNumber;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_add_new_card_expire_date_field'"
    + " AND type == 'XCUIElementTypeTextField'")
  private WebElement frmInputExpiryDate;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_add_new_card_security_code_field'"
    + " AND type == 'XCUIElementTypeSecureTextField'")
  private WebElement frmInputSecurityCode;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_input_add_new_card_name_holder_field'"
    + " AND type == 'XCUIElementTypeTextField'")
  private WebElement frmInputNameOnCard;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'checkbox' AND type == 'XCUIElementTypeButton'")
  private WebElement chkAutoFillData;

  @iOSXCUITFindBy(id = "add_new_card_save_button_identifier")
  private WebElement btnAddCard;

  public AddPaymentMethodModalComponent(WebElement element) {
    super(element);
  }
}
