# Test Case 2 - Inventory Analysis (Cross-Endpoint Validation)

---

## Objective

The objective of this test case is to validate "data consistency between two independent API" in the Petstore system.

Specifically, this test ensures that:

* The number of pets marked as "available" in the inventory API
* Matches (or logically aligns with) the number of pets returned by the pet listing API

This scenario represents a "real-world testing use case", where data returned by different services must remain consistent across the system.

---

## Business Use Case

In a real application:

* The *Inventory API* provides a summarized count of pets grouped by status
* The *Pet API* provides detailed records of pets

Any mismatch between these two sources could indicate:

* Data synchronization issues
* Backend inconsistencies
* Delayed updates or caching problems

This test validates that both APIs are aligned and reliable.

---

## APIs Used

### 1. GET '/store/inventory'

* Returns a JSON map of pet counts grouped by status

---

### 2. GET '/pet/findByStatus?status=available'

* Returns a list of pets filtered by a specific status

---

## Test Execution Flow

### Step 1: Fetch Inventory Data

* Send request to:

  "
  GET /store/inventory
  "
* Extract the count of pets with status "available"

 *Implementation Details:*

* Attempt direct extraction using key "available"
* If not found, apply fallback logic:

  * Iterate through all keys
  * Match keys using case-insensitive comparison
  * Handle irregular values such as:

    * "availabe"
    * "avaliable"
  

 This ensures robustness against inconsistent API responses

---

### Step 2: Fetch Pets by Status

* Send request to:

  "
  GET /pet/findByStatus?status=available
  "
* Extract the list of pets
* Count total number of items in the response

 This represents the "actual number of available pets in the system"

---

### Step 3: Validation Logic

* Compare:

  "
  Inventory Count vs Pet List Count
  "

 *Validation Rule Applied:*

"
Inventory Count >= Pet List Count
"

---

## Why Not Use Strict Equality?

In real-world systems:

* Inventory APIs may use caching or aggregation
* Pet list APIs return real-time records

Because of this:

* Small differences may occur temporarily
* Strict equality ("==") can lead to false test failures

Using ">=" ensures:

* Stability of tests
* Realistic validation
* Better alignment with production behavior

---

## Technical Implementation

### Framework Design

* "Client Pattern" used for API interaction
* Separation of concerns:

  * 'PetClient'-> Pet-related APIs
  * 'StoreClient' -> Inventory API
  * Step Definitions -> Test logic

---

### BDD Approach (Cucumber)

Feature file uses simple and readable steps:

""
gherkin
Given user fetches inventory for "available"
When user fetches pets by status "available"
Then user validates count matches for "available"
""

Easy to understand for both technical and non-technical stakeholders

---

### Logging

* Implemented using Log4j2
* Logs include:

  * Step execution
  * API responses
  * Extracted values
  * Final validation result

 Helps in debugging and traceability

---

## Project Structure


src
 ├── main
 │   └── java
 │       ├── base
 │       │   └── BaseTest.java
 │       └── clients
 │           ├── PetClient.java
 │           └── StoreClient.java
 │
 ├── test
 │   └── java
 │       ├── runner
 │       │   └── TestRunner_TC2.java
 │       └── steps
 │           └── InventorySteps_TC2.java
 │
 └── resources
     └── features
         └── inventory_TC2.feature


---

## Execution

To run only Test Case 2:

"
mvn clean test -Dtest=TestRunner_TC2
"
 Ensures isolated execution without affecting other test cases

---

## Sample Execution Output

"
Inventory Count: 506
Pet List Count: 506
Validation successful: Inventory is consistent with pet list

"

---

## Key Strengths of This Implementation

* Cross-endpoint validation
* Dynamic data handling (no hardcoded values)
* Robust parsing for inconsistent API responses
* Clean and maintainable code structure
* BDD-based readable test scenarios
* Logging for better debugging and analysis

---

## Challenges Faced & Solutions

### Challenge:

Inventory API returns inconsistent or incorrect keys

### Solution:

* Implemented fallback logic
* Used case-insensitive comparison
* Trimmed extra spaces
* Ensured correct aggregation

---

## Future Enhancements

* Add schema validation for API responses
* Integrate advanced reporting tools (Extent Reports)
* Add retry mechanism for unstable APIs
* Extend validation for multiple statuses

---

## Conclusion

This test case demonstrates a "real-world API testing scenario" where data must be validated across multiple endpoints.

It highlights:

* Strong understanding of API behavior
* Practical handling of inconsistent data
* Clean and scalable test framework design

This approach ensures reliable and maintainable API automation.

## Author
Rekha M