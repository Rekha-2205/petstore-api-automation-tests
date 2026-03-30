#  Petstore API Automation – Test Case 1 (CRUD & API Chaining)

---

##  Overview

This project implements Test Case 1 – Pet Lifecycle using a structured API automation framework built with:

- Java
- REST Assured
- Cucumber (BDD)
- Maven
- Log4j2

The framework validates the complete CRUD lifecycle of a Pet and demonstrates API chaining using dynamic test data.

---

##  Objective

To automate and validate:

1. Create a pet
2. Retrieve the pet using ID
3. Update pet status
4. Delete the pet
5. Verify deletion

---

##  Test Flow

Step 1: Create Pet (POST /pet)
- Pet ID is generated dynamically
- Pet name is dynamically generated using timestamp
- Status is passed from feature file
- Validation: Status code = 200

Step 2: Extract ID
- Pet ID is extracted from response
- Used in all subsequent steps

Step 3: Get Pet (GET /pet/{petId})
- Pet is retrieved using extracted ID
- Validations:
  - Status code = 200
  - Name matches expected value
  - Status matches expected value

Step 4: Update Pet (PUT /pet)
- Pet status is updated dynamically
- Validation: Status code = 200

Step 5: Validate Updated Pet
- GET request performed after update
- Validations:
  - Status is updated
  - Status code = 200

Step 6: Delete Pet (DELETE /pet/{petId})
- Pet is deleted using ID
- Validation: Status code = 200

Step 7: Verify Deletion
- GET request performed after deletion using same ID
- Validations:
  - Status code = 404
  - Response: Pet not found

---

## API Chaining

The petId generated during creation is reused in:
- GET
- PUT
- DELETE
- Final GET

This ensures real-world API flow validation.

---

## Dynamic Data Handling

- Pet ID is generated using system time
- Pet name is dynamically created using input + timestamp
- Status values are passed from feature file

Benefits:
- No hardcoding
- Avoids duplicate data
- Improves reusability

---

##  Project Structure

petstore-api-automation
|
├── src
│   ├── main/java
│   │   ├── base
│   │   │   └── BaseTest.java
│   │   └── clients
│   │       └── PetClient.java
│   │
│   └── test
│       ├── java
│       │   ├── runner
│       │   │   └── TestRunner_TC1.java
│       │   └── steps
│       │       └── PetSteps_TC1.java
│       │
│       └── resources
│           ├── features
│           │   └── petLifecycle_TC1.feature
│           └── log4j2.xml
│
├── pom.xml
└── README.md

---

##  Feature File

Scenario Outline: Verify complete pet lifecycle
  Given User create a pet with name "<name>" and status "<status>"

  When User retrieve the pet using created ID
  Then the pet details should have name "<name>" and status "<status>"

  When User update the pet status to "<updatedStatus>"
  Then the pet status should be "<updatedStatus>"

  When User delete the pet
  Then the pet should not exist

---

##  Logging

Logs include:
- Request details
- Response body
- Status codes
- Validation messages

Example:
- CREATE STATUS CODE -> 200
- GET STATUS CODE -> 200
- DELETE STATUS CODE -> 200
- GET AFTER DELETE STATUS CODE -> 404

---

## Execution

Run:
mvn clean test

---


##  Results

- CRUD operations validated
- API chaining implemented
- Dynamic data handling achieved
- Status codes verified
- Deletion confirmed with 404

---

## Conclusion

This framework demonstrates:
- End-to-end API lifecycle validation
- BDD implementation using Cucumber
- Proper use of REST Assured
- Dynamic data handling
- API chaining

---

##  Author

Rekha