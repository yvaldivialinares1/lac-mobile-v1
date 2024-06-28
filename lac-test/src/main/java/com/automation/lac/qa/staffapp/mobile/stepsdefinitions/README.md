# Cucumber Step Definitions Directory

This directory contains the step definitions for our Cucumber test suites. Step definitions are the Java methods that
link the plain text Gherkin steps to code implementations. Each method is annotated with a Cucumber expression that
matches a specific step in the feature files.

## Directory Overview

The `step_definitions` directory is structured to mirror the organization of the feature files in the `features`
directory. Each set of step definitions is responsible for handling the steps of one or more feature files, typically
grouped by functionality or domain area.

## Writing Step Definitions

When writing step definitions, please adhere to the following best practices:

- When creating steps in Cucumber, it is essential to design them with the user's behavior in mind rather than specific actions. This approach ensures that the steps are generic and reusable in different scenarios.
- Avoid including complex logic in step definitions; delegate to tasks and questions methods.
- Use expressive and parameterized Cucumber expressions to maximize step re-usability.
- Maintain clear separation between step definitions for different features or domains.

## TestContextManager

`TestContextManager` is a component designed to store and share information within a test. It provides the methods `getTestContext().set()` and `getTestContext().get()` to save and retrieve data, respectively. Utilizing generics, it can store any type of data. The stored information is isolated to the specific test or thread, ensuring thread safety and preventing data leakage between tests.

Example:
```java
public class test {
  // Storing data
  TestContextManager.getTestContext().set("key", value);

  // Retrieving data
  Type value = TestContextManager.getTestContext().get("key");
}
```
## Example Step Definition

Here is an example of a step definition class, `WelcomeHomeSteps`, which uses the `WelcomeHomeScreen` class to interact
with the Welcome Home screen.

### WelcomeHomeSteps.java

```java
/**
 * Step definition class for Welcome Home screen steps.
 */
public class WelcomeHomeSteps {

    private WelcomeHomeScreen welcomeHomeScreen = new WelcomeHomeScreen();
      /**
   * These are the possible variations:
   * Given an adult who has personal information generated
   * Given a young_adult who has personal information generated
   * Given a minor_adult who has personal information generated
   */
  @Given("^(?:A|An) (minor_adult|young_adult|adult) who has personal information generated"
    + "( and is located in (Texas|Illinois))?$")
  public void theUserHasTheInformation(String userType, String location) {
    UserInfo userInfo = createRandomInfo(FanType.fromString(userType));
    getTestContext().set(USER_INFO.name(), userInfo);
  }
/**
  * These are the possible variations:
  * Given an adult who has personal information generated
  * Given a young_adult who has personal information generated
  * Given a minor_adult who has personal information generated
  */
 @Given("^(?:A|An) (minor_adult|young_adult|adult) who has personal information generated"
    + "( and is located in (Texas|Illinois))?$")
 public void theUserHasTheInformation(String userType, String location) {
   UserInfo userInfo = createRandomInfo(FanType.fromString(userType));
   getTestContext().set(USER_INFO.name(), userInfo);
  }

 @When("the user clicks on the Create an account button")
 public void theUserClicksOnTheCreateAnAccountButton() {
   welcomeHomeScreen.getBtnCreateAnAccount().click();
  }    
}