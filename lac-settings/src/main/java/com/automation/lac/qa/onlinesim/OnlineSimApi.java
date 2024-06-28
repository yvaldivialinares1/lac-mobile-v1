package com.automation.lac.qa.onlinesim;

import static com.automation.lac.qa.onlinesim.enums.OnlineSimStaticData.ONLINE_SIM_BASE_URI;
import static com.automation.lac.qa.onlinesim.enums.OnlineSimStaticData.ONLINE_SIM_COUNTRY;
import static com.automation.lac.qa.onlinesim.enums.OnlineSimStaticData.ONLINE_SIM_NUMBER;
import static com.automation.lac.qa.utils.mobile.WaitActions.createFluentWait;
import static org.apache.http.HttpStatus.SC_OK;

import com.automation.lac.qa.onlinesim.models.OnlineSimResponse;
import com.automation.lac.qa.onlinesim.models.OnlineSimResponse.Message;
import com.automation.lac.qa.onlinesim.models.OnlineSimResponse.NumberInfo;
import com.automation.lac.qa.pages.MobileBaseScreen;
import com.automation.lac.qa.rest.Request;
import com.automation.lac.qa.rest.Response;
import com.automation.lac.qa.utils.CustomException;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Pattern;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

@UtilityClass
@Slf4j
public class OnlineSimApi {

  private static final DateTimeFormatter DATE_TIME_FORMATTER =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  /**
   * Fluent wait instance for managing polling and timeouts during asynchronous operations.
   */
  private static final FluentWait<Object> WAIT =
    createFluentWait(MobileBaseScreen.getDriver(), Duration.ofMinutes(1), Duration.ofSeconds(5),
      "Was impossible to get the phone number OTP", NoSuchElementException.class);

  /**
   * Retrieves a list of available numbers by country names from the OnlineSim service.
   * Only return the list numbers when it received messages in the last 2 hours
   *
   * @param countryNames Varargs parameter that represents the names of countries to retrieve
   *                     numbers for.
   * @return A list of {@link NumberInfo} containing available numbers for the specified countries.
   * @throws CustomException if the response status code is not OK or any other unexpected
   *                         exceptions occur.
   */
  @SneakyThrows
  @Step("GET - /api/getFreeList")
  public static List<NumberInfo> getAvailableNumbersByCountry(List<String> countryNames) {
    Response response = WAIT.until(onlineServiceApi -> {
      Response service = new Request()
        .baseUri(ONLINE_SIM_BASE_URI.getText())
        .contentType(ContentType.JSON)
        .get();
      return (service.getResponse().statusCode() != SC_OK) ? null : service;
    });

    OnlineSimResponse onlineSimResponse = response.getResponse().as(OnlineSimResponse.class);

    List<Integer> countryCodes = getCountryCodes(onlineSimResponse.getCountries(), countryNames);
    List<NumberInfo> availableNumbers = new ArrayList<>();

    countryCodes.forEach(countryCode -> {
      Response responseCountry = new Request()
        .baseUri(ONLINE_SIM_BASE_URI.getText())
        .contentType(ContentType.JSON)
        .param(ONLINE_SIM_COUNTRY.getText(), countryCode.toString())
        .get();

      OnlineSimResponse result = responseCountry.getResponse().as(OnlineSimResponse.class);
      List<NumberInfo> numbersByCountry =
        new ArrayList<>(result.getNumbers().values());
      LocalDateTime lastMessage =
        LocalDateTime.parse(result.getMessages().getData().get(0).getCreatedAt(),
          DATE_TIME_FORMATTER);
      LocalDateTime utcNow = LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC+3"));
      Duration duration = Duration.between(lastMessage, utcNow);
      if (duration.toHours() < 2)
        availableNumbers.addAll(numbersByCountry);
    });

    if (!availableNumbers.isEmpty()) {
      return availableNumbers;
    } else {
      throw new CustomException("No available numbers for given countries");
    }
  }

  /**
   * Retrieves a list of OTP (One-Time Password) messages for a specific phone
   * number since a certain date and time.
   *
   * @param countryCode     The country code of the phone number to filter OTP messages.
   * @param phoneNumber     The phone number to filter OTP messages for.
   * @param initialDateTime The date and time from which to start collecting OTP messages.
   * @return A list of {@link Message} objects containing OTPs received since the specified time.
   */
  public static List<Message> getOtpMessages(String countryCode, String phoneNumber,
                                             LocalDateTime initialDateTime) {
    return WAIT.until(request -> {
      OnlineSimResponse response = fetchOnlineSimResponse(countryCode, phoneNumber);

      List<Message> filterMessages = response.getMessages().getData().stream()
        .filter(message -> message.getText().contains("IntuitDome")
          || message.getText().contains("INTUIT DOME")
          && isMessageAfter(message, initialDateTime)).toList();

      if (filterMessages.isEmpty()) log.warn("OTP number(s) is empty");
      else log.info("OTP number(s) {} from number {}", filterMessages, countryCode + phoneNumber);
      return filterMessages.isEmpty() ? null : filterMessages;
    });
  }

  /**
   * Retrieves the last message sent to a number with a specific inNumber ID.
   *
   * @param messages A list of {@link Message} to search within.
   * @param inNumber The inNumber ID to filter messages by.
   * @return The last {@link Message} with the specified inNumber, or null if none are found.
   */
  public static Message getLastMessageByInNumber(
    List<Message> messages, String inNumber) {
    return messages.stream()
      .filter(message -> inNumber.equals(message.getInNumber()))
      .max(Comparator.comparing(Message::getCreatedAt))
      .orElse(null);
  }

  /**
   * Sanitize the phone number to be uses in the flow.
   */
  public static String sanitizeNumber(String countryCode, String fullNumber) {
    return fullNumber.replaceFirst("^\\+" + Pattern.quote(countryCode), "");
  }

  /**
   * Fetches the OnlineSim response based on the country code and number provided.
   *
   * @param countryCode The code of the country for which to fetch the response.
   * @param number      The phone number without the country code and plus sign.
   * @return An {@link OnlineSimResponse} object with the response data.
   * @throws AssertionError if the response status code is not OK.
   */
  @Step("GET - /api/getFreeList?country={countryCode}&number={number}")
  private static OnlineSimResponse fetchOnlineSimResponse(String countryCode, String number) {
    Response response = new Request()
      .baseUri(ONLINE_SIM_BASE_URI.getText())
      .contentType(ContentType.JSON)
      .queryParam(ONLINE_SIM_COUNTRY.getText(), countryCode)
      .queryParam(ONLINE_SIM_NUMBER.getText(), number)
      .get();

    Assert.assertEquals(response.getResponse().statusCode(), SC_OK,
      "Unexpected OnlineSim response StatusCode");

    return response.getResponse().as(OnlineSimResponse.class);
  }

  /**
   * Retrieves a list of country codes based on the provided country names.
   *
   * @param countryNames The names of the countries for which to retrieve the codes.
   * @return A list of country codes corresponding to the provided country names.
   */
  private static List<Integer> getCountryCodes(
    List<OnlineSimResponse.Country> countries, List<String> countryNames) {
    Set<String> countryNameSet =
      new HashSet<>(countryNames.stream().map(country -> country.replace("_", "")).toList());

    return countries.stream()
      .filter(country -> countryNameSet.contains(country.getCountryName()))
      .map(OnlineSimResponse.Country::getCountryCode).toList();
  }

  /**
   * Checks if a message's creation time is after a provided {@link LocalDateTime}.
   *
   * @param message  The {@link Message} to check.
   * @param dateTime The {@link LocalDateTime} to compare the message's creation time against.
   * @return true if the message was created after the provided dateTime, false otherwise.
   */
  private static boolean isMessageAfter(Message message, LocalDateTime dateTime) {
    return LocalDateTime.parse(message.getCreatedAt(), DATE_TIME_FORMATTER).isAfter(dateTime);
  }

  /**
   * Retrieves the most recent date and time from a list of messages.
   *
   * @param messages the list of messages with creation timestamps
   * @return the latest {@code LocalDateTime} found, or {@code LocalDateTime.MIN} if list is empty
   */
  public static LocalDateTime mostRecentDateTime(List<Message> messages) {
    return messages.stream()
      .map(message -> LocalDateTime.parse(message.getCreatedAt(), DATE_TIME_FORMATTER))
      .max(Comparator.naturalOrder())
      .orElse(LocalDateTime.MIN);
  }
}
