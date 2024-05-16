# Screen Questions Directory

This directory contains `Question` classes that are used to validate the state of UI elements on various screens within our mobile application. Each `Question` class is named by concatenating the name of the screen with `Questions` and extends the corresponding screen class to access its elements and perform assertion tasks.

## Overview

The `Question` classes serve a critical role in our test automation framework by encapsulating the assertions that verify whether the application's state matches our expectations. These classes enable a clean separation of concerns, allowing test cases to remain readable and focused on the business logic.

## Structure and Naming Convention

- Each `Question` class extends a specific screen class and inherits its element locators.
- The name of a `Question` class should follow the pattern `[ScreenName]Questions`. For example, assertions related to the login screen are located in `LoginScreenQuestions`.
- Within each class, methods should be named to clearly describe the checks they perform and should leverage soft assertions for non-blocking verifications.

## Creating `Question` Classes

When creating a new `Question` class, follow these steps:

1. Name the class by appending `Questions` to the name of the corresponding screen class.
2. Extend the screen class to gain access to the defined UI element locators.
3. Implement methods that encapsulate the assertions related to the elements of that screen.
4. Use soft and hard assertions to collect multiple validation results and provide comprehensive feedback.
