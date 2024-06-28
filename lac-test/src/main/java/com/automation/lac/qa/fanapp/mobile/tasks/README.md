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

### Example 1 without design patterns:
```java
public class LandingTask extends LandingScreen {

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
}
```

### Example 2 with fluent interface:
The `grantNotificationPermission` method in the `LandingTask` class demonstrates the fluent interface design pattern. This pattern allows for a more readable and fluent way to perform a sequence of actions. Each method in the chain returns the current instance of the class, enabling the chaining of multiple method calls in a single statement.

#### Example: Granting Notification Permission

The `grantNotificationPermission` method performs the necessary actions to grant notification permission by clicking through the notification permission workflow on the landing screen.

```java
public class LandingTask extends LandingScreen {

  /**
   * Performs the actions necessary to grant notification permission.
   *
   * Clicks through the notification permission workflow on the landing screen.
   */
  public LandingTask grantNotificationPermission() {
    DeviceActions.click(getContinueButton());
    DeviceActions.click(getAllowButton());
    DeviceActions.click(getContinueButton());
    DeviceActions.click(getWhileUsingAppOption());
    return this;
  }
}

```
### Example 3 with method chaining:
The `grantNotificationPermission` method in the `LandingTask` class demonstrates the method chaining design pattern. This pattern allows for a more fluent and readable way to perform a sequence of actions. Each method in the chain returns an instance of the class, enabling the chaining of multiple method calls in a single statement.

  #### Example: Granting Notification Permission

  The `grantNotificationPermission` method performs the necessary actions to grant notification permission by clicking through the notification permission workflow on the landing screen.

  ```java
public class LandingTask extends LandingScreen {

  /**
   * Performs the actions necessary to grant notification permission.
   *
   * Clicks through the notification permission workflow on the landing screen.
   */
  public WelcomeHomeTask grantNotificationPermission() {
    DeviceActions.click(getContinueButton());
    DeviceActions.click(getAllowButton());
    DeviceActions.click(getContinueButton());
    DeviceActions.click(getWhileUsingAppOption());
    return new WelcomeHomeTask();
  }
}
```