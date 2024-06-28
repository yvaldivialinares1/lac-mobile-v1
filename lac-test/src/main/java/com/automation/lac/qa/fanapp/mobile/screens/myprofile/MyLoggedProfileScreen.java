package com.automation.lac.qa.fanapp.mobile.screens.myprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class MyLoggedProfileScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "descriptionContains(\"My Account Settings\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_btn_home_my_profile_account_settings_title\"")
  private WebElement btnMyAccountSettings;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"My Payments\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_btn_home_my_profile_payments_title\"")
  private WebElement btnMyPayments;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"My Tickets\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_btn_home_my_profile_tickets_title\"")
  private WebElement btnMyTickets;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"My Vehicles\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_btn_home_my_profile_vehicles_title\"")
  private WebElement btnMyVehicles;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"My Rewards\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_btn_home_my_profile_rewards_title\"")
  private WebElement btnMyRewards;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"App Settings\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"id_btn_home_my_profile_app_settings_title\"")
  private WebElement btnAppSettings;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Log Out\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'id_home_my_profile_logout_title'")
  private WebElement btnLogOut;

  @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[3]/android.view.View")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS \"Card\" AND type == \"XCUIElementTypeButton\"")
  private List<WebElement> lstReminderCards;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Card number\")")
  private WebElement lblCardNumber;

  @AndroidFindBy(uiAutomator = "textContains(\"Enroll your Game Face ID\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"Enroll your Game Face ID\"")
  private WebElement lnkGameFaceIdReminder;

  @AndroidFindBy(uiAutomator = "textContains(\"Verify your Age\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"Verify your Age\"")
  private WebElement lnkVerifyAgeReminder;

  @AndroidFindBy(uiAutomator = "textContains(\"Add your cards\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"Add your cards\"")
  private WebElement lnkAddYourCards;

  @AndroidFindBy(uiAutomator = "textContains(\"Add identity Pass\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == \"Add Identity Pass\"")
  private WebElement lnkAddIdentityPass;

  @AndroidFindBy(uiAutomator = "textContains(\"YOUR NEXT\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS \"Your next\"")
  private WebElement pnlYourNextEvent;

  @iOSXCUITFindBy(accessibility = "event_list_row_cta_title_label")
  private WebElement lnkTransferTickets;

  @AndroidFindBy(uiAutomator = "descriptionContains(\"Back\")")
  @iOSXCUITFindBy(iOSNsPredicate = "name == 'Back' AND type == 'XCUIElementTypeButton'")
  private WebElement btnBack;

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Card number')]"
    + "/parent::android.view.View/parent::android.view.View")
  private WebElement reminderCardsView;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='COMPLETE YOUR PROFILE!']"
    + "/following-sibling::android.view.View//android.widget.TextView[3]")
  private WebElement reminderCardsText;

  @AndroidFindBy(xpath = "(//android.view.View[contains(@content-desc,'Card number')])[1]")
  private WebElement reminderCardPosition;

  @AndroidFindBy(uiAutomator = "resourceId(\"bannerMessageRow\")"
    + ".childSelector(className(\"android.view.View\"))")
  @iOSXCUITFindBy(iOSNsPredicate = "TBD")
  private WebElement lblBannerMessage;

  protected String xpathCard = "//android.widget.TextView[contains(@text,'%s')]";
}
