package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.home;

import static com.automation.lac.qa.staffapp.mobile.stepsdefinitions.CommonsStep.getFanAccountContextDataByAccountType;

import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.mobile.enums.MyProfileMenuOptions;
import com.automation.lac.qa.staffapp.mobile.enums.NavigationBarOption;
import com.automation.lac.qa.staffapp.mobile.tasks.home.HomeTask;
import io.cucumber.java.en.And;

public class HomeStepDefinitions {

  private final HomeTask homeTask = new HomeTask();

  @And("the user selects the {string} navigation bar option")
  public void employeeSelectTheNavigationBarOption(String navigationBarOption) {
    NavigationBarOption option = NavigationBarOption.valueOf(navigationBarOption);
    homeTask.getNavigationBarComponent().selectNavigationBarOption(option);
  }

  /**
   * User select the Menu option from staff member profile menu
   */
  @And("the user selects the {string} option from staff member profile menu")
  public void userSelectsTheMyProfileMenuOption(String myProfileMenuOption) {
    MyProfileMenuOptions option = MyProfileMenuOptions.valueOf(myProfileMenuOption);
    homeTask.getMyUserProfileComponent().selectMenuOption(option);
  }

  /**
   * User select staff member profile option
   */
  @And("the user selects staff member profile option")
  public void userSelectsStaffMemberProfileOption() {
    homeTask.getNavigationHeaderComponent().clickOnUserMyProfile();
  }

  /**
   * Search the fan from home screen, then select fan note from search results.
   */
  @And("the user searches and finds the fan {string} account from the home screen")
  public void userSearchAndFindFanFromHomeScreen(String fanAccountType) {
    IntuitDomeAccountDto intuitDomeAccount = getFanAccountContextDataByAccountType(fanAccountType);
    homeTask.tapOnHomeScreenSearchFanButton()
      .enterInputInManualSearchField(intuitDomeAccount.getEmail())
      .selectFanAccountByEmail(intuitDomeAccount.getEmail());
  }

  /**
   * Search the fan from header section, then select fan note from search results.
   */
  @And("the user searches and finds the fan {string} account from the header section")
  public void userSearchAndFindFanFromHeaderSection(String fanAccountType) {
    IntuitDomeAccountDto intuitDomeAccount = getFanAccountContextDataByAccountType(fanAccountType);
    homeTask.tapOnHeaderSearchButton()
      .enterInputInManualSearchField(intuitDomeAccount.getEmail())
      .selectFanAccountByEmail(intuitDomeAccount.getEmail());
  }
}
