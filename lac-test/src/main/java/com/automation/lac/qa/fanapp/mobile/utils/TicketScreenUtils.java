package com.automation.lac.qa.fanapp.mobile.utils;

import com.automation.lac.qa.fanapp.mobile.enums.TicketType;
import com.automation.lac.qa.utils.CustomException;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    formattedTime = String.format("%d:%02d %s",
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
      } case PARKING -> {
        String[] parts = textData.split(", ");
        if (textData.toLowerCase().contains("today")) {
          return parts[parts.length - 5].trim() + ", " + parts[parts.length - 4].trim();
        } else
          return parts[parts.length - 6] + ", " + parts[parts.length - 5] + ", "
            + parts[parts.length - 4];
      }
      default -> throw new CustomException("Please select correct ticket type (Game|Event)");
    }
  }

  /**
   * Change date format depending on format from the app
   * @param date        - Date to change
   * @param ticketInfo  - Ticket info contains date in format given by the app
   * @return formatted date
   */
  public static String setDateFormatToPlatformFormat(String date, String ticketInfo)  {

    if (ticketInfo.contains("PM") || ticketInfo.contains("AM")) {
      return date;
    } else if (ticketInfo.contains("p.m.") || ticketInfo.contains("a.m.")) {
      return date.replace("PM", "p.m.").replace("AM", "a.m.");
    } else {
      String[] parts = date.split(", ");

      DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("h:mm a");
      DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm");

      LocalTime time = LocalTime.parse(parts[2].replace("at ", ""), inputFormatter);
      String formattedTime = time.format(outputFormatter);
      String convertedDate = String.format("%s, %s, at %s", parts[0], parts[1], formattedTime);
      log.info("Converted date: {}", convertedDate);
      return convertedDate;
    }
  }
}
