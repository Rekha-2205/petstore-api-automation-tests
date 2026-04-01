@TC2
Feature: Inventory Analysis

  Scenario Outline: Verify pet count matches inventory data
    Given user fetches inventory for "<status>"
    When user fetches pets by status "<status>"
    Then user validates count matches for "<status>"

    Examples:
      | status    |
      | available |