[![Build Status](https://drone.io/github.com/siliconcat/social-console/status.png)](https://drone.io/github.com/siliconcat/social-console/latest)

# Social Console (Technical challenge)
A console-based social networking application (similar to Twitter, but with geeky users seating socially around the same computer) 

This console is purely a technical challenge in JAVA 8.

## Approach

An "outside-in" approach has been followed to create this API, starting by defining the [Acceptance tests using Cucumber](https://github.com/siliconcat/social-console/blob/master/src/cucumber/resources/timelines.feature) to cover the main scenarios.
To make the different steps pass, Unit tests exist to drive the API design.

Different areas are defined on the API:
 
 * **Command line and input commands**: the Command Line Client and several `Command` objects exist to parse and represent the instructions
a user may type in the terminal.
 * **Users and messages**: an expressive API around the central User object that encapsulates the main business logic, such as listing timelines, walls or publishing messages.
 * **Repository**: an in-memory database based on `Multimaps` with methods to read and write data.

## Running the tests

Gradle has been the tool of choice to run. A gradle wrapper exists, so there is no need to install any tool beforehand.

To run all the tests, the following command can be used in UNIX systems: 

    ./gradlew test cucumber

If running on Windows, a `gradle.bat` script exists.