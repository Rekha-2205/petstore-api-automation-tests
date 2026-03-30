@TC2
Feature: Inventory Analysis - TC2

  Scenario: Verify available pet count from inventory matches pet list

    Given user gets inventory data for TC2
    When user gets pets with status available for TC2
    Then verify available pet count matches for TC2