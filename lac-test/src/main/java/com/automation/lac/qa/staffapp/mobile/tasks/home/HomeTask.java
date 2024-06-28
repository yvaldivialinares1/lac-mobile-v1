package com.automation.lac.qa.staffapp.mobile.tasks.home;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.staffapp.mobile.screens.home.HomeScreen;
import com.automation.lac.qa.staffapp.mobile.tasks.common.FanSearchTask;
import io.qameta.allure.Step;

public class HomeTask extends HomeScreen {

  /**
   * Tap on the home screen search bar.
   *
   * @return FanSearchTask
   */
  @Step("activate home screen manual search input")
  public FanSearchTask tapOnHomeScreenSearchFanButton() {
    getFanBarComponent().clickOnSearchFanButton();
    return new FanSearchTask();
  }

  /**
   * Tap on the header search button.
   *
   * @return FanSearchTask
   */
  @Step("activate top header manual search input")
  public FanSearchTask tapOnHeaderSearchButton() {
    click(getNavigationHeaderComponent().getBtnSearch(), "Search fan");
    return new FanSearchTask();
  }
}
