package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static io.restassured.RestAssured.*;

public class PetSteps_TC4 {

    private static final Logger log = LogManager.getLogger(PetSteps_TC4.class);

    long petId;
    Map<String, Object> body;
    Response response;
    List<Map<String, Object>> soldPets;

    static {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    /**
     *
     * @param categoryName - category name assigned to the pet
     * @param status       - status of the pet
     */
    @Given("user creates pet with category {string} and status {string}")
    public void createPet(String categoryName, String status) {
        log.info("STEP 1: PET CREATED");
        petId = System.currentTimeMillis();
        body = new HashMap<>();
        body.put("id", petId);
        body.put("name", "Dog_" + petId);
        body.put("status", status);
        Map<String, Object> category = new HashMap<>();
        category.put("id", petId);
        category.put("name", categoryName);
        body.put("category", category);
        response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/pet");
        log.info("CREATE PET REQUEST BODY: {}", body);
        log.info("Status Code (CREATE PET): {}", response.getStatusCode());
        log.info("CREATE PET RESPONSE:\n{}", response.asPrettyString());
        // STATUS CODE VALIDATION
        Assert.assertEquals("Invalid Status Code for CREATE PET", 200, response.getStatusCode());
        Assert.assertEquals(petId, response.jsonPath().getLong("id"));
    }

    /**
     *
     * @param newStatus - new status value to update the pet
     */
    @When("user updates pet status to {string}")
    public void updatePet(String newStatus) {
        log.info("STEP 2: PET UPDATED");
        body.put("status", newStatus);
        response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .put("/pet");
        log.info("UPDATE PET REQUEST BODY: {}", body);
        log.info("Status Code (UPDATE PET): {}", response.getStatusCode());
        log.info("UPDATE PET RESPONSE:\n{}", response.asPrettyString());
        // STATUS CODE VALIDATION
        Assert.assertEquals("Invalid Status Code for UPDATE PET", 200, response.getStatusCode());
        Assert.assertEquals(newStatus, response.jsonPath().getString("status"));
    }

    /**
     * Retrieves pet details using created pet ID and validates updated status
     */
    @Then("user verifies pet by id")
    public void verifyPetById() {
        log.info("STEP 3: PET FETCHED BY ID");
        response = given()
                .get("/pet/" + petId);
        log.info("Status Code (GET PET): {}", response.getStatusCode());
        log.info("GET PET RESPONSE:\n{}", response.asPrettyString());
        // STATUS CODE VALIDATION
        Assert.assertEquals("Invalid Status Code for GET PET BY ID", 200, response.getStatusCode());
        Assert.assertEquals(petId, response.jsonPath().getLong("id"));
        Assert.assertEquals("sold", response.jsonPath().getString("status"));
    }

    /**
     * Fetches current store inventory and validates response status
     */
    @When("user gets store inventory for TC4")
    public void getInventory() {
        log.info("STEP 4: INVENTORY");
        response = given()
                .get("/store/inventory");
        log.info("Status Code (INVENTORY): {}", response.getStatusCode());
        log.info("INVENTORY RESPONSE:\n{}", response.asPrettyString());
        // STATUS CODE VALIDATION
        Assert.assertEquals("Invalid Status Code for INVENTORY", 200, response.getStatusCode());
        Integer soldCount = response.jsonPath().getInt("sold");
        if (soldCount != null) {
            log.info("Sold count in inventory: {}", soldCount);
        } else {
            log.warn("Sold key not found exactly (API inconsistency)");
        }
    }

    /**
     *
     * @param status - pet status used to fetch pets list
     */
    @And("user fetches pets by status {string} for TC4")
    public void getPetsByStatus(String status) {
        log.info("STEP 5: FIND BY STATUS");
        response = given()
                .queryParam("status", status)
                .get("/pet/findByStatus");
        log.info("Status Code (FIND BY STATUS): {}", response.getStatusCode());
        log.info("FIND BY STATUS RESPONSE:\n{}", response.asPrettyString());
        // STATUS CODE VALIDATION
        Assert.assertEquals("Invalid Status Code for FIND BY STATUS", 200, response.getStatusCode());
        soldPets = response.jsonPath().getList("");
    }

    /**
     *
     * @param status - expected status list
     */
    @Then("pet should be present in {string} list")
    public void validatePet(String status) {
        log.info("STEP 6: VALIDATION");
        boolean found = false;
        for (Map<String, Object> pet : soldPets) {
            long id = ((Number) pet.get("id")).longValue();
            String petStatus = (String) pet.get("status");
            if (id == petId && status.equals(petStatus)) {
                found = true;
                break;
            }
        }
        log.info("Validation Result : Pet ID {} with status '{}' found: {}", petId, status, found);
        Assert.assertTrue("Pet NOT found in sold list!", found);
    }
}
