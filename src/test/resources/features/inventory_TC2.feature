@TC2
Feature: Inventory Analysis

  Scenario: Verify pet count matches inventory data
    Given user fetches inventory for "available"
    When user fetches pets by status "available"
    Then user validates count matches for "available"