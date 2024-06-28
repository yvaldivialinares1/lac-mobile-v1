package com.automation.lac.qa.fanapp.mobile.tasks.home;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CLIPPERS_FAN;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.CONCERT_FAN;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.EVENTS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.HOME;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MENU;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_PROFILE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.THEME_SWITCH;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.TICKETS;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.SELECTED_THEME;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeElementToTheBorder;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.TOP_PAGE;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementVisible;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.fanapp.mobile.screens.home.HomeScreen;
import com.automation.lac.qa.fanapp.mobile.tasks.commons.CommonTask;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyLoggedProfileTask;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomeTasks extends HomeScreen {

  private final CommonTask commonTask = new CommonTask();

  /**
   * Navigates from the home page to the user's profile.
   *
   * @return An instance of {@link MyLoggedProfileTask} representing the user's profile.
   */
  @Step("User navigates from Home Screen to My Profile Screen")
  public MyLoggedProfileTask goToMyProfileFromHome() {
    waitForElementVisibility(getBtnProfile(), 5);
    click(getBtnProfile(), MY_PROFILE.getValue());
    return new MyLoggedProfileTask();
  }

  /**
   * Switches the user interface theme to "As a concert fan".
   */
  @Step("User switches to As a concert fan theme")
  public void changeToTheConcertFanTheme() {
    click(getBtnThemeSwitch(), THEME_SWITCH.getValue());
    click(getBtnSwitchToConcertFan(), CONCERT_FAN.getValue());
  }

  /**
   * Go to Ticket list screen based on selected theme
   */
  public void goToTickets() {
    String actualTheme = getTestContext().getOrDefault(SELECTED_THEME.name(), CLIPPERS_FAN.name());
    click(getBtnMenu(), MENU.getValue());
    swipeElementToTheBorder(TOP_PAGE, getMenuWidget().getMenuButton(HOME.name()));
    if (actualTheme.equals(CONCERT_FAN.name()))
      click(getMenuWidget().getMenuButton(EVENTS.name()), EVENTS.getValue());
    else
      click(getMenuWidget().getMenuButton(TICKETS.name()), TICKETS.getValue());
  }

  /**
   * Validate if home screen is displayed after restarting the app
   *
   * @return boolean
   */
  public boolean isHomeDisplayed() {
    return isTheElementVisible(getBtnProfile(), 15);
  }

  /**
   * This method navigates back until home screen
   */
  public void goBackUntilHomeScreenIsDisplayed() {
    for (int i = 0; i < 6; i++) {
      if (isTheElementVisible(getBtnProfile(), 3))
        break;
      commonTask.clickOnBackButton();
    }
  }
}