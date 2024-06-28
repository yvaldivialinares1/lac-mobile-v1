package com.automation.lac.qa.staffapp.mobile.screens.payment;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.payment.components.AddPaymentMethodModalComponent;
import com.automation.lac.qa.staffapp.mobile.screens.payment.components.PaymentCardComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AllPaymentMethodsScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "payment_back_button")
  private WebElement btnBack;

  @iOSXCUITFindBy(id = "payment_list_modal_header_title")
  private WebElement lblTitle;

  @iOSXCUITFindBy(id = "ADD CARD")
  private WebElement btnAddCard;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'banner_message_text'")
  private WebElement lblBannerMessage;

  private List<PaymentCardComponent> lstPaymentMethods;
  private AddPaymentMethodModalComponent addPaymentMethodModalComponent;
}
