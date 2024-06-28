package com.automation.lac.qa.usercreation.tasks;

import static com.automation.lac.qa.allure.AllureLogger.attachScreenShot;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_COUNT;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_GARAGE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_PARKING_TYPE_STATUS;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_COUNT;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_IS_ACCESSIBLE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_RESPONSE;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TIMESTAMP;
import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_TICKET_TYPE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.TEAM_MATE_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.TICKET_PURCHASE_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.USER_INFO;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.VEHICLES_CREATION_RESULT;
import static com.automation.lac.qa.fanapp.mobile.enums.ScreenTitles.HOME;
import static com.automation.lac.qa.fanapp.mobile.enums.ScreenTitles.MY_PROFILE;
import static com.automation.lac.qa.fanapp.mobile.enums.TicketType.GAME;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.UserCreationConstant.TEAMMATE;
import static com.automation.lac.qa.utils.UserCreationConstant.TICKET_PURCHASE;
import static com.automation.lac.qa.utils.UserCreationConstant.VEHICLE;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.model.Status.FAILED;
import static io.qameta.allure.model.Status.PASSED;
import static java.lang.String.format;
import static java.util.Objects.isNull;

import com.automation.lac.qa.faker.models.UserInfo;
import com.automation.lac.qa.faker.models.userinfo.TeammateInfo;
import com.automation.lac.qa.faker.models.userinfo.VehicleInfo;
import com.automation.lac.qa.fanapp.api.models.tickets.TicketResponse;
import com.automation.lac.qa.fanapp.api.tasks.TicketTask;
import com.automation.lac.qa.fanapp.mobile.enums.TicketType;
import com.automation.lac.qa.fanapp.mobile.questions.CheckOutScreenQuestions;
import com.automation.lac.qa.fanapp.mobile.questions.RegisteredVehiclesQuestion;
import com.automation.lac.qa.fanapp.mobile.questions.TeammateAccountsQuestions;
import com.automation.lac.qa.fanapp.mobile.tasks.commons.CommonTask;
import com.automation.lac.qa.fanapp.mobile.tasks.home.HomeTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyAccountSettingsTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.myprofile.MyLoggedProfileTask;
import com.automation.lac.qa.fanapp.mobile.tasks.myvehicles.EmptyVehiclesTask;
import com.automation.lac.qa.fanapp.mobile.tasks.purchase.CheckoutTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.purchase.OrderDetailsTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.purchase.TransactionSuccessTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.teammateaccounts.AddTeammateAccountTask;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.SeatsAvailabilityTask;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.SeatsSelectionTasks;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.TicketDetailTask;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.TicketFilterTask;
import com.automation.lac.qa.fanapp.mobile.tasks.tickets.TicketListTask;
import com.automation.lac.qa.usercreation.models.TeamMateCreationResult;
import com.automation.lac.qa.usercreation.models.TicketPurchaseResult;
import com.automation.lac.qa.usercreation.models.VehicleCreationResult;
import com.automation.lac.qa.utils.CustomException;
import java.util.List;

public class TeammateVehicleAndTicketTask {

  private final HomeTasks homeTasks = new HomeTasks();
  private final MyLoggedProfileTask myLoggedProfileTasks = new MyLoggedProfileTask();
  private final MyAccountSettingsTasks myAccountSettingsTasks = new MyAccountSettingsTasks();
  private final AddTeammateAccountTask dddTeammateAccountTask = new AddTeammateAccountTask();
  private final EmptyVehiclesTask emptyVehiclesTask = new EmptyVehiclesTask();
  private final CommonTask commonTask = new CommonTask();
  private final TicketFilterTask ticketFilterTask = new TicketFilterTask();
  private final TicketListTask ticketListTask = new TicketListTask();
  private final CheckoutTasks checkoutTasks = new CheckoutTasks();
  private final SeatsAvailabilityTask seatsAvailabilityTask = new SeatsAvailabilityTask();
  private final OrderDetailsTasks orderDetailsTasks = new OrderDetailsTasks();
  private final TicketDetailTask ticketDetailTask = new TicketDetailTask();
  private final SeatsSelectionTasks seatsSelectionTasks = new SeatsSelectionTasks();
  private final TransactionSuccessTasks transactionSuccessTasks = new TransactionSuccessTasks();
  private final TeammateAccountsQuestions teammateQuestions = new TeammateAccountsQuestions();
  private final RegisteredVehiclesQuestion vehiclesQuestion = new RegisteredVehiclesQuestion();
  private final CheckOutScreenQuestions checkOutScreenQuestions = new CheckOutScreenQuestions();

  /**
   * Completes the TeamMate flow
   */
  public void manageTeammate() {
    if (!TEAMMATE.isEmpty()) {
      myLoggedProfileTasks.goToMyAccountSettings();
      myAccountSettingsTasks.clickOnMiniAccounts();

      List<TeammateInfo> teammatesInfo =
        ((UserInfo) getTestContext().get(USER_INFO.name())).getTeammatesInfo();

      try {
        dddTeammateAccountTask.addTeammateAccountInformation(teammatesInfo);
      } catch (Exception e) {
        step(e.getMessage(), FAILED);
        attachScreenShot("teammateCreationError");
      }

      int addedTeammatesNumber = teammateQuestions.getTheAddedTeammatesNumber();

      getTestContext().set(TEAM_MATE_CREATION_RESULT.name(), TeamMateCreationResult.builder()
        .resultMessage(
          format("%s Teammates of %s were added%s", addedTeammatesNumber, TEAMMATE.size(),
            addedTeammatesNumber == 0 ? " or there was an error getting the list." : "."))
        .status(addedTeammatesNumber == TEAMMATE.size() ? PASSED : FAILED)
        .build());

      commonTask.navigateBack(MY_PROFILE.getDescription());
    }
  }

  /**
   * Completes the Vehicle flow
   */
  public void manageVehicle() {
    if (VEHICLE > 0) {
      myLoggedProfileTasks.goToMyVehicles();

      List<VehicleInfo> vehiclesInfo =
        ((UserInfo) getTestContext().get(USER_INFO.name())).getVehiclesInfo();

      try {
        emptyVehiclesTask.clickAddVehicle().addVehicleInformation(vehiclesInfo);
      } catch (Exception e) {
        step(e.getMessage(), FAILED);
        attachScreenShot("vehicleAdditionError");
      }

      int addedVehiclesNumber = vehiclesQuestion.getTheAddedVehiclesNumber();

      getTestContext().set(VEHICLES_CREATION_RESULT.name(), VehicleCreationResult.builder()
        .resultMessage(vehiclesInfo.get(0).getLicensePlateNumber())
        .resultMessage(
          format("%s Vehicles of %s were added%s", addedVehiclesNumber, VEHICLE,
            addedVehiclesNumber == 0 ? " or there was an error getting the list." : "."))
        .status(addedVehiclesNumber == VEHICLE ? PASSED : FAILED)
        .build());
    }
  }

  /**
   * Purchases ticket
   */
  public void manageTicket() {
    if (TICKET_PURCHASE) {
      TicketType ticket = GAME;
      String parkingTicketSoldOut = "any";
      TicketResponse ticketResponse =
        fetchAvailableGameTicketToPurchase(ticket, parkingTicketSoldOut);

      commonTask.navigateBack(HOME.getDescription());
      homeTasks.goToTickets();

      getTestContext().set(SELECTED_TICKET_TIMESTAMP, ticketResponse.getEventTimeStamp());

      ticketFilterTask.searchTicket(ticketResponse.getOpponent());
      ticketListTask.scrollToTicketAndClick();
      ticketDetailTask.clickOnFindSeatsButton();
      seatsSelectionTasks.updateSeatsToPurchase(
        getTestContext().getOrDefault(SELECTED_TICKET_COUNT, 1));
      ticketDetailTask.clickOnFindSeatsButton();
      seatsAvailabilityTask.selectSuggestedSeat(0);
      orderDetailsTasks.getNoOfTicketSection();
      orderDetailsTasks.clickOnGoToPay();
      checkOutScreenQuestions.validateExistingPaymentCard("checkout");
      checkoutTasks.clickOnPlaceOrder();
      transactionSuccessTasks.clickOnContinue();

      getTestContext().set(TICKET_PURCHASE_RESULT.name(), TicketPurchaseResult.builder()
        .status(PASSED)
        .build());
    }
  }

  /**
   * Get an available game through API request
   */
  public TicketResponse fetchAvailableGameTicketToPurchase(TicketType ticket,
                                                           String parkingTicketSoldOut) {
    getTestContext().set(SELECTED_TICKET_TYPE, ticket);
    getTestContext().set(SELECTED_TICKET_COUNT, 1);
    getTestContext().set(SELECTED_TICKET_IS_ACCESSIBLE, "without");
    getTestContext().set(SELECTED_PARKING_TICKET_COUNT, 0);
    getTestContext().set(SELECTED_PARKING_TICKET_GARAGE, parkingTicketSoldOut);
    getTestContext().set(SELECTED_PARKING_TICKET_PARKING_TYPE_STATUS,
      parkingTicketSoldOut != null ? parkingTicketSoldOut.trim() : "");
    TicketResponse ticketResponse = TicketTask.getAvailableTicket(ticket);

    if (isNull(ticketResponse))
      throw new CustomException("No ticket is available to purchase with given parameters");
    getTestContext().set(SELECTED_TICKET_RESPONSE, ticketResponse);

    return ticketResponse;
  }

  /**
   * Go to my profile if necessary
   */
  public void goToMyProfile() {
    if (!TEAMMATE.isEmpty() || VEHICLE > 0)
      homeTasks.goToMyProfileFromHome();
  }
}