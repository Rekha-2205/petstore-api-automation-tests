@TC1
Feature: Pet Lifecycle
  Scenario Outline: Verify complete pet lifecycle
    Given User create a pet with name "<name>" and status "<status>"

    When User retrieve the pet using created ID
    Then the pet details should have name "<name>" and status "<status>"

    When User update the pet status to "<updatedStatus>"
    Then the pet status should be "<updatedStatus>"

    When User delete the pet
    Then the pet should not exist

    Examples:
      | name   | status    | updatedStatus |
      | ruby   | available | sold          |
      | shadow | pending   | sold          |