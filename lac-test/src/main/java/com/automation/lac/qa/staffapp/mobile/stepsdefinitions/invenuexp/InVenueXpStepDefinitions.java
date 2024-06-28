package com.automation.lac.qa.staffapp.mobile.stepsdefinitions.invenuexp;

import static com.automation.lac.qa.pages.MobileBaseScreen.isIpad;
import static com.automation.lac.qa.staffapp.mobile.enums.FanProfileInformationOption.TICKETS;
import static com.automation.lac.qa.staffapp.mobile.enums.FanProfileTabOption.IN_VENUE_XP;
import static com.automation.lac.qa.staffapp.mobile.enums.GarageLocation.EAST;
import static com.automation.lac.qa.staffapp.mobile.enums.SkipExceptionMessageType.IOS_PLATFORM_NOT_SUPPORTED;
import static com.automation.lac.qa.staffapp.mobile.enums.TypeOfParking.STANDARD;
import static com.automation.lac.qa.staffapp.mobile.enums.TypeOfPurchaseTicket.PARKING_PASS;
import static com.automation.lac.qa.staffapp.mobile.stepsdefinitions.CommonsStep.getFanAccountContextDataByAccountType;

import com.automation.lac.qa.staffapp.api.models.identity.IntuitDomeAccountDto;
import com.automation.lac.qa.staffapp.mobile.questions.common.CheckoutSummaryQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.common.FanSearchQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.fanprofile.FanProfileInVenueXpQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.fanprofile.FanProfileInformationQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.fanprofile.FanTicketsQuestions;
import com.automation.lac.qa.staffapp.mobile.questions.ticketing.TicketingQuestions;
import com.automation.lac.qa.staffapp.mobile.tasks.common.CheckoutSummaryTask;
import com.automation.lac.qa.staffapp.mobile.tasks.common.FanSearchTask;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.FanProfileInVenueXpTask;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.FanProfileInformationTask;
import com.automation.lac.qa.staffapp.mobile.tasks.fanprofile.FanProfileTicketsTask;
import com.automation.lac.qa.staffapp.mobile.tasks.home.HomeTask;
import com.automation.lac.qa.staffapp.mobile.tasks.payment.PaymentConfirmationTask;
import com.automation.lac.qa.staffapp.mobile.tasks.ticketing.TicketingTask;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.testng.SkipException;

@Slf4j
public class InVenueXpStepDefinitions {

  private final HomeTask homeTask = new HomeTask();
  private final FanSearchTask fanSearchTask = new FanSearchTask();
  private final FanSearchQuestions fanSearchQuestions = new FanSearchQuestions();
  private final FanProfileInformationQuestions fanProfileInformationQuestions =
    new FanProfileInformationQuestions();
  private final FanProfileInformationTask fanProfileInformationTask =
    new FanProfileInformationTask();
  private final FanProfileInVenueXpTask fanInVenueXpTask = new FanProfileInVenueXpTask();
  private final FanProfileInVenueXpQuestions fanInVenueXpQuestions =
    new FanProfileInVenueXpQuestions();
  private final FanProfileTicketsTask fanProfileTicketsTask = new FanProfileTicketsTask();
  private final FanTicketsQuestions fanTicketsQuestions = new FanTicketsQuestions();
  private final TicketingTask ticketingTask = new TicketingTask();
  private final TicketingQuestions ticketingQuestions = new TicketingQuestions();
  private final CheckoutSummaryTask checkoutSummaryTask = new CheckoutSummaryTask();
  private final CheckoutSummaryQuestions checkoutSummaryQuestions = new CheckoutSummaryQuestions();
  private final PaymentConfirmationTask paymentTask = new PaymentConfirmationTask();

  @And("is helping a Fan with information about already purchased {string} tickets")
  public void isHelpingAFanWithInformationAboutAlreadyPurchasedTickets(String typeOfTicket) {
    //TODO call API for have recently purchased tickets for the Fan
    log.warn("Call the API for POST ticket for the fan");
  }

  /**
   * Search and select fan profile by email
   *
   * @param fanEmail email to search
   */
  @And("selects fan profile with email {string}")
  public void selectsFanProfileWithEmail(String fanEmail) {
    homeTask.tapOnHomeScreenSearchFanButton();
    fanSearchTask.enterInputInManualSearchField(fanEmail);
    fanSearchQuestions.isFanEmailShownInTheResults(fanEmail);
    fanSearchTask.selectFanAccountByEmail(fanEmail);
    fanProfileInformationQuestions.isFanProfileInformationDisplayed();
  }

  /**
   * Tap on in-venue xp tab, not implemented on iPhone
   */
  @When("the user taps on In-Venue XP tab showing {int} upcoming tickets")
  public void theUserTapsOnInVenueXpTabShowingUpcomingTickets(int upcomingTickets) {
    if (isIpad()) {
      fanProfileInformationTask.getFanProfileTabOptionsComponent().clickOnTabOption(IN_VENUE_XP);
      fanInVenueXpQuestions.validateIfFanTicketsDisplayed();
      fanInVenueXpQuestions.areVisibleTickets(upcomingTickets);
    }
  }

  /**
   * Tap on view all button on iPad
   * Tap on Tickets button on iPhone
   */
  @When("the user taps on the view all button showing all the tickets with filter options")
  public void theUserTapsOnTheViewAllButtonShowingAllTheTicketsWithFilterOptions() {
    if (isIpad()) {
      fanInVenueXpTask.clickOnViewAllButton();
      fanInVenueXpQuestions.isTicketInformationDisplayed();
      fanInVenueXpQuestions.areTimeFiltersDisplayed();
      fanInVenueXpQuestions.areTypeOfEventFilterDisplayed();
    } else {
      fanProfileInformationTask.clickOnFanOptionButton(TICKETS);
      fanTicketsQuestions.isTicketInformationDisplayed();
      fanTicketsQuestions.areTimeFiltersDisplayed();
      fanTicketsQuestions.areTypeOfEventFilterDisplayed();
    }
  }

  /**
   * Tap on time tab filter
   */
  @When("the user taps on {string} tab filter")
  public void theUserTapsOnPastTimeTabFilter(String timeFilter) {
    if (isIpad()) {
      if ("upcoming".equals(timeFilter)) {
        fanInVenueXpTask.clickOnUpcomingTab();
      } else {
        fanInVenueXpTask.clickOnPastTab();
      }
    } else {
      if ("upcoming".equals(timeFilter)) {
        fanProfileTicketsTask.clickOnUpcomingTab();
      } else {
        fanProfileTicketsTask.clickOnPastTab();
      }
    }
  }

  /**
   * Validate ticket on pastime
   */
  @Then("the results only shows tickets in {string} time")
  public void theResultsOnlyShowsTicketsInPastTime(String timeFilter) {
    //TODO validate dates on tickets are upcoming|past to execution day
    if (isIpad()) {
      if ("upcoming".equals(timeFilter)) {
        fanInVenueXpQuestions.areOnlyTicketsInUpcomingTime();
      } else {
        fanInVenueXpQuestions.areOnlyTicketsInPastTime();
      }
    } else {
      if ("upcoming".equals(timeFilter)) {
        fanTicketsQuestions.areOnlyTicketsInUpcomingTime();
      } else {
        fanTicketsQuestions.areOnlyTicketsInPastTime();
      }
    }
  }

  /**
   * Tap on type of ticket filter
   */
  @When("the user taps on {string} ticket type filter")
  public void theUserTapsOnTypeTabFilter(String ticketType) {
    if (isIpad()) {
      fanInVenueXpTask.clickOnTicketTypeFilter(ticketType);
    } else {
      fanProfileTicketsTask.clickOnTicketTypeFilter(ticketType);
    }
  }

  /**
   * Validate ticket filtered by ticket type
   */
  @Then("the results only shows tickets of the {string} type")
  public void theResultsOnlyShowsTicketsOfTheType(String ticketType) {
    //TODO validate container locator for widget it is not working to get the type of ticket
    if (isIpad()) {
      fanInVenueXpQuestions.areOnlyTicketsOfType();
    } else {
      fanTicketsQuestions.areOnlyTicketsOfType();
    }
  }

  @And("is helping a Fan for buy a {string} ticket")
  public void isHelpingAFanForBuyAParkingTicket(String ticketType) {
    //TODO call API for validate available parking tickets
    log.warn("Call the API for GET available parking tickets");
  }

  /**
   *
   */
  @And("initiates a ticket purchase for parking pass option")
  public void initiatesATicketPurchaseForParkingPassOption() {
    if (isIpad()) {
      fanInVenueXpQuestions.validateIfFanTicketsDisplayed();
      fanInVenueXpTask.clickOnBuyATicket();
      ticketingQuestions.validateTicketingDisplayed();
      ticketingTask.clickOnAvailableEvent();
      ticketingQuestions.validatePurchaseModalDisplayed();
      ticketingTask.clickOnTicketOption(PARKING_PASS);
    } else {
      throw new SkipException(IOS_PLATFORM_NOT_SUPPORTED.getMessage());
    }
  }

  /**
   * Choose east garage and standard parking
   */
  @And("chooses east garage location and standard parking")
  public void choosesEastGarageLocationAndStandardParking() {
    checkoutSummaryQuestions.validateCheckoutScreen();
    checkoutSummaryTask.clickOnGarageLocation(EAST);
    checkoutSummaryTask.addTypeOfParking(STANDARD);

  }

  @And("accepts terms and conditions")
  public void acceptsTermsAndConditions() {
    checkoutSummaryTask.acceptTermsAndConditions();
  }

  @And("the user validates the purchase price and payment method")
  public void validateThePaymentMethod() {
    checkoutSummaryQuestions.validatePurchasePrice();
    checkoutSummaryQuestions.validateValidPaymentMethod();
  }

  @When("the user places the order")
  public void theUserPlaceTheOrder() {
    checkoutSummaryTask.placeOrder();
  }

  /**
   * Navigate to In Venue XP and view all tickets
   */
  @When("the user views all tickets and filters by {string} ticket type")
  public void theUserViewsAllTicketsAndFiltersByTicketType(String ticketType) {
    paymentTask.clickOnManageTickets();
    fanProfileInformationTask.getFanProfileTabOptionsComponent().clickOnTabOption(IN_VENUE_XP);
    fanInVenueXpQuestions.validateIfFanTicketsDisplayed();
    fanInVenueXpTask.clickOnViewAllButton();
    fanInVenueXpQuestions.isTicketInformationDisplayed();
    fanInVenueXpQuestions.areTimeFiltersDisplayed();
    fanInVenueXpQuestions.areTypeOfEventFilterDisplayed();
    theUserTapsOnTypeTabFilter(ticketType);
  }

  @Then("the fan can validate the purchased ticket")
  public void theFanCanValidateThePurchasedTicket() {
    fanInVenueXpQuestions.validatePurchasedTicket();
  }

  @And("selects a fan account of {string} searching by email")
  public void selectsAFanAccountOfSearchingByEmail(String fanAccountType) {
    IntuitDomeAccountDto intuitDomeAccount = getFanAccountContextDataByAccountType(fanAccountType);
    selectsFanProfileWithEmail(intuitDomeAccount.getEmail());
  }
}
