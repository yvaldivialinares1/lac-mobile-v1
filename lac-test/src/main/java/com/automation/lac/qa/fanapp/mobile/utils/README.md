# Mobile Automation Framework Utilities

This directory contains utility classes that provide supporting functionality to our mobile automation framework. The utilities here are meant to simplify and streamline common tasks, such as device interactions, data generation, and logging.

## Directory Structure

Within this directory, utility classes are organized by their purpose. Each utility class is a collection of static methods that perform general operations not specific to any single test or object within our mobile framework.

## Utility Class Guidelines

- Utility classes should not have state and should only contain static methods and static final variables.
- Class names should be descriptive and reflect the functionality they provide.
- Methods within utility classes should be self-contained and reusable across different parts of the project.
- Avoid creating large "catch-all" utility classes. Instead, group related functionalities into separate, focused classes.