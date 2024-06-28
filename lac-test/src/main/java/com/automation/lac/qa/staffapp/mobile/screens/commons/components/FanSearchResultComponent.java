package com.automation.lac.qa.staffapp.mobile.screens.commons.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class FanSearchResultComponent extends Widget {

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'profile_name_initials'")
  private WebElement lblInitials;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'first_last_name_label'")
  private WebElement lblFirstLastName;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'chosen_name_label'")
  private WebElement lblChosenName;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'email_label'")
  private WebElement lblEmail;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'phone_label'")
  private WebElement lblPhone;

  protected FanSearchResultComponent(WebElement element) {
    super(element);
  }
}
