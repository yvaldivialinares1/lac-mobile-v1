package com.automation.lac.qa.fanapp.mobile.screens.sharepayments;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class SharePaymentsScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(accessibility = "Share cards with Teammate Accounts")
  @AndroidFindBy(id = "tvPaymentMethodTeammateAccountCard")
  private WebElement shareTeammateAccountCard;

  @iOSXCUITFindBy(accessibility = "Share cards with Fan Accounts")
  @AndroidFindBy(id = "tvPaymentMethodFanCard")
  private WebElement shareFanAccountCard;

  @iOSXCUITFindBy(accessibility = "share_with_fan_cms_foreground_image")
  @AndroidFindBy(id = "ivUserImage")
  private WebElement imgShareWithAccountsLogo;

  @iOSXCUITFindBy(accessibility = "share_with_fan_cms_title")
  @AndroidFindBy(id = "ivPaymentInfoTitle")
  private WebElement lblShareCardsAccountsTitle;

  @iOSXCUITFindBy(accessibility = "navigation_bar_title_label")
  @AndroidFindBy(id = "tvPaymentMethodTitle")
  private WebElement lblShareCardsHeaderTitle;

  @iOSXCUITFindBy(accessibility = "primary_rectangle_button")
  @AndroidFindBy(id = "btnSHARE PAYMENT METHOD")
  private WebElement btnSharePaymentMethod;

  @AndroidFindBy(uiAutomator = "description(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND  type == 'XCUIElementTypeButton'")
  private WebElement btnBack;

  @iOSXCUITFindBy(accessibility = "id_btn_share_ds_button")
  @AndroidFindBy(id = "btnSHARE")
  private WebElement btnShare;

  @iOSXCUITFindBy(accessibility = "id_btn_continue_ds_button")
  @AndroidFindBy(id = "btnCONTINUE")
  private WebElement btnContinue;

  @iOSXCUITFindBy(accessibility = "id_btn_yes,_confirm_and_share_ds_button")
  @AndroidFindBy(accessibility = "YES, CONFIRM AND SHARE")
  private WebElement btnConfirmAndShare;

  @iOSXCUITFindBy(accessibility = "GO BACK")
  @AndroidFindBy(accessibility = "go back")
  private WebElement btnGoBack;

  @AndroidFindBy(id = "dropDownTeammateAccount")
  @iOSXCUITFindBy(accessibility = "share_mini_fan_dropdown")
  private WebElement dropDownTeammateAccount;

  protected String androidAccountsToShareWithText =
    "//android.widget.TextView[contains(@text, '%s')]";

  protected String iosAccountsToShareWithText =
    "//XCUIElementTypeStaticText[@value='%s']";

  protected String androidCardSharedWithAccount =
    "//android.widget.TextView[contains(@text, '%s')]/following-sibling"
      + "::android.view.View[@content-desc='Payment Method sharedYES']/parent::android.view.View"
      + "/parent::android.view.View//android.widget.TextView[@text='%s']";

  protected String iosCardSharedWithAccount =
    "//XCUIElementTypeStaticText[contains(@value,'%s')]/preceding-sibling::"
      + "XCUIElementTypeStaticText[@value ='%s']/parent::XCUIElementTypeOther"
      + "//XCUIElementTypeOther[@value='Yes']";
}
