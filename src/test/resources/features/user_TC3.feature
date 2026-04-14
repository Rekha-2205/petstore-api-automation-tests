@TC3
Feature: User Security and Error Handling

  Scenario Outline: Validate user security and error handling for invalid inputs

    Given user creates a user with invalid email "<email>" and password "<userPassword>" and phone "<phone>"
    When user fetches non existing user "<username>"
    Then system should return 404 with message "<message>"
    When user tries to login with username "<loginUser>" and password "<loginPassword>"
    Then login should fail without valid session token

    Examples:
      | email         | userPassword | phone      | username            | message        | loginUser    | loginPassword |
      | invalid_email | pass123      | 9876543210 | nonExistentUser123  | User not found | wrongUser123 | wrongPass123  |