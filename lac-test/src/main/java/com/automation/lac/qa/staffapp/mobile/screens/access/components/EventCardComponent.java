package com.automation.lac.qa.staffapp.mobile.screens.access.components;

import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class EventCardComponent extends Widget {

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'issue_card_photo'")
  private WebElement issueCardPhoto;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'issue_card_title'")
  private WebElement issueCardTitle;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'issue_card_time'")
  private WebElement issueCardTime;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'issue_card_idap_id'")
  private WebElement issueCardDeviceId;

  @iOSXCUITFindBy(accessibility = "view_fan_information_button")
  private WebElement btnFanInformation;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'issue_card_age_tag'")
  private WebElement issueCardAgeTag;

  @iOSXCUITFindBy(accessibility = "issue_reason_label")
  private WebElement issueReasonLabel;

  @iOSXCUITFindBy(accessibility = "fan_name_label")
  private WebElement issueCardFanNameLabel;

  @iOSXCUITFindBy(iOSNsPredicate =
    "name == 'licence_plate' AND type == 'XCUIElementTypeStaticText'")
  private List<WebElement> issueCardPlateValues;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'issue_card_primary_button'")
  private WebElement btnPrimary;

  @iOSXCUITFindBy(iOSNsPredicate = "name == 'secondary_button'")
  private WebElement btnSecondary;

  protected EventCardComponent(WebElement element) {
    super(element);
  }
}