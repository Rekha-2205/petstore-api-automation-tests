# Test Case 2: Inventory Analysis (Cross-Endpoint Validation)

## Objective

Validate that the number of pets marked as **"available"** in the inventory API matches the number of pets returned by the pet listing API.

---

##  APIs Used

### 1. Get Inventory

```
GET /store/inventory
```

* Returns a map of pet statuses and their counts.

### 2. Get Pets by Status

```
GET /pet/findByStatus?status=available
```

* Returns a list of pets filtered by status.

---

##  Test Flow

### Step 1: Fetch Inventory Data

* Call `/store/inventory`
* Parse response as a `Map<String, Integer>`
* Extract count for given status (e.g., "available")

### Step 2: Fetch Pet List

* Call `/pet/findByStatus?status=available`
* Count number of items in response list

### Step 3: Validation

* Compare both counts
* Assert they match exactly

---

##  Key Implementation Details

###  Dynamic Data Handling

* Status is passed from **feature file**
* No hardcoded values used

### Robust Parsing Logic

* Handles inconsistent API keys (e.g., "available", "Available")
* Uses fallback logic with case-insensitive matching

###  Cross-Endpoint Validation

* Ensures consistency between:

  * Inventory API (aggregated data)
  * Pet API (actual records)

### Logging

* Logs request, response, and validation details using Log4j

---

## Project Structure

```
src
 └── test
     ├── java
     │   ├── clients
     │   │   └── PetClient.java
     │   ├── steps
     │   │   └── InventorySteps_TC2.java
     │   └── runner
     │       └── TestRunner_TC2.java
     │
     └── resources
         └── features
             └── inventory_TC2.feature
```

---

##  Execution

Run only Test Case 2:

```
mvn test -Dtest=TestRunner_TC2
```

---

## Sample Output

* Inventory Count: 491
* Pet List Count: 491
* Validation Passed

---

## Conclusion

This test case ensures **data consistency across APIs** by validating that:

* Inventory summary data matches actual pet records.
* Implementation is robust, dynamic, and production-ready.

---

## Author
Rekha
