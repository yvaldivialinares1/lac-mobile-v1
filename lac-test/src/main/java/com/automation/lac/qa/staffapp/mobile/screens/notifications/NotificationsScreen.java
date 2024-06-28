package com.automation.lac.qa.staffapp.mobile.screens.notifications;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.notifications.components.FilterBannerComponent;
import com.automation.lac.qa.staffapp.mobile.screens.notifications.components.NotificationBannerComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class NotificationsScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "back_button")
  private WebElement btnBack;

  @iOSXCUITFindBy(id = "Notifications")
  private WebElement headerTitle;

  @iOSXCUITFindBy(id = "filter_button")
  private WebElement btnFilter;

  @iOSXCUITFindBy(id = "add_button")
  private WebElement btnAdd;

  @iOSXCUITFindBy(id = "no_message_image")
  private WebElement imgEmptyState;

  @iOSXCUITFindBy(id = "no_message_heading1")
  private WebElement titleEmptyState;

  @iOSXCUITFindBy(id = "no_message_heading2")
  private WebElement msgEmptyState;

  @iOSXCUITFindBy(id = "no_message_button")
  private WebElement btnEmptyState;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[starts-with(@name, 'unread')]")
  private List<WebElement> lstUnreadNotifications;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[starts-with(@name, 'read')]")
  private List<WebElement> lstReadNotifications;

  @iOSXCUITFindBy(xpath = "//*[contains(@name, 'read_notification')]/XCUIElementTypeStaticText[1]")
  private List<WebElement> lstNotificationsTitles;

  @iOSXCUITFindBy(xpath = "//*[contains(@name, 'read_notification')]/XCUIElementTypeStaticText[2]")
  private List<WebElement> lstNotificationsDescriptions;

  @iOSXCUITFindBy(xpath = "//*[contains(@name, 'read_notification')]/XCUIElementTypeStaticText[3]")
  private List<WebElement> lstNotificationsCategories;

  @iOSXCUITFindBy(xpath = "//*[contains(@name, 'read_notification')]/XCUIElementTypeStaticText[4]")
  private List<WebElement> lstNotificationsDates;

  private FilterBannerComponent filterBannerComponent;
  private NotificationBannerComponent notificationBannerComponent;
}
