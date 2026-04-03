@TC3
Feature: User Security and Error Handling

  Scenario Outline: Validate invalid user scenarios

    Given user creates a user with invalid email "<email>"
    When user fetches non existing user "<username>"
    Then system should return 404 with message "<message>"
    When user tries to login with username "<loginUser>" and password "<password>"
    Then login should fail without valid session token

    Examples:
      | email          | username             | message           | loginUser     | password      |
      | invalid_email  | nonExistentUser123   | User not found    | wrongUser123  | wrongPass123  |