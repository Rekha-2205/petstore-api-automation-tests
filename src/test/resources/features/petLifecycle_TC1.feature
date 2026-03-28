Feature: Pet Lifecycle

  Scenario Outline: Verify complete pet lifecycle
    Given I create a pet with name "<name>" and status "<status>"
    When I get the pet details
    Then the pet name should be "<name>" and status should be "<status>"

    When I update the pet status to "<updatedStatus>"
    Then the pet status should be "<updatedStatus>"

    When I delete the pet
    Then the pet should not exist

  Examples:
    | name     | status    | updatedStatus |
    | ruby     | available | sold          |
    | shadow   | not available|sold|