[![Build Status](https://drone.io/github.com/siliconcat/social-console/status.png)](https://drone.io/github.com/siliconcat/social-console/latest)

# Social Console (Technical challenge)
A console-based social networking application (similar to Twitter, but with geeky users seating socially around the same computer) 

This console is purely a technical challenge in JAVA 8.

## Starting the Social console

The application is just a terminal that accepts multiple commands:

 * *posting*: `<user name> -> <message>`
 * *reading*: `<user name>`
 * *following*: `<user name> follows <another user>`
 * *wall*: `<user name> wall`

There are different options to start the terminal, explained below.

#### Using `gradle run`

For the less patient social users, it is possible to simply start Gradle and run this command:

    ./gradlew --console plain run

And when you see the '`>`' prompt, you can start executing commands right away.

#### Building an executable from the sources

For the slightly less impatience, it is possible to install the application and create an executable file to start it. Unless someone 
is really exciting and start contributing code, the install command is only needed once:

    ./gradlew installApp
   
This creates a standalone application that can simply be started:

    ./build/install/social-console/bin/social-console
    
#### Download the latest release

The latest release can be downloaded in binary form from the [Releases section](https://github.com/siliconcat/social-console/releases/tag/1.0). 

Once uncompress, simply run the executable:

    ./bin/social-console
   
Or the one with the `.bat` extension if running on Windows.

## Insight into the approach to the challenge

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