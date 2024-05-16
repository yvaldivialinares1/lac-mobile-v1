# Page Element Tasks for Java Project

This directory is designated for housing the task classes that encapsulate actions performed on the elements of screens in our Java automation project. These tasks represent the reusable business logic that can be applied across various tests and scenarios.

## Directory Structure

The task classes are organized within this directory based on the page or feature they are associated with. Each task is represented by a Java class file which encapsulates a specific action or sequence of actions.

## Task Implementation

When implementing a new task, please adhere to the following principles:

1. Each task class should encapsulate a single responsibility or user action, following the Single Responsibility Principle (SRP).
2. Class names should be descriptive and reflect the action they perform, using verb phrases where possible (e.g., `FillOutContactForm`, `AddItemToBasket`).
3. Document the purpose and usage of the task within class and method comments.
4. If applicable, use method overloading to provide multiple ways to perform a task with different sets of parameters.

## Example Task

Here is an example of how a simple task class might be structured:

```java
public class LandingScreenTasks extends LandingScreen {

 /**
 * Performs the actions necessary to grant notification permission.
 *
 * Clicks through the notification permission workflow on the landing screen.
 */
  public void grantNotificationPermission() {
    DeviceActions.click(getContinueButton());
    DeviceActions.click(getAllowButton());
    DeviceActions.click(getContinueButton());
    DeviceActions.click(getWhileUsingAppOption());
  }

  public void clickOnLoginButton() {
    DeviceActions.click(getLoginOption());
  }
}