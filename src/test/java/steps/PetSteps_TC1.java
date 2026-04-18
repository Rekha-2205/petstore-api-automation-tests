package steps;

import clients.PetClient;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.Matchers.*;

public class PetSteps_TC1 {
    private static final Logger log = LogManager.getLogger(PetSteps_TC1.class);
    private final PetClient petClient = new PetClient();
    private Response response;
    private long petId;
    private String petName;
    private int deleteStatusCode;

    /**
     *
     * @param name   - name of the pet
     * @param status - pet availability status
     */
    @Given("User create a pet with name {string} and status {string}")
    public void createPet(String name, String status) {
        petName = name + "_" + System.currentTimeMillis();
        long id = System.currentTimeMillis();
        log.info("STEP 1: CREATE PET");
        response = petClient.createPet(id, petName, status);
        log.info("REQUEST : Name = {}, Status = {}", petName, status);
        log.info("RESPONSE : {}", response.asString());
        log.info("STATUS CODE : {}", response.getStatusCode());
        response.then().statusCode(200);
        petId = response.jsonPath().getLong("id");
        log.info("Extracted Pet ID : {}", petId);
    }

    /**
     * Retrieves created pet using stored pet ID
     */
    @When("User retrieve the pet using created ID")
    public void getPet() {
        log.info("STEP 2: GET PET");
        response = petClient.getPet(petId);
        log.info("REQUEST : Pet ID = {}", petId);
        log.info("RESPONSE : {}", response.asString());
        log.info("STATUS CODE : {}", response.getStatusCode());
        response.then().statusCode(200);
    }

    /**
     *
     * @param expectedName   - base pet name provided in feature file
     * @param expectedStatus - expected pet status
     */
    @Then("the pet details should have name {string} and status {string}")
    public void validatePet(String expectedName, String expectedStatus) {
        log.info("VALIDATING PET DETAILS");
        response.then()
                .body("name", equalTo(petName))
                .body("status", equalTo(expectedStatus));
        log.info("VALIDATION PASSED : Name = {} | Status = {}", petName, expectedStatus);
    }

    /**
     *
     * @param newStatus - new status value to update the existing pet
     */
    @When("User update the pet status to {string}")
    public void updatePet(String newStatus) {
        log.info("STEP 3: UPDATE PET");
        response = petClient.updatePet(petId, petName, newStatus);
        log.info("REQUEST : Pet ID = {}, New Status = {}", petId, newStatus);
        log.info("RESPONSE : {}", response.asString());
        log.info("STATUS CODE : {}", response.getStatusCode());
        response.then().statusCode(200);
    }

    /**
     *
     * @param expectedStatus - expected status after update operation
     */
    @Then("the pet status should be {string}")
    public void validateUpdatedStatus(String expectedStatus) {
        log.info("VALIDATING UPDATED STATUS");
        response = petClient.getPet(petId);
        log.info("RESPONSE : {}", response.asString());
        response.then()
                .statusCode(200)
                .body("status", equalTo(expectedStatus));
    }

    /**
     * Deletes existing pet using stored pet ID
     */
    @When("User delete the pet")
    public void deletePet() {
        log.info("STEP 4: DELETE PET");
        response = petClient.deletePet(petId);
        deleteStatusCode = response.getStatusCode();
        log.info("DELETE STATUS CODE : {}", deleteStatusCode);
        response.then().statusCode(200);
    }

    /**
     * Verifies delete success and confirms pet is no longer available
     */
    @Then("the pet should not exist")
    public void verifyDeletion() {
        log.info("FINAL VALIDATION - VERIFY DELETION");
        // Step 1: Ensure DELETE was successful
        if (deleteStatusCode != 200) {
            throw new AssertionError("Expected DELETE 200 but got " + deleteStatusCode);
        }
        // Step 2: Try retrieving deleted pet
        response = petClient.getPet(petId);
        log.info("REQUEST : GET Pet ID = {}", petId);
        log.info("RESPONSE : {}", response.asString());
        log.info("STATUS CODE : {}", response.getStatusCode());
        // Step 3: Validate pet is NOT found
        response.then()
                .statusCode(404)
                .body("message", containsString("not found"));
        log.info("FINAL VALIDATION PASSED : Pet deleted and not retrievable");
    }
}