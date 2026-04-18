package steps;

import clients.PetClient;
import clients.StoreClient;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class InventorySteps_TC2 {

    private static final Logger log = LogManager.getLogger(InventorySteps_TC2.class);
    private final PetClient petClient = new PetClient();
    private final StoreClient storeClient = new StoreClient();
    private Response inventoryResponse;
    private Response petListResponse;
    private int inventoryCount;
    private int petListCount;

    /**
     *
     * @param status - pet status used to fetch inventory count
     */
    @Given("user fetches inventory for {string}")
    public void getInventory(String status) {
        log.info("STEP 1: GET INVENTORY");
        inventoryResponse = storeClient.getInventory();
        log.info("STATUS CODE: {}", inventoryResponse.getStatusCode());
        log.info("RESPONSE: {}", inventoryResponse.asString());
        inventoryResponse.then().statusCode(200);
        inventoryCount = inventoryResponse.jsonPath().getInt(status);
        if (inventoryCount == 0) {
            Map<String, Integer> inventoryMap = inventoryResponse.jsonPath().getMap("");

            for (Map.Entry<String, Integer> entry : inventoryMap.entrySet()) {
                if (entry.getKey().trim().equalsIgnoreCase(status)) {
                    inventoryCount += entry.getValue();
                }
            }
        }

        log.info("Inventory Count [{}]: {}", status, inventoryCount);
    }

    /**
     *
     * @param status - pet status used to fetch pets list
     */
    @When("user fetches pets by status {string}")
    public void getPetsByStatus(String status) {
        log.info("STEP 2: GET PETS BY STATUS");
        petListResponse = petClient.getPetsByStatus(status);
        log.info("STATUS CODE: {}", petListResponse.getStatusCode());
        petListResponse.then().statusCode(200);
        petListCount = petListResponse.jsonPath().getList("$").size();
        log.info("Pet List Count [{}]: {}", status, petListCount);
    }

    /**
     *
     * @param status - pet status used for final validation
     */
    @Then("user validates count matches for {string}")
    public void validateCounts(String status) {
        log.info("FINAL VALIDATION");
        log.info("Inventory Count [{}]: {}", status, inventoryCount);
        log.info("Pet List Count [{}]: {}", status, petListCount);

        assertEquals("Mismatch between inventory count and pet list count",
                inventoryCount, petListCount);

        log.info("VALIDATION PASSED");
    }
}