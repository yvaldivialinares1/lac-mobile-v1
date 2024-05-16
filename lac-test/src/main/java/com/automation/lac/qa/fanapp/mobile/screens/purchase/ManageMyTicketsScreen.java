package com.automation.lac.qa.fanapp.mobile.screens.purchase;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;

@Getter
public class ManageMyTicketsScreen extends MobileBaseScreen {

  @AndroidFindBy(uiAutomator = "resourceId(\"tvPaymentConfirmationSkipText\")")
  @iOSXCUITFindBy(xpath = "(//*[contains(@name,'skip')])[1]")
  private WebElement btnSkip;

  @AndroidFindBy(xpath = "//android.view.View"
    + "[@resource-id=\"btnManageTicket\"]/android.widget.TextView")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains"
    + "(@name,\"_ds_button\")]/XCUIElementTypeButton")
  private WebElement manageMyTicketsButton;


  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"tvPaymentConfirmationTitle\"]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText"
    + "[@name=\"transaction_success_header_title_text\"]")
  private WebElement manageYourTicketsTitle;

  @AndroidFindBy(xpath = "//android.widget.TextView"
    + "[@resource-id=\"tvPaymentConfirmationSubTitle\"]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText"
    + "[@name=\"transaction_success_header_sub_title_text\"]")
  private WebElement manageYourTicketsSubTitle;

  @AndroidFindBy(xpath = "//android.widget.TextView[contains(@content-desc,\"Pending\")]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,\"Pending\")]")
  private List<WebElement> ticketStatuses;

  @AndroidFindBy(uiAutomator = "resourceId(\"tvEventDate\")")
  private WebElement ticketDateTimes;

  @AndroidFindBy(xpath = "//*[contains(@resource-id,'tvExpandCardSubTitle')]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,"
    + "\"transaction_success_seat_information\")]")
  private WebElement ticketRowSeatSection;

  @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,"
    + "\"tvExpandCardSubTitle1\")]/android.widget.TextView")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name="
    + "\"transaction_success_garage_information\"]\n")
  private List<WebElement> ticketGarageName;

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"ticket of\")]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@label,\"ticket of\")]")
  private List<WebElement> ticketSummaryBlock;


}