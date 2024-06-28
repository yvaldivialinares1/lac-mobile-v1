# Enumerations for Java Project

This folder contains all the enumeration classes used throughout the Java project. Enumerations (enums) are a feature in Java that allow you to define collections of constants in a type-safe manner.

## Structure

Each enum is defined in its own `.java` file within this folder. The enums are organized by their functional domain or usage within the project.

## Enum Naming and Ordering Conventions

When defining enums, it is required that all enum constants be listed in alphabetical order. This ensures consistency and makes it easier to locate specific values.

## Example
Here is an example of how to construct an enum in Java. This example defines a `TicketType` enum with two constants: `EVENT` and `GAME`, listed in alphabetical order.

### TicketType.java

```java
package com.example.enums;

/**
 * Enum representing different types of tickets.
 */
public enum TicketType {
    EVENT,
    GAME
    
}