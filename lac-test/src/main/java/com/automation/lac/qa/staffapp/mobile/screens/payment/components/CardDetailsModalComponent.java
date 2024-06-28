package com.automation.lac.qa.staffapp.mobile.screens.payment.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name = "
  + "'payment_card_details_modal_header_title']/..")
public class CardDetailsModalComponent extends Widget {

  @iOSXCUITFindBy(id = "payment_card_details_modal_back_button")
  private WebElement btnBack;

  @iOSXCUITFindBy(id = "payment_card_details_modal_close_button")
  private WebElement btnClose;

  @iOSXCUITFindBy(id = "card_background")
  private WebElement imgCard;

  @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == "
    + "'payment_card_details_modal_card_number'`][1]")
  private WebElement lblCardNumber;

  @iOSXCUITFindBy(iOSNsPredicate = "label == 'Expiry date'")
  private WebElement lblExpiryDate;

  @iOSXCUITFindBy(id = "payment_card_details_modal_checkbox")
  private WebElement checkboxPreferredCard;

  @iOSXCUITFindBy(id = "payment_card_details_modal_delete_button")
  private WebElement btnDeletePaymentMethod;

  public CardDetailsModalComponent(WebElement element) {
    super(element);
  }
}
