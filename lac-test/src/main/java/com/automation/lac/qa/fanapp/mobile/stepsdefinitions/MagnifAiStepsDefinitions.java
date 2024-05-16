package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import static com.automation.lac.qa.utils.MagnifAiUtils.generateScreenshotFileName;

import com.automation.lac.qa.fanapp.api.tasks.MagnifAiTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.home.WelcomeHomeTask;
import com.automation.lac.qa.fanapp.mobile.tasks.initialpermissions.TurnOnNotificationTask;
import com.automation.lac.qa.utils.TestContextManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.util.List;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MagnifAiStepsDefinitions {
  TurnOnNotificationTask turnOnNotificationTask = new TurnOnNotificationTask();
  WelcomeHomeTask welcomeHomeTask = new WelcomeHomeTask();

  /**
   *
   */
  @Given("The user opens the Los Angeles Clippers application and try to create an account")
  public void chuckOpensHisLosAngelesClippersApplication(List<String> images) {
    String screenShotName = generateScreenshotFileName();
    TestContextManager.getTestContext().set("screenshot_name", screenShotName);
    turnOnNotificationTask.grantLacPermission();
    MagnifAiTasks.flexibleSearchImage(screenShotName, images);
    welcomeHomeTask.createAnAccount();
  }

  /**
   *
   */
  @Then("The user should be able to see the create account screen")
  public void chuckShouldBeAbleToSeeTheHomeScreen(List<String> images) {
    String screenshotName = TestContextManager.getTestContext().get("screenshot_name");
    MagnifAiTasks.flexibleCompareImage(screenshotName, images);
  }

}