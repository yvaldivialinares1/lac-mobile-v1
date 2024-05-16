package com.automation.lac.qa.utils;

import java.security.SecureRandom;
import lombok.experimental.UtilityClass;
import net.datafaker.Faker;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@UtilityClass
public class Toolbox {

  private static final Faker faker = new Faker();
  private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
  private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String SPECIAL_CHARACTERS = "!@#$*-_+,.";
  private static final String DIGIT_CHARACTERS = "0123456789";

  /**
   * Method to return the UTC date with a format passed by parameter.
   *
   * @param format to add
   * @return UTC date as string
   */
  public static String getUtcDateFormatted(String format) {
    DateTimeFormatter fmt = DateTimeFormat.forPattern(format).withZoneUTC();
    DateTime utcNow = DateTime.now(DateTimeZone.UTC);
    return fmt.print(utcNow);
  }

  /**
   * Generates an email with the format 'test.environment.name.date@domain.com'
   *
   * @return Generated email string.
   */
  public static String generateValidEmail() {
    long timestamp = System.currentTimeMillis();
    return String.format("testing-auto-%s%s", timestamp, "@putsbox.com");
  }

  /**
   * Generates a random password with specified constraints.
   *
   * @param minLength           The minimum length of the generated password.
   * @param maxLength           The maximum length of the generated password.
   * @param includeLowercase    If true, the password will include lowercase characters.
   * @param includeUppercase    If true, the password will include uppercase characters.
   * @param includeSpecialChars If true, the password will include special characters.
   * @param includeDigits       If true, the password will include numeric digits.
   * @return A randomly generated password as a String.
   * @throws IllegalArgumentException if the minimum length is greater than
   *                                  the maximum length or less than 4.
   */
  public static String generatePassword(int minLength, int maxLength, boolean includeLowercase,
                                        boolean includeUppercase, boolean includeSpecialChars,
                                        boolean includeDigits) {
    if (minLength > maxLength || minLength < 4) {
      throw new CustomException("Invalid length parameters");
    }

    StringBuilder password = new StringBuilder();
    SecureRandom random = new SecureRandom();

    if (includeLowercase) {
      password.append(getRandomCharacter(LOWERCASE_CHARACTERS, random));
    }
    if (includeUppercase) {
      password.append(getRandomCharacter(UPPERCASE_CHARACTERS, random));
    }
    if (includeSpecialChars) {
      password.append(getRandomCharacter(SPECIAL_CHARACTERS, random));
    }
    if (includeDigits) {
      password.append(getRandomCharacter(DIGIT_CHARACTERS, random));
    }

    int length;
    length = minLength + random.nextInt(maxLength - minLength + 1);
    StringBuilder characterSet = new StringBuilder();
    if (includeLowercase) {
      characterSet.append(LOWERCASE_CHARACTERS);
    }
    if (includeUppercase) {
      characterSet.append(UPPERCASE_CHARACTERS);
    }
    if (includeSpecialChars) {
      characterSet.append(SPECIAL_CHARACTERS);
    }
    if (includeDigits) {
      characterSet.append(DIGIT_CHARACTERS);
    }

    for (int i = password.length(); i < length; i++) {
      password.append(getRandomCharacter(characterSet.toString(), random));
    }

    return password.toString();
  }

  /**
   * Selects a random character from a given set of characters.
   *
   * @param characterSet The string containing the set of characters to choose from.
   * @param random       The instance of SecureRandom used to ensure a secure random selection.
   * @return A randomly selected character from the characterSet.
   * @throws IllegalArgumentException if the characterSet is empty.
   */
  private static char getRandomCharacter(String characterSet, SecureRandom random) {
    int randomIndex = random.nextInt(characterSet.length());
    return characterSet.charAt(randomIndex);
  }

}
