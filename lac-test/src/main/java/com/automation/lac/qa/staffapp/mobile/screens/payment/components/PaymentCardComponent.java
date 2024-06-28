package com.automation.lac.qa.staffapp.mobile.screens.payment.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'payment_method_card_background' AND"
  + " type == 'XCUIElementTypeButton'")
public class PaymentCardComponent extends Widget {

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'payment_method_card_logo'")
  private WebElement imgCardLogo;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'payment_method_card_name_label'")
  private WebElement lblCardName;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'payment_method_card_preferred_label'")
  private WebElement lblPreferredMethod;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'payment_method_card_status'")
  private WebElement lblCardStatus;

  public PaymentCardComponent(WebElement element) {
    super(element);
  }
}
