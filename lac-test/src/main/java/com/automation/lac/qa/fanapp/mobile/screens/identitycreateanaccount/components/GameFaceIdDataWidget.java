package com.automation.lac.qa.fanapp.mobile.screens.identitycreateanaccount.components;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class GameFaceIdDataWidget extends Widget {

  public GameFaceIdDataWidget(WebElement element) {
    super(element);
  }

  @AndroidFindBy(className = "android.widget.ScrollView")
  @iOSXCUITFindBy(accessibility = "id_createaccount_educational_description_text")
  private WebElement txtInfoScroll;

  @AndroidFindBy(id = "tvPrimaryCta")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'CONTINUE' AND type == 'XCUIElementTypeButton'")
  private WebElement btnContinue;
  
}