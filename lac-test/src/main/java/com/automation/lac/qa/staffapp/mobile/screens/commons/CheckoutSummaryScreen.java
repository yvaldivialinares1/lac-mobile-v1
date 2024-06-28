package com.automation.lac.qa.staffapp.mobile.screens.commons;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.payment.components.PaymentCardComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class CheckoutSummaryScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'TitleHeader-TitleHeader'")
  private WebElement lblTitleEvent;

  @iOSXCUITFindBy(id = "Date")
  private WebElement lblEventDate;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'fan_tab_item_' AND label CONTAINS 'garage'")
  private List<WebElement> lstGarageButtons;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'stepper_minus_button'")
  private List<WebElement> lstMinusButtons;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'select_seat_count'")
  private List<WebElement> lstSelectedPlaces;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'stepper_plus_button'")
  private List<WebElement> lstPlusButtons;

  @iOSXCUITFindBy(id = "CHANGE")
  private WebElement btnChangePaymentMethod;

  private PaymentCardComponent paymentCardComponent;

  @iOSXCUITFindBy(id = "checkout_payment_warning_message_text")
  private WebElement lblWarningPayment;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'unselected' or name == 'checkbox'")
  private WebElement chkTermsAndConditions;

  @iOSXCUITFindBy(id = "Total")
  private WebElement lblTotalParking;

  @iOSXCUITFindBy(id = "Price")
  private WebElement lblTotalPrice;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'buttonCANCEL'")
  private WebElement btnCancel;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'buttonPLACE ORDER'")
  private WebElement btnPlaceOrder;
}
