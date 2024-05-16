package com.automation.lac.qa.fanapp.mobile.screens.educationals;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class TeammateAccountsEducationalScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "resourceId(\"ivEmptyTeammateAccountImage\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH 'img_'")
  private WebElement imgTeammateAccountsLogo;

  @AndroidFindBy(uiAutomator = "resourceId(\"tvTeammateAccountEmptyTitle\")")
  @iOSXCUITFindBy(accessibility = "id_label_title_id")
  private WebElement lblTeammateAccountsTitle;

  @AndroidFindBy(uiAutomator = "resourceId(\"btnAdd Teammate To Your Profile\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'ADD TEAMMATE ACCOUNT' AND type CONTAINS 'Button'")
  private WebElement btnAddTeammate;

  @AndroidFindBy(uiAutomator = "description(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND  type == 'XCUIElementTypeButton'")
  private WebElement btnBack;
}