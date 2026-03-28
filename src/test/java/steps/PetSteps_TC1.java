package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PetSteps_TC1 {

    private int petId;
    private Response response;
    private String baseUrl = "https://petstore.swagger.io/v2";
    private String name;
    private String status;

    public PetSteps_TC1() {
        RestAssured.baseURI = baseUrl;
    }

    // Step 1: Create Pet(POST)
    @Given("I create a pet with name {string} and status {string}")
    public void createPet(String name, String status) {

        this.name = name;
        this.status = status;

        petId = (int) (System.currentTimeMillis() % 100000);

        String requestBody = "{\n" +
                "\"id\": " + petId + ",\n" +
                "\"name\": \"" + name + "\",\n" +
                "\"status\": \"" + status + "\"\n" +
                "}";

        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/pet");

        response.then().statusCode(200);
    }

    // Step 2 & 3: Get Pet
    @When("I get the pet details")
    public void getPet() {

        response = given()
                .when()
                .get("/pet/" + petId);

        response.then().statusCode(200);
    }

    // Validation
    @Then("the pet name should be {string} and status should be {string}")
    public void validatePet(String expectedName, String expectedStatus) {

        response.then()
                .statusCode(200)
                .body("name", equalTo(expectedName))
                .body("status", equalTo(expectedStatus));
    }

    // Step 4: Update Pet
    @When("I update the pet status to {string}")
    public void updatePet(String updatedStatus) {

        String requestBody = "{\n" +
                "\"id\": " + petId + ",\n" +
                "\"name\": \"" + name + "\",\n" +
                "\"status\": \"" + updatedStatus + "\"\n" +
                "}";

        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("/pet");

        response.then().statusCode(200);

        this.status = updatedStatus;
    }

    // Validation after update
    @Then("the pet status should be {string}")
    public void validateUpdatedStatus(String expectedStatus) {

        given()
                .when()
                .get("/pet/" + petId)
                .then()
                .statusCode(200)
                .body("status", equalTo(expectedStatus));
    }

    // Step 5: Delete Pet
    @When("I delete the pet")
    public void deletePet() {

        response = given()
                .when()
                .delete("/pet/" + petId);

        response.then().statusCode(200);
    }

    // Final Validation
    @Then("the pet should not exist")
    public void verifyDeletion() {

        given()
                .when()
                .get("/pet/" + petId)
                .then()
                .statusCode(404);
    }
}