@TC4
Feature: Cross Endpoint Data Consistency

  Scenario: Validate pet consistency across endpoints
    Given user creates pet with category "HighValue-Bulldog" and status "available"
    When user updates pet status to "sold"
    Then user verifies pet by id
    When user gets store inventory for TC4
    And user fetches pets by status "sold" for TC4
    Then pet should be present in "sold" list
