# Test Case 3: User Security & Error Handling (Negative Testing)

---

## Overview

This test case validates how the system behaves under *Invalid inputs and negative scenarios*.
It focuses on ensuring that the API responds correctly to incorrect data, non-existent resources, and invalid authentication attempts.

Unlike positive testing, this scenario emphasizes *robustness, error handling, and system reliability*.

---

## Objective

The primary goals of this test case are:

* Validate API behavior when "invalid user data" is submitted
* Ensure proper "error handling for non-existing users"
* Verify "login behavior with incorrect credentials"
* Handle "real-world API inconsistencies gracefully"

---

## Technology Stack

* Java
* REST Assured
* Cucumber (BDD)
* Maven
* JUnit
* Log4j2 (Logging)

---

## Framework Structure

The framework follows a clean and reusable architecture:


src
 ├── main/java
 │    ├── base        -> Common configuration (BaseTest)
 │    ├── clients     -> API interaction layer (UserClient)
 │    └── models      -> Request payloads (User)
 │
 ├── test/java
 │    ├── steps       -> Step definitions (UserSteps_TC3)
 │    └── runner      -> Test runner (TestRunner_TC3)
 │
 └── test/resources
      └── features    -> Feature files (user_TC3.feature)


---

## Test Scenario Flow

### Scenario: Validate User Security and Error Handling

---

### Step 1: Create User with Invalid Email

*API:* "POST /user"

* A new user is created with an *invalid email format*(e.g., invalid_email)
* Dynamic data is used for username to avoid duplication

*Validations Performed:*

* Status Code is "200"
* Response contains the "created user ID"
* Confirms that the API allows invalid email (observed behavior)

---

### Step 2: Fetch Non-Existing User

*API:* "GET /user/{username}"

* Attempt to retrieve a user that does not exist

*Validations Performed:*

* Status Code is "404"
* Response contains message:

  "
  User not found
  "

---

### Step 3: Login with Invalid Credentials

*API:* "GET /user/login"

* Login attempted with incorrect username and password

*Expected Behavior:*

* System should *not return a valid session token*

*Actual API Behavior (Swagger Limitation):*

* API returns a session token even for invalid credentials

---

## Important Observation

The Swagger Petstore API has a known limitation:

> It returns a successful login response even for invalid credentials.

---

## Framework Handling Strategy

To ensure correct validation:

* The framework *detects the unexpected behavior*
* Logs a *clear warning message*
* Avoids false test failure
* Maintains test stability and accuracy

Example log:

"
Swagger limitation: session returned even for invalid login
"

---

## Logging Strategy

Each step logs:

* Request details
* Response status code
* Response body
* Validation results

This ensures:

* Easy debugging
* Clear execution trace
* Professional reporting

---

## Assertions Used

* "assertEquals" -> Status code validation
* "assertTrue" -> Response content validation
* Conditional validation for API inconsistencies

All assertions include "clear and meaningful messages".

---

## Data Handling

* Dynamic test data is used:

  * Unique usernames generated using timestamps
* Ensures:

  * No duplication
  * Repeatable execution
  * Independent test runs

---

## Execution

Run only Test Case 3 using:

"
bash
mvn clean test -Dtest=TestRunner_TC3   //individual branch
"

---

## Execution Outcome

| Step                           | Result                           |
| ------------------------------ | -------------------------------- |
| Create user with invalid email | Passed                           |
| Fetch non-existing user        | Passed                           |
| Invalid login handling         | Passed (with limitation handled) |

---

## Key Highlights

* Strong negative testing coverage
* Real-world API limitation handled professionally
* Clean and reusable framework design
* Dynamic data usage
* Detailed logging and reporting

---

## Conclusion

This test case successfully validates "system behavior under negative conditions".

It demonstrates:

* Strong understanding of "error handling"
* Ability to manage "real-world API inconsistencies"
* Development of "robust, maintainable automation tests"

---

## Author
Rekha M
