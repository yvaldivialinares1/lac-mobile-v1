package com.automation.lac.qa.fanapp.mobile.tasks.home;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MENU;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_ACCOUNT_SETTINGS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_PAYMENTS;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_PROFILE;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.MY_VEHICLES;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.PAYMENT_METHODS_MANAGEMENT;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.SWITCH_CONCERT_FAN;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.THEME_SWITCH;
import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.TICKETS;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementNotVisibleOrDisabled;

import com.automation.lac.qa.fanapp.mobile.enums.SwipeDirections;
import com.automation.lac.qa.fanapp.mobile.screens.home.HomeScreen;
import com.automation.lac.qa.fanapp.mobile.screens.home.MenuScreen;
import com.automation.lac.qa.fanapp.mobile.screens.myprofile.MyLoggedProfileScreen;
import com.automation.lac.qa.fanapp.mobile.screens.paymentmethods.MyPaymentsScreen;
import com.automation.lac.qa.fanapp.mobile.utils.DeviceActions;
import com.automation.lac.qa.utils.mobile.WaitActions;
import io.qameta.allure.Step;

public class HomeTasks extends HomeScreen {

  protected MyPaymentsScreen myPaymentsScreen = new MyPaymentsScreen();
  protected MyLoggedProfileScreen myProfileLoggedScreen = new MyLoggedProfileScreen();
  protected MenuScreen menuScreen = new MenuScreen();

  @Step("User navigates from Home Screen to My Profile Screen")
  public void goToMyProfileFromHome() {
    click(getBtnProfile(), MY_PROFILE.getValue());
  }

  /**
   * Navigates the user from the Home screen to the My Payments screen.
   */
  @Step("User navigates from Home to My Payments")
  public void goToMyPaymentsFromHome() {
    goToMyProfileFromHome();
    WaitActions.waitForElementVisibility(myProfileLoggedScreen.getPnlYourNextEvent(),3);
    DeviceActions.verticallyScrollToElement(myProfileLoggedScreen.getBtnMyPayments(),
      SwipeDirections.DOWN_TO_UP,5, 30);
    click(myProfileLoggedScreen.getBtnMyPayments(), MY_PAYMENTS.getValue());
  }

  /**
   * Navigates the user from the Home screen to the Payment Methods Management screen.
   */
  @Step("User navigates from Home to Payments Methods Management")
  public void goToPaymentMethodsManagementFromHome() {
    goToMyPaymentsFromHome();
    click(myPaymentsScreen.getBtnPaymentMethodManagement(),PAYMENT_METHODS_MANAGEMENT.getValue());
  }

  /**
   * Navigates the user from the Home screen to the My Account Settings screen.
   */

  @Step("User navigates from Home to My Account Settings")
  public void goToMyAccountSettingsFromHome() {
    goToMyProfileFromHome();
    click(myProfileLoggedScreen.getBtnMyAccountSettings(), MY_ACCOUNT_SETTINGS.getValue());
  }

  /**
   * Navigates the user to the My Vehicles section from the Home screen.
   */
  @Step("User navigates to My Vehicles")
  public void goToMyVehiclesFromHome() {
    goToMyProfileFromHome();
    click(myProfileLoggedScreen.getBtnMyVehicles(), MY_VEHICLES.getValue());
  }

  /**
   * Switches the user interface theme to "As a concert fan".
   */
  @Step("User switches to As a concert fan theme")
  public void changeToTheConcertFanTheme() {
    click(getBtnThemeSwitch(), THEME_SWITCH.getValue());
    click(getBtnSwitchToConcertFan(), SWITCH_CONCERT_FAN.getValue());
    waitForElementNotVisibleOrDisabled(3, getBtnSwitchToConcertFan());
  }

  public void goToTickets() {
    click(getBtnMenu(), MENU.getValue());
    click(menuScreen.getBtnTickets(), TICKETS.getValue());
  }
}