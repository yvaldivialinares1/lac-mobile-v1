package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.home;

import com.automation.lac.qa.staffapp.mobile.enums.NavigationBarOption;
import com.automation.lac.qa.staffapp.mobile.tasks.home.NavigationBarTask;
import io.cucumber.java.en.And;

public class NavigationStepDefinitions {

  private NavigationBarTask navigationBarTask = new NavigationBarTask();

  @And("employee select the {string} navigation bar option")
  public void employeeSelectTheNavigationBarOption(String navigationBarOption) {
    NavigationBarOption option = NavigationBarOption.valueOf(navigationBarOption);
    navigationBarTask.selectNavigationBarOption(option);
  }
}
