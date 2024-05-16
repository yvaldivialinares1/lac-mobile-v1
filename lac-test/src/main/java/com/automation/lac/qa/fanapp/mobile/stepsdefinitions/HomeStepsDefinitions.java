package com.automation.lac.qa.fanapp.mobile.stepsdefinitions;

import com.automation.lac.qa.fanapp.mobile.tasks.home.HomeTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.home.WelcomeHomeTask;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyLoggedProfileTask;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyUnloggedProfileTask;
import io.cucumber.java.en.And;

public class HomeStepsDefinitions {

  private final HomeTasks homeTasks = new HomeTasks();

  private final MyLoggedProfileTask myLoggedProfileTasks = new MyLoggedProfileTask();
  private final WelcomeHomeTask welcomeHomeTask = new WelcomeHomeTask();
  private final MyUnloggedProfileTask myUnloggedProfileTask = new MyUnloggedProfileTask();

  @And("the user navigates to my profile")
  public void theUserNavigatesToMyProfile() {
    homeTasks.goToMyProfileFromHome();
  }

  /**
   * the user navigates to Tickets Screen
   */
  @And("^the user navigates to Tickets Screen( from my profile)?$")
  public void userNavigateToTicketScreen(String from) {
    if (from != null && from.contains("from my profile")) {
      myLoggedProfileTasks.clickBackOnMyProfile();
    }
    homeTasks.goToTickets();
  }

  @And("the user goes to Login screen from Welcome Home screen")
  public void theUserGoesToLoginScreenFromWelcomeHomeScreen() {
    myUnloggedProfileTask.goToLoginFromWelcomeHomeScreen();
  }
}