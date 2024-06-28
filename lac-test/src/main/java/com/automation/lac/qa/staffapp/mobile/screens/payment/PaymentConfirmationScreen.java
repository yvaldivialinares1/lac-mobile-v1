package com.automation.lac.qa.staffapp.mobile.screens.payment;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class PaymentConfirmationScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "take_over_message_ticket_purchase_success_background")
  private WebElement imgPurchaseSuccess;

  @iOSXCUITFindBy(id = "payment_processing_success_title")
  private WebElement lblTitle;

  @iOSXCUITFindBy(id = "payment_processing_success_subtitle")
  private WebElement lblSubtitle;

  @iOSXCUITFindBy(id = "payment_processing_owner")
  private WebElement lblOwner;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'ticket_card_event_name'")
  private WebElement lblEventName;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'seat_type_info_label'")
  private WebElement lblSeatsInfo;

  @iOSXCUITFindBy(id = "MANAGE TICKETS")
  private WebElement btnManageTickets;
}
