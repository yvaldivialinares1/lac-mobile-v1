package com.automation.lac.qa.staffapp.mobile.questions.notifications;

import static com.automation.lac.qa.assertions.SoftAssertManager.getSoftAssert;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheListOfElementsNotEmpty;

import com.automation.lac.qa.staffapp.mobile.screens.notifications.NotificationsScreen;
import io.qameta.allure.Step;

public class NotificationsQuestions extends NotificationsScreen {

  @Step("Validate if the notification screen is visible")
  public void isScreenVisible() {
    getSoftAssert().assertTrue(getHeaderTitle().isDisplayed(), "Notifications screen is visible");
  }

  /**
   * Validate if the read and unread notifications are visible
   *
   * @param areUnreadNotifications true if there are unread notifications
   */
  @Step("Validate if the notifications are visible read/unread when areUnreadNotifications "
    + "is {areUnreadNotifications}")
  public void areNotificationsVisible(boolean areUnreadNotifications) {
    getSoftAssert().assertFalse(getLstReadNotifications().isEmpty(), "Read notifications visible");
    if (areUnreadNotifications) {
      getSoftAssert()
        .assertFalse(getLstUnreadNotifications().isEmpty(), "Unread notifications visible");
    }
  }

  /**
   * Validate if the unread notifications are empty
   */
  @Step("Validate if the unread notifications are empty")
  public void areUnreadNotificationsEmpty() {
    getSoftAssert().assertFalse(isTheListOfElementsNotEmpty(getLstUnreadNotifications(), 5),
      "Empty unread notifications");
  }

  /**
   * Validate is the filter banner is visible
   */
  @Step("Validate if the filter banner is visible")
  public void isTheFilterBannerVisible() {
    if (isIpad()) {
      getSoftAssert()
        .assertTrue(
          getFilterBannerComponent().getFiltersTitle().isDisplayed(), "Filter title is displayed");
    } else {
      getSoftAssert()
        .assertTrue(
          getFilterBannerComponent().getBtnApplyFilters().isDisplayed(),
          "Filter banner is visible");
    }
  }

  /**
   * Validate if the categories on the filter banner are visible
   */
  @Step("Validate is the filter categories are visible")
  public void areFilterCategoriesVisible() {
    getSoftAssert()
      .assertFalse(
        getFilterBannerComponent().getBtnFilterCategories().isEmpty(), "Filter categories visible");
  }

  /**
   * Validate if the notifications are filtered by category
   *
   * @param filterOption option to validate
   */
  @Step("Validate if the notifications are filtered by category {filterOption}")
  public void areNotificationsFilteredByCategory(String filterOption) {
    getLstNotificationsCategories()
      .forEach(
        webElement -> {
          getSoftAssert()
            .assertEquals(webElement.getText().toLowerCase(), filterOption.toLowerCase());
        });
  }

  /**
   * Validate if the filter banner is not visible
   */
  @Step("Validate if the filter banner is not visible")
  public void isTheFilterBannerNotVisible() {
    getSoftAssert().assertTrue(getHeaderTitle().isDisplayed(), "Filter banner is not visible");
  }
}
