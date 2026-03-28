#  Petstore API Testing – Test Case 1 (CRUD & Chaining)

##  Project Overview

This project demonstrates **API automation testing** for the Swagger Petstore APIs using **Java, RestAssured, Cucumber, and Maven**.  
The focus is on **dynamic, data-driven CRUD operations** for a pet lifecycle and validating API responses with assertions.

**Base URL:**  
https://petstore.swagger.io/v2

**Objective:**  
- Create, retrieve, update, and delete a pet dynamically  
- Validate API responses and status codes at each step  
- Handle multiple test scenarios using **Cucumber Scenario Outline**

---

##  Tools and Technologies

* Java 11  
* Maven  
* RestAssured  
* Cucumber (io.cucumber)  
* JUnit 4  
* Git & GitHub  

---

##  Project Structure
petstore-api-automation/
│
├── src/
│ ├── main/java/
│ │
│ ├── test/java/steps/
│ │ └── PetSteps.java # Step definitions for CRUD operations
│ │
│ ├── test/java/runner/
│ │ └── TestRunner.java # Cucumber test runner
│ │
│ └── test/resources/features/
│ └── petLifecycle.feature # Scenario Outline for dynamic pet testing
│
├── pom.xml
└── README.md

---

##  Test Case Implementation – Pet Lifecycle

### Step 1: Create Pet (POST)

- Dynamically create a pet using name and status from the feature file  
- Extract **pet ID** for subsequent API calls  
- **Expected Status Code:** 200  

### Step 2: Retrieve Pet (GET)

- Get the pet details using the extracted ID  
- Validate **name** and **status** match input  
- **Expected Status Code:** 200  

### Step 3: Update Pet Status (PUT)

- Update the pet’s status dynamically (e.g., “sold”)  
- Validate updated status using GET  
- **Expected Status Code:** 200  

### Step 4: Delete Pet (DELETE)

- Delete the pet using the extracted ID  
- Verify deletion with GET (should return 404)  
- **Expected Status Code:** 200 (DELETE), 404 (GET after deletion)  

---

## Feature File Example

```gherkin
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
  | name    | status        | updatedStatus |
  | ruby    | available     | sold          |
  | shadow  | not available | sold          |

  ##  Step Definitions (PetSteps.java)

POST /pet → Create a pet with a dynamic name and status
GET /pet/{petId} → Retrieve the pet details and validate
PUT /pet → Update the pet status dynamically
DELETE /pet/{petId} → Delete the pet and validate with 404

All dynamic data is generated using System.currentTimeMillis() to ensure unique pet IDs for each run.


##  Runner Class (TestRunner.java)

Runs all feature files in the project
Uses Cucumber JUnit integration
Generates console output for test execution



## How It Works

The feature file drives the test using Scenario Outline and Examples
Step definitions execute API calls via RestAssured
Responses are validated with status codes and body assertions
Dynamic data is generated using unique IDs for each test run
After deletion, 404 status code is verified to confirm the pet is removed
  
##  Execution Steps

Open project in IDE -IntelliJ
Run Maven build to download dependencies:
mvn clean install
Run Cucumber tests via TestRunner.java
Observe console output for step execution and status codes
Each step prints HTTP responses in the console for verification
Any assertion failure will be displayed with error details

##  Key Learnings

Understanding CRUD operations using APIs
Using Cucumber BDD with RestAssured for automation
Validating response body and HTTP status codes
Structuring Maven project for multiple test cases
Using dynamic data for test chaining

## Conclusion

Test Case 1 successfully demonstrates the **complete lifecycle of a pet** in the Swagger Petstore API:

- **Creation** of a pet with dynamic ID and initial status.  
- **Retrieval** and **validation** of pet details using GET requests.  
- **Updating** the pet status and verifying the change.  
- **Deletion** of the pet and validation of removal using the 404 status code.  

This test case confirms that all CRUD operations are working correctly, responses are validated for both **data and HTTP status codes**, and the automation framework supports **dynamic, reusable, and maintainable testing**.  

It provides a solid foundation to add more **API test cases** in a structured and professional manner.