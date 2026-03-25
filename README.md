#  Petstore API Automation Testing (RestAssured + Maven)

##  Project Overview

This project demonstrates **API automation testing** using **RestAssured** for the Swagger Petstore APIs.

It includes:

* API request creation
* Response validation using assertions
* Logging using Log4j
* Basic test automation setup using Maven

---

## Tech Stack

* Java
* RestAssured
* Maven
* JUnit
* Log4j (for logging)
* Git & GitHub

---

##  Project Structure

```
petstore-api-automation/
│
├── src/
│   ├── main/java/
│   │
│   ├── test/java/basic/
│   │   └── GetAllPetsTest.java
│   │
│   └── test/resources/
│       └── log4j.properties
│
├── pom.xml
└── README.md
```

---

##  API Endpoint Used

**Base URL:**
https://petstore.swagger.io

**Endpoint:**
/v2/pet/findByStatus?status=available

**Method:**
GET

---

##  Test Implementation

### Get All Pets by Status

This test retrieves all pets with status **available** and validates the response.

###  Assertions Used

* ✔ Assertion 1: Verify status code is **200**
* ✔ Assertion 2: Verify all returned pets have status **"available"**

---

## Sample Test Code

```java
package basic;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAllPetsTest {

    @Test
    public void getAllPets() {

        RestAssured.baseURI = "https://petstore.swagger.io";

        given()
                .when()
                .get("/v2/pet/findByStatus?status=available")
                .then()
                .statusCode(200) // Assertion 1
                .body("status", everyItem(equalTo("available"))) // Assertion 2
                .log().all();
    }
}
```

---

## Logging Configuration

Log4j is configured in:

```
src/test/resources/log4j.properties
```

### Purpose of Log4j

* Helps in logging API request and response details
* Useful for debugging test failures
* Improves visibility during test execution

---

##  Key Learnings

* Writing API automation tests using RestAssured
* Implementing assertions for response validation
* Validating response body using Hamcrest matchers
* Understanding project structure in Maven
* Using logging for better debugging

---

## Future Enhancements

* CRUD operations (POST, PUT, DELETE)
* Response data extraction and chaining
* Dynamic data handling
* BDD framework using Cucumber

---

## Conclusion

This project provides a strong foundation in API automation testing and demonstrates practical implementation of REST API validations using Java.

---

## Author

**Rekha**
