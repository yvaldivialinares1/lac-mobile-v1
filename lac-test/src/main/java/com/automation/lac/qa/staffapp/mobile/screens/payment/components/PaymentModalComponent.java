package com.automation.lac.qa.staffapp.mobile.screens.payment.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name = 'payment_list_modal_header_title']/..")
public class PaymentModalComponent extends Widget {

  @iOSXCUITFindBy(id = "payment_list_modal_header_title")
  private WebElement lblTitle;

  @iOSXCUITFindBy(id = "CLOSE")
  private WebElement btnClose;

  @iOSXCUITFindBy(id = "ADD CARD")
  private WebElement btnAddCard;

  private List<PaymentCardComponent> lstPaymentMethods;

  public PaymentModalComponent(WebElement element) {
    super(element);
  }
}
