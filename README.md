# Petstore API Automation Framework

## Overview

This project is a *BDD-based API Automation Framework* designed to validate the functionality, reliability, and consistency of the Swagger Petstore APIs.

It covers:

* End-to-end API workflows
* Cross-endpoint validations
* Negative testing scenarios
* Real-world API inconsistencies

The framework is built using 
*Java
*RestAssured
*Cucumber
*Maven
with a focus on 
**clean design
**reusability
**clarity

---

## Objectives

* Automate API testing using industry best practices
* Validate both functional and negative scenarios
* Ensure *data consistency across multiple endpoints*
* Build a *scalable and maintainable test framework*

---

## Tech Stack

* Java
* Maven
* RestAssured
* Cucumber (BDD)
* JUnit
* Log4j2

---

## Architecture Diagram (Project Structure)


petstore-api-automation
│
├── src
│   ├── main/java
│   │   ├── base        -> Common setup (BaseTest)
│   │   ├── clients     -> API layer (Pet, Store, User)
│   │   └── models      -> Request payloads
│   │
│   ├── test/java
│   │   ├── steps       -> Step definitions
│   │   └── runner      -> Test runners
│   │
│   └── test/resources
│       └── features    -> BDD feature files
│
├── docs                -> Detailed test case documentation
├── target              -> Execution reports
└── pom.xml


---

# Detailed Test Coverage

This framework covers *4 major real-world API testing scenarios*:

---

## TC1 - Pet Lifecycle Validation (CRUD Operations)

### Purpose

Validate the complete lifecycle of a pet entity using API operations.

### Coverage

* Create Pet (POST /pet)
* Retrieve Pet (GET /pet/{id})
* Update Pet (PUT /pet)
* Delete Pet (DELETE /pet/{id})
* Verify deletion (GET -> 404)

### Validations

* Status codes (200, 404)
* Response body correctness (id, name, status)
* Data persistence after update
* Proper deletion verification

### What it proves

* API supports full CRUD operations
* Data is correctly stored, updated, and deleted
* System behaves correctly for lifecycle scenarios

---

## TC2 - Inventory Analysis (Cross-Endpoint Validation)

### Purpose

Validate *data consistency between two independent APIs*.

### APIs Used

* '/store/inventory' -> Aggregated data
* '/pet/findByStatus' -> Actual records

### Coverage

* Fetch inventory count by status
* Fetch list of pets by same status
* Compare both values

### Validations

* Status code = 200
* Correct parsing of JSON response
* Count comparison:

  * Inventory count vs actual pet list count

### Special Handling

* Handles inconsistent API keys:

  * "available", "Available", "available "
* Handles real-time data mismatch gracefully

### What it proves

* Cross-endpoint data validation
* Ability to handle *real-world API inconsistencies*
* Strong validation logic beyond simple checks

---

## TC3 - User Security & Error Handling (Negative Testing)

### Purpose

Validate API behavior under invalid and unexpected inputs.

### Coverage

* Create user with invalid email
* Fetch non-existing user
* Login with invalid credentials

### Validations

* Status code validation (200, 404)
* Error message validation
* Handling unexpected API responses

### Real API Limitation

* Login API returns success even for invalid credentials

### Framework Handling

* Detects incorrect behavior
* Logs warning instead of failing test
* Maintains test stability

### What it proves

* Strong negative testing capability
* Ability to handle unreliable APIs
* Real-world testing mindset

---

## TC4 - Cross-Endpoint Data Consistency

### Purpose

Ensure that data changes are reflected correctly across multiple APIs.

### Coverage

* Create pet
* Update pet status
* Fetch pet by ID
* Fetch inventory
* Fetch pets by status
* Validate data consistency

### Validations

* Status code validation (200)
* Updated status is reflected everywhere
* Pet ID exists in all relevant responses

### Cross Validation Logic

* Created pet -> updated -> verified in:

  * Inventory API
  * Pet list API

### What it proves

* End-to-end workflow validation
* Data consistency across services
* Real production-like testing scenario

---

## Key Framework Concepts

### Feature Files

* Written in Gherkin language
* Describe test scenarios in readable format
* Example:

""
Scenario: Verify pet lifecycle
Given user creates a pet
When user updates the pet
Then pet should be updated
""

---

### Step Definitions

* Java implementation of feature steps
* Contains:

  * API calls
  * Assertions
  * Logging

---

### Client Layer

* Reusable API methods
* Keeps test logic clean
* Improves maintainability

---

### Execution Reports

Location:

""
target/surefire-reports
""

Contains:

* Test results
* Pass/Fail status
* Error details

---

## How to Run Tests

Run all:

""
mvn clean test
""

Run specific test:

"""
mvn test -Dtest=TestRunner_TC1
mvn test -Dtest=TestRunner_TC2
mvn test -Dtest=TestRunner_TC3
mvn test -Dtest=TestRunner_TC4
"""

---

## Expected Results

* All test cases should pass
* Logs should clearly show:

  * Requests
  * Responses
  * Validations

---

## Design Highlights

* Clean layered architecture
* Reusable client methods
* Dynamic test data (no hardcoding)
* Robust logging using Log4j
* Handles real API limitations

---

## Deliverables

* README.md
* Architecture Diagram
* Feature Files
* Step Definitions
* Execution Reports
* Source Code

NOTE:
    **Execution reports are generated in target/surefire-reports after running tests and are included in the submission package.

---

## Author

Rekha M
