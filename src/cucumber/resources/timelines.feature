Feature: Reading timelines

  Background: Alice and Bob have posted messages
    Given I execute the following commands at the specified times:
      | command                           | timestamp           |
      | Alice -> I love the weather today | 22/04/2015 18:18:05 |
      | Bob -> Damn! We lost!             | 22/04/2015 18:21:01 |
      | Bob -> Good game though.          | 22/04/2015 18:22:15 |
    And now is the 22/04/2015 at 18:23:36


  Scenario: Users can read each other user timelines
    When I execute the command 'Alice'
    Then I see:
      | I love the weather today (5 minutes ago) |
    When I execute the command 'Bob'
    Then I see:
      | Good game though. (1 minute ago) |
      | Damn! We lost! (2 minutes ago)   |


  Scenario: Users subscribe to other timelines and view an aggregated list
    Given I execute the following commands:
      | Charlie -> I'm in New York today! Anyone wants to have a coffee? |
      | Charlie follows Alice                                            |
    And now is the 22/04/2015 at 18:23:38
    When I execute the command 'Charlie wall'
    Then I see:
      | Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago) |
      | Alice - I love the weather today (5 minutes ago)                                |
    When I execute the following commands:
      | Charlie follows Bob |
      | Charlie wall        |
    Then I see:
      | Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago) |
      | Bob - Good game though. (1 minute ago)                                          |
      | Bob - Damn! We lost! (2 minutes ago)                                            |
      | Alice - I love the weather today (5 minutes ago)                                |