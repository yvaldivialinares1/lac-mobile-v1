package com.automation.lac.qa.fanapp.mobile.screens.modals;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class ModalPopupScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath = "//*[@resource-id='tvModalTitle']")
  @iOSXCUITFindBy(accessibility = "modal_message_title")
  private WebElement lblModalTitle;

  @AndroidFindBy(xpath = "//*[@resource-id='tvModalDescription']")
  @iOSXCUITFindBy(accessibility = "modal_message_description")
  private WebElement lblModalDescription;

  @AndroidFindBy(xpath = "//*[@resource-id='tvModalDescription']")
  @iOSXCUITFindBy(accessibility = "id_parking_modal_description")
  private WebElement lblModalDescriptionForParking;

  @AndroidFindBy(xpath = "//*[@resource-id='tvPrimaryCta']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='modal_message_primary_button']")
  private WebElement lblModalPrimaryButton;

  @AndroidFindBy(xpath = "//*[@resource-id='tvSecondaryCta']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='modal_message_secondary_button']")
  private WebElement lblModalSecondaryButton;

  @AndroidFindBy(xpath = "//*[@resource-id='btnPrimary']")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='modal_message_primary_button']")
  private WebElement btnModalPrimary;

  @AndroidFindBy(xpath = "//android.widget.TextView[ends-with(@content-desc,'seconds') "
    + "or ends-with(@text,'seconds')] ")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name,"
    + "'modal_message_time_counter')]")
  private WebElement lblModalTimer;
}


