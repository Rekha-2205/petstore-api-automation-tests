#  Test Case 2: Inventory Analysis (Complex Data Parsing)

## Overview

This test case validates the consistency and accuracy of data across multiple API endpoints in the Petstore application. It focuses on **cross-endpoint validation** and **dynamic data handling**, which are critical aspects of real-world API automation.

The objective is to ensure that the number of pets marked as **"available"** is consistent across different API responses.

---

##  Objective

* Validate inventory data returned by '/store/inventory'
* Compare it with the list of pets returned by 'pet/findByStatus'
* Ensure both endpoints reflect consistent real-time data

---

##  APIs Covered

### 1. GET /store/inventory

* Returns a map of pet statuses and their counts

---

### 2. GET /pet/findByStatus?status=available

* Returns a list of pets with status "available"
* Used to validate the count obtained from inventory

---

##  Test Workflow

### Step 1: Fetch Inventory Data

* Send GET request to '/store/inventory'
* Extract count of "available" pets

### Step 2: Fetch Available Pets List

* Send GET request to '/pet/findByStatus?status=available'
* Count number of items in response list

### Step 3: Validation

* Compare both counts
* Allow slight variation (+/-1) due to real-time API updates

---

## Validation Logic

The test ensures:

* Inventory count = List count
* Difference <=1 (to handle live data changes)

---

## Key Concepts Implemented

*  Cross-endpoint validation
*  Dynamic data handling (no hardcoding)
*  JSON response parsing
*  API chaining
*  Real-time validation strategy

---

##  Project Files

| File                      | Description                 |
| ------------------------- | --------------------------- |
|'inventory_TC2.feature'   | Cucumber feature file       |
| 'InventorySteps_TC2.java' | Step definitions with logic |
| 'TestRunner_TC2.java'     | Test runner                 |
| 'PetClient.java'          | API interaction methods     |

---

##  Execution

Run only Test Case 2 using:

'''bash
mvn clean test "-Dtest=runner.TestRunner_TC2"
'''

---

## Expected Output

* Inventory count printed in logs
* List count printed in logs
* Validation result displayed
* Test execution should PASS

---

## Notes

* All data is fetched dynamically from API responses
* No static or hardcoded values are used
* Minor mismatches are expected due to concurrent API updates
* Designed to reflect real-world automation scenarios

---

## Conclusion

This test case successfully demonstrates:

* Advanced API validation techniques
* Handling of dynamic and real-time data
* Implementation of scalable and maintainable automation logic

## Author
Rekha
