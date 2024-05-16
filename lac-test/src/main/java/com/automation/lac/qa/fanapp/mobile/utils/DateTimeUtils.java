package com.automation.lac.qa.fanapp.mobile.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

  /**
   * convert timestamp value to specific date time format
   *
   * @param timeStamp timestamp value
   * @param format    format for fate time
   * @return localDateTime
   */
  public static String convertDateFromTimeStamp(long timeStamp, String format) {
    ZoneId losAngelesTimeZone = ZoneId.of("America/Los_Angeles");

    Instant instant = Instant.ofEpochSecond(timeStamp);
    LocalDateTime localDateTime =
            LocalDateTime.ofInstant(instant, losAngelesTimeZone);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return localDateTime.format(formatter);
  }

}
