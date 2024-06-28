package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class CreateAccountEndingScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Title\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"Some stages are still missing\"")
  private WebElement lblStagesStatusTitle;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Complete them later\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Complete them later'")
  private WebElement lblCompleteLaterMessage;

  @AndroidFindBy(uiAutomator = "resourceId(\"ProcessEndingMissingStepsbtnPrimary\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_primary_ds_button'")
  private WebElement btnTakeMeToIntuitDome;

  //TODO: iOS locator is pending due to bugs CA-52499, CA-52110
  @AndroidFindBy(id = "btnCONTINUE WITH YOUR PURCHASE")
  @iOSXCUITFindBy(id = "RBD")
  private WebElement btnContinuePurchase;
}