package com.automation.lac.qa.fanapp.mobile.screens.teammateaccounts;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class TeammateAccountsScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "resourceId(\"tvTeammateAccountEmptyTitle\")")
  @iOSXCUITFindBy(accessibility = "id_label_title")
  private WebElement lblTeammateAccountsTitle;

  @AndroidFindBy(uiAutomator = "resourceId(\"tvTeammateAccountEmptyDescription\")")
  @iOSXCUITFindBy(accessibility = "id_label_info")
  private WebElement lblTeammateAccountsDescription;

  @AndroidFindBy(uiAutomator = "resourceIdMatches(\"^ivTeammateAccountImage.*\")")
  @iOSXCUITFindBy(accessibility = "mini_fans_mini_fan_icon")
  private List<WebElement> imgTeammateAccountIcon;

  @AndroidFindBy(uiAutomator = "resourceIdMatches(\"^tvTeammateAccountTitle.*\")")
  @iOSXCUITFindBy(accessibility = "id_label_mini_fan_item_title")
  private List<WebElement> lblTeammateAccountName;

  @AndroidFindBy(uiAutomator = "resourceIdMatches(\"^tvTeammateAccountDescription.*\")")
  @iOSXCUITFindBy(accessibility = "id_label_mini_fan_item_info")
  private List<WebElement> lblTeammateAccountDateOfBirth;

  @AndroidFindBy(xpath = "resourceId(\"btnTeammateAccountDelete\")")
  @iOSXCUITFindBy(accessibility = "id_btn_mini_fan_item_delete_icon")
  private List<WebElement> btnDeleteMTeammateAccount;

  @AndroidFindBy(uiAutomator = "resourceId(\"btnAdd Teammate To Your Profile\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'ADD TEAMMATE' AND type CONTAINS 'Button'")
  private WebElement btnAddTeammateToYourProfile;

  @AndroidFindBy(accessibility = "Back")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND type == 'XCUIElementTypeButton'")
  private WebElement btnBack;
}