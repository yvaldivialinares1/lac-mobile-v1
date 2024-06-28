package com.automation.lac.qa.staffapp.mobile.screens.commons.components;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;

import com.automation.lac.qa.staffapp.mobile.enums.NavigationBarOption;
import com.automation.lac.qa.utils.CustomException;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeApplication'")
public class NavigationBarComponent extends Widget {

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Home'")
  private WebElement btnHome;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Sales'")
  private WebElement btnSales;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Access'")
  private WebElement btnAccess;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Incidents'")
  private WebElement btnIncidents;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'Wayfinding'")
  private WebElement btnWayFinding;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'More'")
  private WebElement btnMore;

  protected NavigationBarComponent(WebElement element) {
    super(element);
  }

  /**
   * select Navigation Bar Option
   */
  public void selectNavigationBarOption(NavigationBarOption option) {
    switch (option) {
      case HOME:
        click(getBtnHome(), "navigationBarHomeOption");
        return;
      case SALES:
        click(getBtnSales(), "navigationBarSalesOption");
        return;
      case ACCESS:
        click(getBtnAccess(), "navigationBarAccessOption");
        return;
      case INCIDENTS:
        click(getBtnIncidents(), "navigationBarIncidentsOption");
        return;
      case WAY_FINDING:
        click(getBtnWayFinding(), "navigationBarWayFindingOption");
        return;
      case MORE:
        click(getBtnMore(), "navigationBarMoreOption");
        return;
      default:
        throw new CustomException("There is no option to select defined as " + option);
    }
  }
}