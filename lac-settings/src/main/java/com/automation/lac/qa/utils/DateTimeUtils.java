package com.automation.lac.qa.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateTimeUtils {

  /**
   * convert timestamp value to specific date time format
   *
   * @param timeStamp timestamp value
   * @param format    format for fate time
   * @param zoneId    the time-zone ID
   * @return localDateTime
   */
  public static String convertTimeStampToDate(long timeStamp, String format, String zoneId) {
    Instant instant = Instant.ofEpochSecond(timeStamp);
    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of(zoneId));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return zonedDateTime.format(formatter);
  }

  /**
   * Formats a given LocalDate into a string representation based on the specified format.
   *
   * @param date   The LocalDate to be formatted.
   * @param format The format pattern to be applied to the LocalDate.
   *               The pattern should follow the rules defined in DateTimeFormatter.
   *               For example, "dd MMM yyyy" will format the date as "23 May 1934".
   * @return A string representation of the LocalDate formatted according to the specified pattern.
   */
  public static String formatLocalDate(LocalDate date, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return date.format(formatter);
  }
}
