package com.automation.lac.qa.fanapp.mobile.screens.paymentmethods;

import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Getter
public class CardDetailsScreen extends MobileBaseScreen {

  //Main..
  @AndroidFindBy(uiAutomator = ".resourceId(\"cardDetailsNicknameAddButton\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"card_details_nickname_add_button\"")
  private WebElement btnGiveItANickname;

  @AndroidFindBy(uiAutomator = ".resourceId(\"cardDetailsNickname\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"card_details_nickname\"")
  private WebElement txtNickname;

  @AndroidFindBy(uiAutomator = ".descriptionContains(\"Edit option\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"card_details_nickname_edit_button\"")
  private WebElement btnEditNickname;

  @AndroidFindBy(uiAutomator = ".descriptionContains(\"Delete payment method button\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"card_details_delete_card_button\"")
  private WebElement btnDeletePaymentMethod;

  @AndroidFindBy(uiAutomator = ".descriptionContains"
    + "(\"Select to Set as preferred payment method for purchases\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"card_details_prefer_checkbox_image\"")
  private WebElement chkPreferredPaymentMethod;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"Back\" AND type == \"XCUIElementTypeButton\"")
  private WebElement btnBackButton;

  //Nickname modal..

  @AndroidFindBy(id = "paymentMethodEditNickNameSave")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"edit_nickname_save_button\"")
  private WebElement btnSaveNicknameAtModal;

  @AndroidFindBy(uiAutomator = ".resourceId(\"paymentMethodEditNickName\")"
    + ".childSelector(className(\"android.widget.EditText\"))")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_input_edit_nickname_text_input_field\"")
  private WebElement txtEditNicknameAtModal;

  @AndroidFindBy(uiAutomator = ".descriptionContains(\"Clear Input\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_btn_edit_nickname_text_input_clear\"")
  private WebElement btnClearEditNicknameAtModal;

  @AndroidFindBy(uiAutomator = ".descriptionContains(\"Cancel\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"edit_nickname_cancel_button\"")
  private WebElement btnCancelEditNicknameAtModal;

  //Confirm deletion modal..

  @AndroidFindBy(uiAutomator = ".resourceId(\"btnPrimary\")"
    + ".childSelector(className(\"android.widget.Button\"))")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"delete_card_primary_button\" AND label == \"DELETE\"")
  private WebElement btnDeleteInConfirmationModal;

  @AndroidFindBy(uiAutomator = ".resourceId(\"tvSecondaryCta\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"delete_card_secondary_button\" "
    + "AND label == \"CANCEL\"")
  private WebElement btnCancelInConfirmationModal;

  @AndroidFindBy(uiAutomator = ".resourceId(\"btndelete and update\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"delete_card_primary_button\"")
  private WebElement btnDeleteAndUpdateInConfirmationModal;

  @AndroidFindBy(uiAutomator = ".resourceId(\"tvBrandName\")")
  @iOSXCUITFindBy(iOSNsPredicate = "type = 'XCUIElementTypeStaticText' "
    + "AND name = 'payment_method_card_name_label'")
  private List<WebElement> lstPaymentMethodsInConfirmationModal;

  @AndroidFindBy(id = "modalContentView")
  @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeScrollView[2]")
  private WebElement deleteModalView;

  /**
   * get card Details Card Number
   */
  public static WebElement getCardNumber(String lastFour) {
    if (!isAndroid()) {
      return getElement(
        By.xpath(String.format("//XCUIElementTypeStaticText[@name='card_"
          + "details_card_number'][contains(@label,'%s')]", lastFour)));
    } else {
      return getElement(
        By.xpath(String.format("//*[@resource-id='tvBrandName'][contains(@text,'%s')]", lastFour)));
    }
  }
}