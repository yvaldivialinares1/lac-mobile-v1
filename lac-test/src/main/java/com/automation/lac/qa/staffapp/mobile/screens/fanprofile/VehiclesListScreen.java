package com.automation.lac.qa.staffapp.mobile.screens.fanprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.commons.components.BannerMessageComponent;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.VehicleInfoComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class VehiclesListScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "fan_identity_identity_pass_wiew_close_button")
  private WebElement btnClose;

  @iOSXCUITFindBy(id = "fan_vehicle_list_back_button")
  private WebElement btnBack;

  @iOSXCUITFindBy(id = "fan_vehicle_label")
  private WebElement lblFanVehicles;

  @iOSXCUITFindBy(id = "fan_identity_add_vehicle_text")
  private WebElement btnAddVehicle;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name, 'vehicle_info_result_list')"
    + " and @visible='true'] | //XCUIElementTypeStaticText[contains"
    + "(@name, 'vehicle_info_result_list') and @visible='true']")
  private List<VehicleInfoComponent> vehiclesList;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'staff_vehicle_list_quantity_bar'")
  private WebElement lblStaffVehicle;

  private BannerMessageComponent bannerMessageComponent;
}
