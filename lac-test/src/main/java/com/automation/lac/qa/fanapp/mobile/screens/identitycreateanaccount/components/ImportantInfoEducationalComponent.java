package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class ImportantInfoEducationalComponent {

  @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckBox\")")
  @iOSXCUITFindBy(accessibility = "createAccount_educational_checkbox"
    + "-createAccount_educational_checkbox")
  private WebElement chkIHaveRead;

  @AndroidFindBy(className = "android.widget.ScrollView")
  @iOSXCUITFindBy(accessibility = "id_createaccount_educational_description_text")
  private WebElement txtInfoScroll;

  @AndroidFindBy(uiAutomator = "resourceId(\"btnPrimary\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_btn_create_intuit_dome_account_ds_button'")
  private WebElement btnCreateAnIntuitDomeAccount;
}