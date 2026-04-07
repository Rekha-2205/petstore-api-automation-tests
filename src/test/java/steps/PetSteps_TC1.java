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

    // STEP 1: CREATE PET

    @Given("User create a pet with name {string} and status {string}")
    public void createPet(String name, String status) {

        petName = name + "_" + System.currentTimeMillis();
        long id = System.currentTimeMillis();

        log.info(" STEP 1: CREATE PET ");

        response = petClient.createPet(id, petName, status);

        log.info("REQUEST -> Name: " + petName + ", Status: " + status);
        log.info("RESPONSE -> " + response.asString());
        log.info("STATUS CODE -> " + response.getStatusCode());

        response.then().statusCode(200);

        petId = response.jsonPath().getLong("id");

        log.info("Extracted Pet ID -> " + petId);
    }

    // STEP 3: GET PET

    @When("User retrieve the pet using created ID")
    public void getPet() {

        log.info(" STEP 3: GET PET ");

        response = petClient.getPet(petId);

        log.info("REQUEST -> Retrieving Pet using ID: " + petId);
        log.info("RESPONSE -> " + response.asString());
        log.info("STATUS CODE -> " + response.getStatusCode());

        response.then().statusCode(200);
    }

    // VALIDATE NAME & STATUS

    @Then("the pet details should have name {string} and status {string}")
    public void validatePet(String expectedName, String expectedStatus) {

        log.info(" VALIDATING PET DETAILS ");

        response.then()
                .body("name", containsString(expectedName))
                .body("status", equalTo(expectedStatus));

        log.info("VALIDATION PASSED -> Name contains: " + expectedName +
                " | Status: " + expectedStatus);
    }


    // STEP 4: UPDATE PET

    @When("User update the pet status to {string}")
    public void updatePet(String newStatus) {

        log.info(" STEP 4: UPDATE PET ");

        response = petClient.updatePet(petId, petName, newStatus);

        log.info("REQUEST -> Pet ID: " + petId + ", New Status: " + newStatus);
        log.info("RESPONSE -> " + response.asString());
        log.info("STATUS CODE -> " + response.getStatusCode());

        response.then().statusCode(200);
    }


    // VALIDATE UPDATED STATUS

    @Then("the pet status should be {string}")
    public void validateUpdatedStatus(String expectedStatus) {

        log.info(" VALIDATING UPDATED STATUS ");

        response = petClient.getPet(petId);

        log.info("REQUEST -> Fetching updated pet using ID: " + petId);
        log.info("RESPONSE -> " + response.asString());
        log.info("STATUS CODE -> " + response.getStatusCode());

        response.then()
                .statusCode(200)
                .body("status", equalTo(expectedStatus));

        log.info("VALIDATION PASSED -> Updated Status: " + expectedStatus);
    }

    // STEP 5: DELETE PET

    @When("User delete the pet")
    public void deletePet() {

        log.info(" STEP 5: DELETE PET ");

        response = petClient.deletePet(petId);

        deleteStatusCode = response.getStatusCode();

        log.info("REQUEST -> Deleting Pet using ID: " + petId);
        log.info("DELETE STATUS CODE -> " + deleteStatusCode);

        response.then().statusCode(200);
    }

    // FINAL VALIDATION

    @Then("the pet should not exist")
    public void verifyDeletion() {

        log.info(" FINAL VALIDATION ");

        if (deleteStatusCode != 200) {
            throw new AssertionError("Expected DELETE status 200 but got " + deleteStatusCode);
        }

        log.info("DELETE VALIDATION PASSED (200)");

        response = petClient.getPet(petId);

        log.info("REQUEST -> Verifying deletion using Pet ID: " + petId);
        log.info("GET AFTER DELETE RESPONSE -> " + response.asString());
        log.info("GET AFTER DELETE STATUS CODE -> " + response.getStatusCode());

        response.then().statusCode(404);

        log.info("FINAL VALIDATION PASSED -> Pet not found (404)");
    }
}