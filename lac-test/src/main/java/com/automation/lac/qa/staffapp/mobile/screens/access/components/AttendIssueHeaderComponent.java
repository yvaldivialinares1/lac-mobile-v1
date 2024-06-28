package com.automation.lac.qa.staffapp.mobile.screens.access.components;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class AttendIssueHeaderComponent extends MobileBaseScreen {

  @iOSXCUITFindBy(accessibility = "attend_issue_type_title")
  private WebElement issueTypeTitle;

  @iOSXCUITFindBy(accessibility = "license_plate_title")
  private WebElement licencePlateTitle;

  @iOSXCUITFindBy(accessibility = "state_title")
  private WebElement stateTitle;

  @iOSXCUITFindAll({
    @iOSXCUITBy(accessibility = "SEND TO BOX OFFICE"),
    @iOSXCUITBy(accessibility = "attend_issue_send_to_box_office")
  })
  private WebElement btnSendToBoxOffice;

  @iOSXCUITFindAll({
    @iOSXCUITBy(accessibility = "LEAVE ISSUE"),
    @iOSXCUITBy(accessibility = "attend_issue_leave_issue")
  })
  private WebElement btnLeaveIssue;

  @iOSXCUITFindAll({
    @iOSXCUITBy(accessibility = "FINISH ISSUE"),
    @iOSXCUITBy(accessibility = "attend_issue_finish_issue")
  })
  private WebElement btnFinishIssue;
}