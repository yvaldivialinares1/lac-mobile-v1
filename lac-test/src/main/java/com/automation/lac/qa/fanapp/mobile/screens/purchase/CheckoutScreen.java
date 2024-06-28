package com.automation.lac.qa.fanapp.mobile.screens.purchase;

import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
@Slf4j
public class CheckoutScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Payment Method']")
  @iOSXCUITFindBy(accessibility = "Payment Method")
  private WebElement txtCheckoutPaymentMethod;

  @AndroidFindBy(id = "addCardButton")
  @iOSXCUITFindBy(accessibility = "payment_method_add_card_title")
  private WebElement btnCheckoutAddCard;

  @AndroidFindBy(id = "addCardButton")
  @iOSXCUITFindBy(accessibility = "payment_method_add_card_title")
  private WebElement btnCheckoutChange;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Google Pay']/preceding-sibling::"
    + "android.view.View[@resource-id ='paymentMethodCardRadioButton']"
    + "[@content-desc='Selected']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@label='radio_button_selected']/"
    + "following-sibling::XCUIElementTypeStaticText[@label = 'Apple Pay']")
  private WebElement appleGooglePaySelected;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='Google Pay']")
  @iOSXCUITFindBy(xpath = "//CUIElementTypeStaticText[@label = 'Apple Pay']")
  private WebElement appleGooglePay;

  @AndroidFindBy(id = "cardDetailsInvalidCardLabel")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='card_error_information']")
  private WebElement cardErrorMessage;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvPreferred']/preceding-sibling::"
    + "android.view.View[@resource-id='paymentMethodCardRadioButton']"
    + "[@content-desc ='Selected']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label ='Preferred card']/"
    + "preceding-sibling::XCUIElementTypeImage[@label = 'radio_button_selected']")
  private WebElement rdoPreferredCard;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvPreferred']/preceding-sibling::"
    + "android.widget.TextView[@resource-id='tvBrandName']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@label='Preferred card']/preceding-sibling::"
    + "XCUIElementTypeStaticText[@name = 'payment_method_card_name_label']")
  private WebElement preferredCard;

  @AndroidFindBy(id = "btnPLACE ORDER")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='PLACE ORDER']")
  private WebElement btnPlaceOrder;

  @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, '$')]"
    + "[@resource-id = 'tvItemPrice']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains"
    + "(@name,'purchase_history_details_item_')]"
    + "/child::XCUIElementTypeStaticText[contains(@value,'$')]")
  private List<WebElement> checkoutSummarySubTotalValue;

  @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'Order Total:')]"
    + "[@resource-id = 'tvItemName']/"
    + "following-sibling::android.widget.TextView[@resource-id='tvItemPrice']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Order Total:']"
    + "/following-sibling::XCUIElementTypeStaticText")
  private WebElement checkoutSummaryOrderTotalValue;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvEventName']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='navigation_bar_subtitle_label']")
  private WebElement checkoutSummaryEventName;

  protected WebElement checkoutSubDescription() {
    return getElement(By.xpath(!isAndroid()
      ? "//XCUIElementTypeStaticText[@name='checkout_seat_details']"
      + "[contains(@label, concat('You've selected %s seats, %s,',''))] "
      : "//android.widget.TextView[@resource-id='tvDescription']"
      + "[contains(@text,'You've selected %s seats, %s')]"));
  }

  /**
   * returning selected radio state for non preferred card in checkout screen
   */
  public static WebElement getRdoNonPreferredCard(String lastFour) {
    String xpath;
    if (!isAndroid()) {
      xpath = String.format("//XCUIElementTypeStaticText[@name='payment_method"
        + "_card_name_label'][contains(@label,'%s')]", lastFour);
    } else {
      xpath = String.format("//android.widget.TextView[contains(@text,'%s')]/"
        + "preceding-sibling::android.view.View[@content-desc ='Selected']", lastFour);
    }
    return getElement(By.xpath(xpath));
  }
}