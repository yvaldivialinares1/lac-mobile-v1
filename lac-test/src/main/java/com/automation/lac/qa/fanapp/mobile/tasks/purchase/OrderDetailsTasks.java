package com.automation.lac.qa.fanapp.mobile.tasks.purchase;

import static com.automation.lac.qa.fanapp.mobile.enums.ButtonsDescription.GO_TO_PAY;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.ROW_SECTION_DETAILS;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.SwipeActions.scrollIntoListToCollectAttributeValue;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.UP_TO_DOWN;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.fanapp.mobile.screens.purchase.OrderDetailsScreen;
import com.automation.lac.qa.utils.CustomException;
import com.automation.lac.qa.utils.mobile.SwipeDirections;
import com.automation.lac.qa.utils.mobile.WaitActions;
import java.util.List;

public class OrderDetailsTasks extends OrderDetailsScreen {

  private static final String DEFECT_MESSAGE = """
    User is not redirected to Order detail screen after creating account while
    purchasing the ticket. \nAndroid Bug: CA-52498 --- iOS Bug: CA-52499""";

  /**
   * Click on Go To Pay
   */
  public void clickOnGoToPay() {
    //TODO Remove conditional when bugs are fixed
    if (WaitActions.isTheElementVisible(getBtnGoToPay(), 10)) {
      click(getBtnGoToPay(), GO_TO_PAY.getValue());
    } else {
      throw new CustomException(DEFECT_MESSAGE);
    }
  }

  /**
   * get No of ticket section from order detail screen
   */
  public void getNoOfTicketSection() {
    swipeUntilFindElement(getTicketDateTime(), UP_TO_DOWN);
    String attribute = isAndroid() ? "text" : "label";
    List<String> sectionData = scrollIntoListToCollectAttributeValue(getRowSectionDetails(),
      getParkingPassesLabel(), attribute, SwipeDirections.DOWN_TO_UP,
      1, 0, 0, 0.25,0.25,false);
    getTestContext().set(ROW_SECTION_DETAILS.name(), sectionData);
  }

  public void waitForScreenToLoad() {
    waitForElementVisibility(getTicketDateTime(), 60);
  }
}