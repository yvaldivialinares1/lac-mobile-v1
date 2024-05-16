package com.automation.lac.qa.fanapp.mobile.screens.purchase;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class TransactionSuccessScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "textContains(\"CONTINUE\")")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='modal_view_cta_title_label']")
  private WebElement btnContinue;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='tvPaymentConfirmationTitle']")
  @iOSXCUITFindBy(xpath =
          "//XCUIElementTypeStaticText[@name='transaction_success_header_title_text']")
  private WebElement transactionSuccessTitle;

  @AndroidFindBy(xpath =
          "//android.widget.TextView[@resource-id='tvPaymentConfirmationSubTitle']")
  @iOSXCUITFindBy(xpath =
          "//XCUIElementTypeStaticText[@name='transaction_success_header_sub_title_text']")
  private WebElement transactionSuccessSubTitle;

  @AndroidFindBy(xpath =
          "//android.widget.TextView[@resource-id='tvPaymentConfirmationCarouselInfo0']")
  @iOSXCUITFindBy(xpath =
          "//XCUIElementTypeStaticText[@name='transaction_success_carosel_description0']")
  private WebElement transactionSuccessFirstCarouselInfo;

  @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Next carousel']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='home_hero_carousel_next_button']")
  private WebElement transactionSuccessNextCarousel;

  @AndroidFindBy(xpath = "//android.view.View[@resource-id='bannerMessageRow']"
          + "/android.view.View[1]")
  @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='banner_message_text'])[1]")
  private WebElement transactionSuccessTopBannerDescription;

  @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Close']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='banner_message_close']")
  private WebElement btnTransactionSuccessBannerClose;

}