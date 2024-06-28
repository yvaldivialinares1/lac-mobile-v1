package com.automation.lac.qa.staffapp.mobile.screens.fanprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.payment.components.AddPaymentMethodModalComponent;
import com.automation.lac.qa.staffapp.mobile.screens.payment.components.CardDetailsModalComponent;
import com.automation.lac.qa.staffapp.mobile.screens.payment.components.PaymentCardComponent;
import com.automation.lac.qa.staffapp.mobile.screens.payment.components.PaymentModalComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class FanProfilePaymentsScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "payment_method_title")
  private WebElement lblTitle;

  @iOSXCUITFindBy(id = "view_all_button")
  private WebElement btnViewAll;

  @iOSXCUITFindBy(id = "ADD CARD")
  private WebElement btnAddCard;

  @iOSXCUITFindBy(id = "no_payments_view")
  private WebElement emptyState;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'banner_message_text'")
  private WebElement lblBannerMessage;

  private List<PaymentCardComponent> lstPaymentMethods;
  private PaymentModalComponent paymentModalComponent;
  private CardDetailsModalComponent cardDetailsModalComponent;
  private AddPaymentMethodModalComponent addPaymentMethodModalComponent;
}
