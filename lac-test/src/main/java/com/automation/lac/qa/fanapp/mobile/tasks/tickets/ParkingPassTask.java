package com.automation.lac.qa.fanapp.mobile.tasks.tickets;

import static com.automation.lac.qa.fanapp.constants.TicketingConstants.SELECTED_PARKING_TICKET_GARAGE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PARKING_COUNT_GARAGE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PARKING_COUNT_GARAGE_PARKING_TYPE;
import static com.automation.lac.qa.fanapp.mobile.enums.FanAppKeys.PARKING_COUNT_PARKING_TYPE;
import static com.automation.lac.qa.utils.TestContextManager.getTestContext;
import static com.automation.lac.qa.utils.mobile.DeviceActions.click;
import static com.automation.lac.qa.utils.mobile.SwipeActions.swipeUntilFindElement;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.DOWN_TO_UP;
import static com.automation.lac.qa.utils.mobile.SwipeDirections.UP_TO_DOWN;
import static com.automation.lac.qa.utils.mobile.WaitActions.isTheElementAttributeToBeValue;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementToBeClickable;
import static com.automation.lac.qa.utils.mobile.WaitActions.waitForElementVisibility;

import com.automation.lac.qa.fanapp.api.models.tickets.ParkingAvailabilityResponse;
import com.automation.lac.qa.fanapp.mobile.enums.ParkingType;
import com.automation.lac.qa.fanapp.mobile.screens.tickets.ParkingPassScreen;
import com.automation.lac.qa.utils.CustomException;
import com.automation.lac.qa.utils.mobile.SwipeDirections;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebElement;

public class ParkingPassTask extends ParkingPassScreen {

  private final SecureRandom random = new SecureRandom();

  /**
   * It creates a map of map where spotType is a key for outer map & garageId and availableCount in
   * internal Map
   *
   * @param parkingAvailabilityResponse parking availability API response
   * @return Map of map
   */
  public Map<ParkingType, Map<String, Integer>> getParkingAvailabilityAsTypeOfParkAsKeyMap(
    ParkingAvailabilityResponse parkingAvailabilityResponse) {
    Map<ParkingType, Map<String, Integer>> mapOfGarages = new EnumMap<>(ParkingType.class);

    for (ParkingAvailabilityResponse.Garage garage : parkingAvailabilityResponse.getGarages()) {
      String garageTitle = garage.getCmsData().getModalTitle();
      List<ParkingAvailabilityResponse.Garage.AvailableSpot> availableSpots =
        garage.getAvailableSpots();

      for (ParkingType parkingType : ParkingType.values()) {
        Map<String, Integer> availabilityMap = new HashMap<>();
        int availableCount = availableSpots.stream()
          .filter(avail -> parkingType.getValue().equals(avail.getType()))
          .mapToInt(ParkingAvailabilityResponse.Garage.AvailableSpot::getAvailable).sum();

        if (availableCount > 0 && mapOfGarages.containsKey(parkingType))
          mapOfGarages.get(parkingType).put(garageTitle, availableCount);
        else if (availableCount > 0) {
          availabilityMap.put(garageTitle, availableCount);
          mapOfGarages.put(parkingType, availabilityMap);
        }
      }
    }
    return mapOfGarages;
  }

  /**
   * Selects parking tickets based on the availability map and the required parking count.
   *
   * @param availabilityMapWithTypeOfParkingAsKey A map where the key is a {@link ParkingType}
   *                                              and the value is another map
   *                                              with parking details. The inner map's key is a
   *                                              string representing the parking details,
   *                                              and the value is the count of available spots.
   * @param parkingCount                          The number of parking tickets to select.
   */
  public void selectParkingTickets(
    Map<ParkingType, Map<String, Integer>> availabilityMapWithTypeOfParkingAsKey,
    int parkingCount) {
    lookForAvailableParkingGarage(DOWN_TO_UP);
    while (parkingCount > 0) {
      for (Map.Entry<ParkingType, Map<String, Integer>> entry :
        availabilityMapWithTypeOfParkingAsKey.entrySet()) {
        ParkingType parkingType = entry.getKey();
        Map<String, Integer> availability = entry.getValue();
        List<String> garageIds = new ArrayList<>(availability.keySet());
        String randomGarageTitle = garageIds.get(random.nextInt(garageIds.size()));

        int availableCountForGarage = availability.get(randomGarageTitle);

        if (randomGarageTitle != null) {
          clickOnAvailableGarageByGarageId(randomGarageTitle);
          int clickCount = getClickCount(availableCountForGarage);
          parkingCount = parkingCount - clickCount;
          clickCount = parkingCount >= 0 ? clickCount : 1;
          clickOnTypeOfParking(parkingType, clickCount);
          String parkingCountGarageParkingTypeKey =
            randomGarageTitle + "." + parkingType.getValue();

          Map<String, Integer> parkingCountGarageMap =
            getTestContext().getOrDefault(PARKING_COUNT_GARAGE.name(),
              new HashMap<>());
          Map<String, Integer> parkingCountParkingTypeMap =
            getTestContext().getOrDefault(PARKING_COUNT_PARKING_TYPE.name(),
              new HashMap<>());
          Map<String, Integer> parkingCountGarageParkingTypeMap =
            getTestContext().getOrDefault(PARKING_COUNT_GARAGE_PARKING_TYPE.name(),
              new HashMap<>());

          parkingCountGarageMap.put(randomGarageTitle,
            parkingCountGarageMap.getOrDefault(randomGarageTitle, 0) + clickCount);
          parkingCountParkingTypeMap.put(parkingType.getValue(),
            parkingCountParkingTypeMap.getOrDefault(parkingType.getValue(), 0) + clickCount);
          parkingCountGarageParkingTypeMap.put(parkingCountGarageParkingTypeKey,
            parkingCountGarageParkingTypeMap.getOrDefault(parkingCountGarageParkingTypeKey, 0)
              + clickCount);

          getTestContext().set(PARKING_COUNT_GARAGE.name(), parkingCountGarageMap);
          getTestContext().set(PARKING_COUNT_PARKING_TYPE.name(), parkingCountParkingTypeMap);
          getTestContext().set(PARKING_COUNT_GARAGE_PARKING_TYPE.name(),
            parkingCountGarageParkingTypeMap);

          availabilityMapWithTypeOfParkingAsKey.get(parkingType).put(randomGarageTitle,
            availabilityMapWithTypeOfParkingAsKey.get(parkingType).get(randomGarageTitle)
              - clickCount);
          if (parkingCount <= 0)
            break;
        } else {
          throw new CustomException("Not able to find the Garage Id");
        }
      }
    }
  }

  private int getClickCount(int availableCountForGarage) {
    return availableCountForGarage == 1 ? 1 : random.nextInt(2) + 1;
  }

  /**
   * It will swipe in a given direction to look for the available parking if any
   *
   * @param directions The direction in which to swipe, as represented by a SwipeDirections enum
   */
  public void lookForAvailableParkingGarage(SwipeDirections directions) {
    if ((int) getTestContext().get(SELECTED_PARKING_TICKET_GARAGE) > 1)
      swipeUntilFindElement(getFirstGarageOption(), directions,
        getParkingGarageScreenScrollView());
  }

  /**
   * click on available garage
   *
   * @param availableGarage garage to be clicked on
   */
  public void clickOnAvailableGarageByGarageId(String availableGarage) {
    if ((int) getTestContext().get(SELECTED_PARKING_TICKET_GARAGE) > 1) {
      swipeUntilFindElement(getFirstGarageOption(), UP_TO_DOWN);
      String attribute = isAndroid() ? "text" : "label";
      for (WebElement garageElement : getAllAvailableGarages()) {
        if (garageElement.getAttribute(attribute)
          .equals(availableGarage)) {
          waitForElementToBeClickable(garageElement, 10);
          click(garageElement, String.format("%s garage", availableGarage));
          break;
        }
      }
    }
  }

  /**
   * Click on type of parking for number of times provided in clickCount
   *
   * @param parkingType parking type for which we need to increase parking count
   * @param clickCount  no parking count
   */
  public void clickOnTypeOfParking(ParkingType parkingType, int clickCount) {
    WebElement elementBtnIncrease;
    WebElement elementLblParkingCount;
    int previousParkingCount;
    for (int i = 0; i < clickCount; i++) {
      switch (parkingType) {
        case STANDARD -> {
          elementBtnIncrease = getBtnIncreaseStdParking();
          elementLblParkingCount = getLblGarageCountStd();
        }
        case ELECTRIC -> {
          elementBtnIncrease = getBtnIncreaseEleParking();
          elementLblParkingCount = getLblGarageCountEle();
        }
        case ACCESSIBLE -> {
          elementBtnIncrease = getBtnIncreaseAccParking();
          elementLblParkingCount = getLblGarageCountAda();
        }
        default -> throw new CustomException("Please select correct Parking Type");
      }
      swipeUntilFindElement(elementBtnIncrease, DOWN_TO_UP,
        getParkingGarageScreenScrollView());
      String attribute = isAndroid() ? "text" : "value";
      previousParkingCount = Integer.parseInt(elementLblParkingCount.getAttribute(attribute));
      for (int count = 0;
           count < 2 && !isTheElementAttributeToBeValue(elementLblParkingCount, 10, attribute,
             String.valueOf(previousParkingCount + 1)); count++) {
        waitForElementToBeClickable(elementBtnIncrease, 10);
        click(elementBtnIncrease, String.format("%s Parking Type", parkingType.name()));
      }
    }
  }

  /**
   * Wait till the parking details screen gets loaded
   */
  public void waitForParkingDetailsScreenToLoad() {
    waitForElementVisibility(getLblPageHeadline(), 20);
  }
}