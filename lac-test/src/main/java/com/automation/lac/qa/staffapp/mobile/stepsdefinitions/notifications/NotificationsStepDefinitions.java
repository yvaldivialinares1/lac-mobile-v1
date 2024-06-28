package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.notifications;

import static com.automation.lac.qa.pages.MobileBaseScreen.isIpad;
import static com.automation.lac.qa.staffapp.mobile.enums.NotificationFilterOption.ALL;

import com.automation.lac.qa.staffapp.mobile.questions.notifications.NotificationsQuestions;
import com.automation.lac.qa.staffapp.mobile.tasks.home.HomeTask;
import com.automation.lac.qa.staffapp.mobile.tasks.notifications.NotificationsTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NotificationsStepDefinitions {

  private final HomeTask homeTask = new HomeTask();
  private final NotificationsTask notificationsTask = new NotificationsTask();
  private final NotificationsQuestions notificationsQuestions = new NotificationsQuestions();

  /**
   * Add read and unread notifications
   */
  @And("the user has read and unread notifications")
  public void theUserHasReadAndUnreadNotifications() {
    homeTask.getNavigationHeaderComponent().clickOnNotificationsIcon();
    notificationsTask.addNotification(false);
    notificationsTask.addNotification(true);
    if (isIpad()) {
      homeTask.getNavigationHeaderComponent().clickOnNotificationsIcon();
    } else {
      notificationsTask.clickOnBackButton();
    }
  }

  @When("the user goes to notifications module")
  public void theUserGoesToNotificationsModule() {
    homeTask.getNavigationHeaderComponent().clickOnNotificationsIcon();
  }

  @Then("the user sees the notification module with the notifications")
  public void theUserSeesTheNotificationModuleWithTheNotifications() {
    notificationsQuestions.isScreenVisible();
    notificationsQuestions.areNotificationsVisible(true);
  }

  @When("the user taps on unread notifications")
  public void theUserTapsOnUnreadNotifications() {
    notificationsTask.clickOnUnreadNotification();
  }

  @Then("the notifications are mark as read")
  public void theNotificationsAreMarkAsRead() {
    notificationsQuestions.areUnreadNotificationsEmpty();
  }

  /**
   * Applied filter by category
   */
  @When("the user filters the notification by the category {string}")
  public void theUserFiltersTheNotificationByTheCategory(String filterOption) {
    notificationsTask.clickOnFilterButton();
    notificationsQuestions.isTheFilterBannerVisible();
    notificationsQuestions.areFilterCategoriesVisible();
    if (isIpad()) {
      notificationsTask.clickOnFilterCategory(ALL.getName());
      notificationsTask.clickOnFilterButton();
      notificationsTask.clickOnFilterCategory(filterOption);
    } else {
      notificationsTask.clickOnFilterCategory(filterOption);
      notificationsTask.clickOnApplyFilters();
    }
    notificationsQuestions.isTheFilterBannerNotVisible();
  }

  @Then("the user should see only the notifications by category {string}")
  public void theUserSeesOnlyTheNotificationsByCategory(String filterOption) {
    notificationsQuestions.areNotificationsFilteredByCategory(filterOption);
  }

  /**
   * Applies All filter category
   */
  @And("the user applies All option filter")
  public void theUserAppliesAllOptionFilter() {
    if (isIpad()) {
      notificationsTask.clickOnFilterCategory(ALL.getName());
    } else {
      theUserFiltersTheNotificationByTheCategory(ALL.getName());
    }
  }

  /**
   * Remove the filter
   */
  @When("the user removes the applied filter")
  public void theUserRemoveTheAppliedFilter() {
    notificationsTask.clickOnFilterButton();
    notificationsQuestions.isTheFilterBannerVisible();
    notificationsQuestions.areFilterCategoriesVisible();
    if (isIpad()) {
      notificationsTask.clickOnFilterCategory(ALL.getName());
    } else {
      theUserFiltersTheNotificationByTheCategory(ALL.getName());
    }
    notificationsQuestions.isTheFilterBannerNotVisible();
  }

  @Then("the user should see all the notifications read")
  public void theUserSeesAllTheNotificationsRead() {
    notificationsQuestions.areNotificationsVisible(false);
  }
}
