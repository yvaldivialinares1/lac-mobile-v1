package com.automation.lac.qa.fanapp.mobile.screens.purchase;

import static com.automation.lac.qa.utils.mobile.DeviceActions.getElement;

import com.automation.lac.qa.pages.MobileBaseScreen;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
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

  @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"ticket of\")]")
  @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@label,\"ticket of\")]")
  private List<WebElement> ticketSummaryBlock;

  protected WebElement getTicket(int index) {
    if (isAndroid())
      return getElement(By.xpath(String
        .format("//*[@resource-id='mainTicketCardContainer%s']", index)));
    else
      return getElement(By.xpath(String.format("//XCUIElementTypeStaticText"
        + "[@name='transaction_success_seat_information_%s']", index)));
  }

  protected WebElement getTicketRowSection(int index) {
    return getElement(By.xpath(String
      .format("//*[@resource-id='tvExpandCardSubTitle%s']", index)));
  }

  protected WebElement getTicketDateTimes(int index) {
    return getTicket(index).findElement(By.id("tvEventDate"));
  }

  protected WebElement getTicketGarageName(int index) {
    return getElement(By.xpath(String
      .format("//*[@resource-id='tvSectionWithGarageName%s']",index)));
  }

}