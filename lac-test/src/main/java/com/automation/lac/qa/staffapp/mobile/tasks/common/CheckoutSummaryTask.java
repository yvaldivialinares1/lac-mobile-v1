package com.automation.lac.qa.staffapp.mobile.tasks.common;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitElementWillBeAvailable;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import com.automation.lac.qa.staffapp.mobile.enums.GarageLocation;
import com.automation.lac.qa.staffapp.mobile.enums.TypeOfParking;
import com.automation.lac.qa.staffapp.mobile.screens.commons.CheckoutSummaryScreen;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;

public class CheckoutSummaryTask extends CheckoutSummaryScreen {

  /**
   * Click on garage location
   *
   * @param garageLocation to filter the button
   */
  @Step("Tap on garage location {garageLocation}")
  public void clickOnGarageLocation(GarageLocation garageLocation) {
    getLstGarageButtons().stream().filter(
        garageOption -> containsIgnoreCase(garageOption.getAttribute("label"),
          garageLocation.name())).findFirst()
      .orElseThrow(() -> new CustomException("Garage not found")).click();
  }

  @Step("Tap on plus button for type of parking {typeOfParking}")
  public void addTypeOfParking(TypeOfParking typeOfParking) {
    click(getLstPlusButtons().get(typeOfParking.ordinal()), "Add parking");
    waitElementWillBeAvailable(getLstPlusButtons().get(typeOfParking.ordinal()), 10);
  }

  public void acceptTermsAndConditions() {
    click(getChkTermsAndConditions(), "Terms and conditions");
  }

  public void placeOrder() {
    click(getBtnPlaceOrder(), "Place order");
  }
}
