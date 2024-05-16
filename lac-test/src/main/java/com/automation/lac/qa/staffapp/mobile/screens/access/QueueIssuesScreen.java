package com.automation.lac.qa.staffapp.mobile.screens.access;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.access.components.EventCardComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class QueueIssuesScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(accessibility = "all_issue_tab_bar")
  private WebElement pendingIssuesTab;

  @iOSXCUITFindBy(accessibility = "pending_issues_tab_bar")
  private WebElement pendingIssuesTabBar;

  @iOSXCUITFindBy(accessibility = "my_view_tab_bar")
  private WebElement incomingIssuesTab;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='ADD TO QUEUE']")
  private WebElement btnAddToQueue;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@label='QUEUE DETAILS']")
  private WebElement btnQueueDetails;

  @iOSXCUITFindBy(accessibility = "no_issues_in_view_heading_for_box_office")
  private WebElement noCurrentIssuesInThisViewMessage;

  @iOSXCUITFindBy(accessibility = "//XCUIElementTypeImage[@name='no_store_image']")
  private WebElement noCurrentIssuesInThisViewMessageImage;

  @iOSXCUITFindBy(accessibility = "no_idaps_selected_image")
  private WebElement noIdapsSelectedImage;

  @iOSXCUITFindBy(accessibility = "no_idaps_selected_button")
  private WebElement btnSelectIdaps;

  @iOSXCUITFindBy(accessibility = "no_idaps_selected_subheading")
  private WebElement noIdapsSelectedMessage;

  @iOSXCUITFindBy(accessibility = "no_idaps_selected_heading")
  private WebElement noIdapsSelectedMessageTitle;

  @iOSXCUITFindBy(accessibility = "no_issues_selected_heading")
  private WebElement noCurrentIssuesInYourViewMessage;

  @iOSXCUITFindBy(accessibility = "no_lane_selected_heading")
  private WebElement noLanesSelectedMessageTitle;

  @iOSXCUITFindBy(accessibility = "no_lane_selected_subheading")
  private WebElement noLanesSelectedSubHeading;

  @iOSXCUITFindBy(accessibility = "no_lanes_selected_button")
  private WebElement btnSelectLanes;

  @iOSXCUITFindBy(accessibility = "no_lane_selected_image")
  private WebElement selectLanesSelectedImage;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'commonProgressView_loader'")
  private WebElement progressBarIcon;

  @iOSXCUITFindBy(accessibility = "issue_card_list")
  private List<EventCardComponent> eventCardList;
}