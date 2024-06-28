# Mobile Automation Framework Utilities

This directory contains utility classes that provide supporting functionality to our mobile automation framework. The utilities here are meant to simplify and streamline common tasks, such as device interactions, data generation, and logging.

## Directory Structure

Within this directory, utility classes are organized by their purpose. Each utility class is a collection of static methods that perform general operations not specific to any single test or object within our mobile framework.

## Utility Class Guidelines

- Utility classes should not have state and should only contain static methods and static final variables.
- Class names should be descriptive and reflect the functionality they provide.
- Methods within utility classes should be self-contained and reusable across different parts of the project.
- Avoid creating large "catch-all" utility classes. Instead, group related functionalities into separate, focused classes.
- 
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