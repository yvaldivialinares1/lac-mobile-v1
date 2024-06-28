package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.educationalexperience;

import com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components.ImportantInformationWidget;
import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class CreateAccountEducationalScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath = "//*[@resource-id='CreateInitialDomeAccountScreenbtnPrimary' or"
    + " @resource-id='btnCREATE INTUIT DOME ACCOUNT']")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_primary_ds_button'")
  private WebElement btnCreateIntuitDomeAccount;

  @AndroidFindBy(id = "modalContentView")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='modal_message_header']/..")
  private ImportantInformationWidget importantInfoWidget;

  @AndroidFindBy(uiAutomator = "resourceId(\"CreateInitialDomeAccountScreentvTitle\")")
  @iOSXCUITFindBy(iOSNsPredicate =
    "name == 'id_label_title' AND label CONTAINS 'create your account'")
  private WebElement lblCreateYourAccountTittle;
}