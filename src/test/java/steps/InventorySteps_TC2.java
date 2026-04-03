package steps;

import clients.PetClient;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class InventorySteps_TC2 {

    private static final Logger log = LogManager.getLogger(InventorySteps_TC2.class);

    private final PetClient petClient = new PetClient();

    private Response inventoryResponse;
    private Response petListResponse;

    private int inventoryCount;
    private int petListCount;


    // STEP 1: GET INVENTORY

    @Given("user fetches inventory for {string}")
    public void getInventory(String status) {

        log.info(" STEP 1: GET /store/inventory ");

        inventoryResponse = petClient.getInventory();

        log.info("STATUS CODE : " + inventoryResponse.getStatusCode());
        log.info("RESPONSE : " + inventoryResponse.asString());

        inventoryResponse.then().statusCode(200);

        //  Primary extraction (simple way)
        inventoryCount = inventoryResponse.jsonPath().getInt(status);

        // Fallback logic (handles inconsistent API keys)
        if (inventoryCount == 0) {

            Map<String, Integer> inventoryMap = inventoryResponse.jsonPath().getMap("");

            for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(status)) {
                    inventoryCount += entry.getValue();
                }
            }
        }

        log.info("Inventory Count for [" + status + "] : " + inventoryCount);
    }


    // STEP 2: GET PET LIST

    @When("user fetches pets by status {string}")
    public void getPetsByStatus(String status) {

        log.info(" STEP 2: GET /pet/findByStatus ");

        petListResponse = petClient.getPetsByStatus(status);

        log.info("STATUS CODE: " + petListResponse.getStatusCode());

        petListResponse.then().statusCode(200);

        petListCount = petListResponse.jsonPath().getList("$").size();

        log.info("Pet List Count for [" + status + "] : " + petListCount);
    }

    // FINAL VALIDATION

    @Then("user validates count matches for {string}")
    public void validateCounts(String status) {

        log.info(" FINAL VALIDATION ");

        log.info("Inventory Count : " + inventoryCount);
        log.info("Pet List Count : " + petListCount);

        // Strict validation
        assertTrue(
                "Mismatch between inventory and pet list count",
                inventoryCount == petListCount
        );

        log.info("Validation Passed: Counts match exactly");
    }
}