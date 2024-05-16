package com.automation.lac.qa.staffapp.mobile.tasks.access;

import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForListWebElementsIsNotEmpty;

import com.automation.lac.qa.staffapp.api.enums.GarageCategoryType;
import com.automation.lac.qa.staffapp.mobile.screens.access.AccessScreen;
import java.security.SecureRandom;
import org.openqa.selenium.WebElement;

public class AccessTask extends AccessScreen {

  private final SecureRandom random = new SecureRandom();

  /**
   * select the random parking category garage
   */
  public GarageCategoryType selectRandomParkingCategoryGarage() {
    GarageCategoryType[] values = GarageCategoryType.values();
    GarageCategoryType categoryType = values[random.nextInt(values.length)];
    waitForListWebElementsIsNotEmpty(4, getParkingCategoryQueues());

    WebElement categoryQueue =
      getParkingCategoryQueues().stream().filter(q -> q.getText().equals(categoryType.getGarage()))
        .findFirst()
        .orElseThrow();
    click(categoryQueue, categoryType.getGarage());
    return categoryType;
  }
}