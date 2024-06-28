package com.automation.lac.qa.staffapp.mobile.screens.payment;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class CardDetailsScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "payment_card_details_modal_back_button")
  private WebElement btnBack;

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
}
