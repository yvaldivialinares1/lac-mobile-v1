# Screen Questions Directory

This directory contains `Question` classes that are used to validate the state of UI elements on various screens within our mobile application. Each `Question` class is named by concatenating the name of the screen with `Questions` and extends the corresponding screen class to access its elements and perform assertion tasks.

## Overview

The `Question` classes serve a critical role in our test automation framework by encapsulating the assertions that verify whether the application's state matches our expectations. These classes enable a clean separation of concerns, allowing test cases to remain readable and focused on the business logic.

## Structure and Naming Convention

- Each `Question` class extends a specific screen class and inherits its element locators.
- The name of a `Question` class should follow the pattern `[ScreenName]Questions`. For example, assertions related to the login screen are located in `LoginScreenQuestions`.
- Within each class, methods should be named to clearly describe the checks they perform and should leverage soft assertions for non-blocking verifications.

## SoftAssertManager

`SoftAssertManager` is an implementation that allows performing soft asserts across different steps of a test, logging the status in the Allure report. In case of a failure, a screenshot is added to the report. This component is designed to store assertions per test or thread, ensuring proper handling in concurrent environments.

`SoftAssertManager` is configured in the Cucumber hook to invoke `assertAll` at the end of each test. This ensures that a consolidated test status is generated, indicating if any assertion failed during its execution.

### Features
- **Soft Asserts Across Steps:** Allows performing multiple assertions without interrupting the test execution.
- **Allure Reporting:** Logs the status of each assertion in the Allure report.
- **Screenshots on Failure:** Adds screenshots to the report in case of assertion failures.
- **Per Test or Thread Storage:** Designed to store assertions in isolation per test or thread.
- **Cucumber Integration:** Configured in the Cucumber hook to invoke `assertAll` at the end of the test, providing a consolidated status of the test.

## Creating `Question` Classes

When creating a new `Question` class, follow these steps:

1. Name the class by appending `Questions` to the name of the corresponding screen class.
2. Extend the screen class to gain access to the defined UI element locators.
3. Implement methods that encapsulate the assertions related to the elements of that screen.
4. Use soft and hard assertions to collect multiple validation results and provide comprehensive feedback.

## Example Question

Here is an example of a question class, `WelcomeHomeQuestion`, which extends `WelcomeHomeScreen` and checks if the Welcome Home screen is displayed.

### WelcomeHomeQuestion.java

```java
package com.example.questions;

import com.example.screens.WelcomeHomeScreen;
import static com.automation.lac.qa.assertions.SoftAssertManager;

/**
 * Question class to verify the Welcome Home screen.
 */
public class WelcomeHomeQuestion extends WelcomeHomeScreen {

    /**
     * Checks if the Welcome Home screen is displayed using
       SoftAssert without interrupting the test.
     */
    public void checkWelcomeHomeScreenIsDisplayedSoftAssert() {
      SoftAssertManager.getSoftAssert().assertTrue(getImgIntuitDomeLogo().isDisplayed(),
          "The Welcome Home Screen is displayed");
    }
    
    /**
     * Checks if the Welcome Home screen is displayed using
      SoftAssert with immediate assertAll call.
     */
    public void checkWelcomeHomeScreenIsDisplayedSoftAssertWithInterrupt() {
      SoftAssertManager.getSoftAssert().assertTrue(getImgIntuitDomeLogo().isDisplayed(),
          "The Welcome Home Screen is displayed");
      SoftAssertManager.assertAll(); // This will interrupt the test if the assertion fails
    }
    
    /**
     * Checks if the Welcome Home screen is displayed using HardAssert.
     */
    public void checkWelcomeHomeScreenIsDisplayedHardAssert() {
        Assert.assertTrue(getImgIntuitDomeLogo().isDisplayed(),
          "The Welcome Home Screen is displayed");
    }
}
```
### Hard Asserts

To perform hard asserts, use the `Assert` class from TestNG. Each assertion should include a clear and descriptive message to indicate the purpose of the assertion and the expected outcome. This helps in understanding the context of the failure when an assertion fails.

Example:
```java
import org.testng.Assert;

public class ExampleTest {

    public void verifyElementIsDisplayed() {
        Assert.assertTrue(element.isDisplayed(), "The element should be displayed on the screen.");
    }
}