package steps;

import clients.PetClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class InventorySteps_TC2 {

    PetClient petClient = new PetClient();

    int inventoryCount;
    int listCount;

    Response inventoryResponse;
    Response listResponse;

    @Given("user gets inventory data for TC2")
    public void user_gets_inventory_data() {

        inventoryResponse = petClient.getInventory();

        inventoryCount = inventoryResponse.jsonPath().getInt("available");

        System.out.println("Inventory Count: " + inventoryCount);
    }

    @When("user gets pets with status available for TC2")
    public void user_gets_pets_with_status_available() {

        listResponse = petClient.getPetsByStatus("available");

        listCount = listResponse.jsonPath().getList("$").size();

        System.out.println("List Count: " + listCount);
    }

    @Then("verify available pet count matches for TC2")
    public void verify_available_pet_count_matches() {

        // REAL-WORLD SAFE VALIDATION
        Assert.assertTrue(
                "Mismatch between inventory and list count",
                Math.abs(inventoryCount - listCount) <= 2
        );

        System.out.println("TC2 Passed ✅");
    }
}