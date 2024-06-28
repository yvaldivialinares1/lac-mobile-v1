# API Utilities Directory

Welcome to the API Utilities directory of our Java project. This folder is dedicated to providing utility classes that support and facilitate the interaction with various APIs. These utility classes are essential for handling common tasks such as HTTP request execution, response parsing, and error handling.

## Directory Overview

The utility classes in this directory are organized to encapsulate different aspects of API interactions. Each class contains static methods or shared resources that can be utilized by API tasks or test classes to streamline the process of working with APIs.

## Utility Class Design

- Utility classes should be stateless, containing only static methods and static final variables.
- Class names must clearly reflect the purpose and functionality they encapsulate.
- Methods should be designed to be reusable and modular, promoting code reuse across the project.
- Group related functionalities into specific classes rather than creating monolithic utility classes.

## Example

This is an example of a utility class, characterized by its static methods. To prevent it from being instantiated, the `@UtilityClass` annotation from Lombok is used.


```java
@UtilityClass
public class ValidationUtils {
  /**
   * Validates if the given content description matches the specified pattern and ensures
   * that the captured group is not empty.
   *
   * @param contentDesc The content description to validate.
   * @param pattern     The regular expression pattern to match against.
   * @return true if the content description matches the pattern and the captured group
   *   is not empty, otherwise, false.
   */
  public static boolean validateContentDesc(String contentDesc, String pattern) {
    Pattern regex = Pattern.compile(pattern);
    Matcher matcher = regex.matcher(contentDesc);
    return matcher.matches() && !matcher.group(1).isEmpty();
  }
}
```