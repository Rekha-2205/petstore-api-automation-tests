# Test Case 4: Cross-Endpoint Data Consistency

## Overview

This test case validates *data consistency across multiple API endpoints* in the Petstore application.
The goal is to ensure that when a pet is created and updated, the changes are correctly reflected across different system APIs.

This scenario simulates a *real-world workflow*, where data created in one service must remain consistent when accessed through other services.

---

## Objective

To verify that a pet created and updated through the '/pet' API is correctly reflected in:

* "/store/inventory"
* "/pet/findByStatus"

This ensures *cross-endpoint data integrity* within the system.

---

## Test Flow

### Step 1: Create Pet (POST /pet)

* A new pet is created with:

  > Dynamic ID (generated at runtime)
  > Category name (from feature file)
  > Initial status ('available')
* The response is validated for:

  > Status code (200)
  > Correct pet ID

---

### Step 2: Update Pet Status (PUT /pet)

* The same pet is updated:

  > Status changed from 'available' -> "sold"
* The response is validated for:

  > Status code (200)
  > Updated status value

---

### Step 3: Verify Pet by ID (GET /pet/{id})

* Fetch the pet using its ID
* Validate:

  * Status code (200)
  * Correct pet ID
  * Updated status ("sold")

---

### Step 4: Get Store Inventory (GET /store/inventory)

* Retrieve overall inventory data
* Validate:

  * Status code (200)
  * "sold" count is present and greater than 0


---

### Step 5: Get Pets by Status (GET /pet/findByStatus)

* Fetch all pets with status "sold"
* Validate:

  * Status code (200)
  * Response is a list of pets

---

### Step 6: Cross-Endpoint Validation

* Iterate through the list of sold pets
* Verify that:

  * The created pet ID exists
  * The status is "sold"

This confirms that the update made earlier is correctly reflected across endpoints.

---

## Validation Logic

* Status codes are validated for all API calls
* Response data is verified at each step
* Final validation ensures:

  * "Exact match of pet ID"
  * "Correct status after update"
* Uses loop-based parsing to validate data inside a list of JSON objects

---

## Test Data Handling

* All data is *dynamic*
* Pet ID is generated at runtime using:

  * "System.currentTimeMillis()"
* Category and status values are passed through the *feature file*
* No hardcoded test data is used

---

## Key Highlights

* End-to-end API flow validation
* Cross-endpoint data verification
* Dynamic test data handling
* Real-time API response validation
* Clean logging for debugging and traceability

---

## Expected Outcome

* Pet is successfully created and updated
* Updated status is reflected in all relevant endpoints
* Pet appears in the "sold" list
* All validations pass without errors

---

## Execution Result

* 1 Scenario executed
* All steps passed successfully
* No failures observed

---

## Conclusion

This test case successfully validates "data consistency across multiple APIs", ensuring that changes made in one endpoint are accurately reflected in others.

It demonstrates:

* Strong understanding of API workflows
* Ability to validate real-world scenarios
* Effective use of dynamic data and response parsing

---

## Project Structure


petstore-api-automation
│
├── src
│   ├── main
│   │   └── java
│   │       ├── base
│   │       ├── clients
│   │       │   ├── PetClient.java
│   │       │   └── StoreClient.java
│   │
│   └── test
│       ├── java
│       │   ├── steps
│       │   │   └── PetSteps_TC4.java
│       │   └── runner
│       │       └── TestRunner_TC4.java
│       │
│       └── resources
│           └── features
│               └── pet_TC4.feature
│
└── docs
    └── TC4_Cross_Endpoint_Data_Consistency.md


---

## Final Note

This implementation fully satisfies the "Test Case 4 requirement", including:

* Cross-endpoint logic
* Complex response parsing
* Dynamic data handling

It reflects a *real-world API testing approach* and follows professional automation practices.

## Author
Rekha M
