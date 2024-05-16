package com.automation.lac.qa.fanapp.mobile.utils;

import static com.automation.lac.qa.pages.MobileBaseScreen.isAndroid;

import com.automation.lac.qa.fanapp.mobile.enums.TicketType;
import com.automation.lac.qa.utils.CustomException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TicketScreenUtils {

  /**
   * convert Timestamp from Api to ticket screen date format
   *
   * @param timestampFromApi long
   */
  public static String convertTimestampFromApiToTicketScreenDate(long timestampFromApi) {
    // Convert the Unix timestamp to a ZonedDateTime object in the specific timezone
    ZonedDateTime zdt =
      Instant.ofEpochSecond(timestampFromApi).atZone(ZoneId.of("America/Los_Angeles"));
    // Get the current date and time for comparison
    ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
    // Get the suffix for the day (st, nd, rd, th)
    String suffix = getDayOfMonthSuffix(zdt.getDayOfMonth());
    // Format the time part of the date
    String formattedTime = "";
    if (isAndroid()) {
      formattedTime = String.format("%02d:%02d %s",
        zdt.getHour() % 12 == 0 ? 12 : zdt.getHour() % 12,
        zdt.getMinute(),
        zdt.getHour() >= 12 ? "PM" : "AM");
    } else formattedTime = String.format("%d:%02d %s",
      zdt.getHour() % 12 == 0 ? 12 : zdt.getHour() % 12,
      zdt.getMinute(),
      zdt.getHour() >= 12 ? "PM" : "AM");


    // Check if the timestamp date is equal to the current date
    if (zdt.toLocalDate().isEqual(currentDate.toLocalDate())) {
      // If it is today, return the time with "TODAY"
      return String.format("TODAY, %s", formattedTime);
    } else {
      // If it's not today, return the full date and time formatted
      return String.format("%s, %s %d%s, at %s",
        zdt.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US), // Full name of the day
        zdt.getMonth().getDisplayName(TextStyle.FULL, Locale.US),    // Full name of the month
        zdt.getDayOfMonth(),                                        // Day of the month
        suffix,                                                      // Suffix for the day
        formattedTime);                                             // Formatted
    }
  }

  private static String getDayOfMonthSuffix(final int n) {
    if (n >= 11 && n <= 13) {
      return "th";
    }
    switch (n % 10) {
      case 1:
        return "st";
      case 2:
        return "nd";
      case 3:
        return "rd";
      default:
        return "th";
    }
  }

  /**
   * return date time value of ticket text received from ticket list screen ticket
   *
   * @param ticketType Type of ticket(Game|Event)
   * @param textData   String
   * @return String
   */
  public static String extractTicketDateTime(TicketType ticketType, String textData) {
    switch (ticketType) {
      case EVENT -> {
        String[] parts = textData.split(", ");
        if (textData.toLowerCase().contains("today")) {
          return parts[parts.length - 5].trim() + ", " + parts[parts.length - 4].trim();
        } else
          return parts[parts.length - 6] + ", " + parts[parts.length - 5] + ", "
            + parts[parts.length - 4];
      }
      case GAME -> {
        String[] parts = textData.split(", ");
        if (textData.toLowerCase().contains("today")) {
          return parts[parts.length - 4].trim() + ", " + parts[parts.length - 3].trim();
        } else
          return parts[parts.length - 5] + ", " + parts[parts.length - 4] + ", "
            + parts[parts.length - 3];
      }
      default -> throw new CustomException("Please select correct ticket type (Game|Event)");
    }
  }
}
