# Screen Locators Directory

Welcome to the Screen Locators directory for our mobile automation project. This directory contains classes that define the locators for UI elements on different screens within the mobile application. Each class represents a screen and contains locators for the elements on that screen.

## Directory Structure

The classes within this directory are named after the screens they represent, with the suffix `Screen` to indicate their role within the project, such as `HomeScreen`, `LoginScreen`, etc.

## Class and Locator Guidelines

- Each screen class should extend the `MobileBaseScreen` class or a similar base class that provides common functionality.
- Define locators using appropriate annotations provided by the Appium framework (e.g., `@AndroidFindBy`, `@iOSXCUITFindBy`).
- Group locators logically within the class to reflect the grouping of elements on the screen.
- Use descriptive variable names for locators to clearly indicate the element they refer to.
