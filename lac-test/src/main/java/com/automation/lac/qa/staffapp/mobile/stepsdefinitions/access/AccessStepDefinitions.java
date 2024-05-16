package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.access;

import static com.automation.lac.qa.staffapp.constants.ContextConstants.GARAGE_CATEGORY;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;

import com.automation.lac.qa.staffapp.api.enums.GarageCategoryType;
import com.automation.lac.qa.staffapp.mobile.tasks.access.AccessTask;
import io.cucumber.java.en.And;

public class AccessStepDefinitions {

  private final AccessTask accessTask = new AccessTask();

  @And("employee select the random garage under parking access category")
  public void selectRandomParkingCategoryGarage() {
    GarageCategoryType garageCategory = accessTask.selectRandomParkingCategoryGarage();
    getTestContext().set(GARAGE_CATEGORY, garageCategory);
  }
}
