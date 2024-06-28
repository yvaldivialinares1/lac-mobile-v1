package com.automation.lac.qa.staffapp.mobile.screens.fanprofile;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.fanprofile.components.TeamMateInfoComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class TeammatesListScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "mini_fan_list_quantity_bar")
  private WebElement teamMatesListTitle;

  @iOSXCUITFindBy(id = "close_button")
  private WebElement btnClose;

  @iOSXCUITFindBy(id = "ADD TEAMMATE")
  private WebElement btnAddTeammate;

  @iOSXCUITFindBy(iOSNsPredicate = "name CONTAINS 'minor_sub_record_result_list'")
  private List<TeamMateInfoComponent> teammatesList;
}
