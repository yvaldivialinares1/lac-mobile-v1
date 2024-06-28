package com.automation.lac.qa.fanapp.mobile.screens.teammateaccounts;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AddTeammateAccountScreen extends MobileBaseScreen {

  @AndroidFindBy(xpath = "//android.view.View[matches(@content-desc, '^first name', 'i')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'input_first_name' AND type CONTAINS 'TextField'")
  private WebElement inputFirstName;

  @AndroidFindBy(xpath = "//android.view.View[matches(@content-desc, '^last name', 'i')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'input_last_name' AND type CONTAINS 'TextField'")
  private WebElement inputLastName;

  @AndroidFindBy(uiAutomator = "resourceId(\"tfPreferred Name\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'preferred_name' AND type CONTAINS 'TextField'")
  private WebElement inputPreferredName;

  @AndroidFindBy(xpath = "//android.view.View[starts-with(@content-desc, 'date of birth')]/..")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Date Picker'")
  private WebElement inputDateOfBirth;

  @AndroidFindBy(uiAutomator = "resourceId(\"btnAdd Teammate Account\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'ADD TEAMMATE' AND type CONTAINS 'Button'")
  private WebElement btnAddTeammateAccount;
}