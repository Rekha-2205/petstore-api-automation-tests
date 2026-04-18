# Petstore API Automation - Test Case 1 (TC1)

## Project Overview
This project is an API automation framework developed to test the complete lifecycle of a Pet entity using the Petstore API. It is built using Java, Maven, Cucumber (BDD), and RestAssured.

The goal of this test case is to validate all major CRUD operations (Create, Read, Update, Delete) for a pet and ensure the API behaves correctly at each step.

---

## Test Case Objective
To verify the complete lifecycle of a pet:

1. Create a new pet
2. Retrieve the created pet
3. Update the pet status
4. Delete the pet
5. Verify the pet is deleted

---

## Technologies Used
- Java
- Maven
- Cucumber (BDD)
- RestAssured
- JUnit
- Log4j (for logging)

---

## Project Structure

petstore-api-automation
│
├── src
│ ├── main/java
│ │ └── clients
│ │ └── PetClient.java # API request methods (Reusable)
│ │
│ └── test/java
│ ├── steps
│ │ └── PetSteps_TC1.java # Step definitions
│ │
│ └── runner
│ └── TestRunner_TC1.java # Test runner
│
└── src/test/resources
└── features
└── petLifecycle_TC1.feature # Feature file


---

## Test Flow Explanation

### Step 1: Create Pet (POST)
- A new pet is created with:
  - Unique ID (using current timestamp)
  - Unique name (to avoid duplication)
- Response is validated with status code '200'

---

### Step 2: Get Pet (GET)
- The created pet is retrieved using the pet ID
- Response is validated:
  - Status code = '200'
  - Name and status are correct

---

### Step 3: Update Pet (PUT)
- Pet status is updated (e.g., from 'available' -> 'sold')
- Response is validated with status code '200'

---

### Step 4: Validate Updated Pet
- Pet is retrieved again
- Status is verified to be updated correctly

---

### Step 5: Delete Pet (DELETE)
- Pet is deleted using ID
- Response status code is validated as '200'

---

### Step 6: Verify Deletion
- Attempt to retrieve deleted pet
- Expected result:
  - Status code = '404' (Pet not found)

---

## Key Design Decisions

- Client Layer in 'main/java'
  - API methods are reusable and separated from test logic

- Dynamic Test Data
  - Used 'System.currentTimeMillis()' to generate unique IDs and names

- Logging Instead of System.out
  - Log4j is used for better debugging and professional logging

- BDD Approach
  - Cucumber feature files improve readability and understanding

---

## How to Run the Test

Run the following command:
  mvn test -Dtest=TestRunner_TC1    //Individual branch

---

## Expected Result

- All scenarios should pass successfully
- Status:
  - Tests run: 2
  - Failures: 0
  - Errors: 0

---

## Notes

- This framework is designed with simplicity and clarity for learning and demonstration purposes
- Focus is on understanding API flow rather than over-engineering
- Code is written in a way that is easy to explain during interviews
---

## Author
Rekha M
