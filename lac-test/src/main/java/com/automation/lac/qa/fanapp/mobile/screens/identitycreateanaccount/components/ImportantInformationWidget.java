package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components;

import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class ImportantInformationWidget extends Widget {

  public ImportantInformationWidget(WebElement element) {
    super(element);
  }

  @AndroidFindBy(xpath =
    "//*[@resource-id='modalContentView']/android.widget.ScrollView/android.widget.CheckBox")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'checkbox' AND type CONTAINS 'Button'")
  private WebElement chkIHaveRead;

  @AndroidFindBy(className = "android.widget.ScrollView")
  @iOSXCUITFindBy(accessibility =
    "id_createaccount_educational_title_text-id_createaccount_educational_description_text")
  private WebElement txtInfoScroll;

  @AndroidFindBy(id = "btnPrimary")
  @iOSXCUITFindBy(accessibility = "id_btn_secondary_ds_button")
  private WebElement btnContinue;

  /**
   * Define xpath for checkbox
   * @return xpath for checkbox
   */
  public String chkIHaveReadXpath() {
    String androidXpath = "//android.widget.CheckBox";
    String iosXpath = "//XCUIElementTypeImage[@name='createAccount_educational_checkbox']";
    return isAndroid() ? androidXpath : iosXpath;
  }
}