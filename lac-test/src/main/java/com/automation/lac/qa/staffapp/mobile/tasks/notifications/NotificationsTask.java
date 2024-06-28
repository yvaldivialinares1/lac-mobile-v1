package com.automation.lac.qa.staffapp.mobile.tasks.notifications;

import static com.automation.lac.qa.staffapp.mobile.enums.NotificationFilterOption.ALL;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.DeviceActions.hideKeyboard;
import static com.automation.lac.qa.utils.mobile.DeviceActions.sendKeys;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import com.automation.lac.qa.staffapp.mobile.enums.NotificationFilterOption;
import com.automation.lac.qa.staffapp.mobile.screens.notifications.NotificationsScreen;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;
import java.util.Arrays;
import net.datafaker.Faker;

public class NotificationsTask extends NotificationsScreen {

  private final Faker faker = new Faker();

  /**
   * Add read or unread notification manually
   *
   * @param isUnreadNotification if is unread is true value
   */
  @Step("Add notifications read or unread when isUnreadNotification is {isUnreadNotification}")
  public void addNotification(boolean isUnreadNotification) {
    Arrays.stream(NotificationFilterOption.values())
      .filter(notificationFilterOption -> !ALL.equals(notificationFilterOption))
      .forEach(
        notificationFilterOption -> {
          click(getBtnAdd(), "Add button");
          sendKeys(
            getNotificationBannerComponent().getInputTitle(), faker.lorem().characters(10),
            "Title");
          hideKeyboard("Return");
          sendKeys(
            getNotificationBannerComponent().getInputDescription(),
            faker.lorem().sentence(5),
            "Description");
          hideKeyboard("Return");
          click(getNotificationBannerComponent().getBtnCategory(), "Category dropdown");
          click(
            getNotificationBannerComponent()
              .getLstBtnCategories()
              .get(notificationFilterOption.ordinal() - 1),
            "Category");
          if (isUnreadNotification)
            click(getNotificationBannerComponent().getSwtReadUnread(), "Unread notification");
          click(getNotificationBannerComponent().getBtnAdd(), "Add notification");
        });
  }

  public void clickOnBackButton() {
    click(getBtnBack(), "Back to home");
  }

  /**
   * Clicking unread notification
   */
  @Step("Mark as read the unread notifications")
  public void clickOnUnreadNotification() {
    if (getLstUnreadNotifications().size() - 1 > 0) {
      click(
        getLstUnreadNotifications().get(getLstUnreadNotifications().size() - 1),
        "Unread notification");
      clickOnUnreadNotification();
    } else {
      click(getLstUnreadNotifications().get(0), "Last unread notification");
    }
  }

  public void clickOnFilterButton() {
    click(getBtnFilter(), "Filter button");
  }

  /**
   * Click on filter option
   *
   * @param filterOption value for filter
   */
  @Step("Tap on filter category {filterOption}")
  public void clickOnFilterCategory(String filterOption) {
    getFilterBannerComponent().getBtnFilterCategories().stream()
      .filter(
        filterCategories ->
          containsIgnoreCase(filterCategories.getAttribute("label"), filterOption))
      .findFirst()
      .orElseThrow(() -> new CustomException("Category not found"))
      .click();
  }

  public void clickOnApplyFilters() {
    click(getFilterBannerComponent().getBtnApplyFilters(), "Apply filters");
  }
}
