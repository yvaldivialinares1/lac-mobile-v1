package com.automation.lac.qa.staffapp.mobile.tasks.home;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.enums.NavigationBarOption;
import com.automation.lac.qa.staffapp.mobile.screens.home.components.NavigationBarScreen;
import com.automation.lac.qa.utils.CustomException;

public class NavigationBarTask extends NavigationBarScreen {

  /**
   * select Navigation Ba rOption
   */
  public MobileBaseScreen selectNavigationBarOption(NavigationBarOption option) {
    switch (option) {
      case HOME:
        click(getBtnHome(), "navigationBarHomeOption");
        return this;
      case SALES:
        click(getBtnSales(), "navigationBarSalesOption");
        return this;
      case ACCESS:
        click(getBtnAccess(), "navigationBarAccessOption");
        return this;
      case INCIDENTS:
        click(getBtnIncidents(), "navigationBarIncidentsOption");
        return this;
      case WAY_FINDING:
        click(getBtnWayFinding(), "navigationBarWayFindingOption");
        return this;
      case MORE:
        click(getBtnMore(), "navigationBarMoreOption");
        return this;
      default:
        throw new CustomException("There is no option to select defined as " + option);
    }
  }
}
