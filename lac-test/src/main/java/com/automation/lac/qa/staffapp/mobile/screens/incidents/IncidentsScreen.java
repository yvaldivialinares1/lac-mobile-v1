package com.automation.lac.qa.staffapp.mobile.screens.incidents;

import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.staffapp.mobile.screens.incidents.components.IncidentsBannerComponent;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class IncidentsScreen extends MobileBaseScreen {

  @iOSXCUITFindBy(id = "incident_field_buttonBack")
  private WebElement btnBack;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label = 'DepartmentRequired']")
  private WebElement frmInputDepartment;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label= 'Incident typeRequired']")
  private WebElement frmInputIncidentType;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label = 'LOCATION AND SECTION*']")
  private WebElement frmDropdownLocation;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name = 'STATUS']")
  private WebElement frmDropdownStatus;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label = 'PRIORITY']")
  private WebElement frmDropdownPriority;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label = 'REPORTED VIA']")
  private WebElement frmInputReportedVia;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label = 'Department Reporting']")
  private WebElement frmInputDepartmentReporting;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name, 'CANCEL')]")
  private WebElement btnCancel;

  @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name, 'CREATE')]")
  private WebElement btnCreate;

  private IncidentsBannerComponent incidentsBannerComponent;
}
