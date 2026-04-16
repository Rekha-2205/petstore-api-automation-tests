@TC3
Feature: User Security and Error Handling

  Scenario: Validate user security and error handling for invalid inputs

    Given user creates a user with invalid email "invalid_email" and password "pass123" and phone "9876543210"
    When user fetches non existing user "nonExistentUser123"
    Then system should return 404 with message "User not found"
    When user tries to login with username "wrongUser123" and password "wrongPass123"
    Then login should fail without valid session token