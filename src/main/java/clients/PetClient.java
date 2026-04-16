package clients;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetClient {

    // =========================
    // TC1 METHODS
    // =========================

    public Response createPet(long id, String name, String status) {

        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("name", name);
        body.put("status", status);

        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/pet");
    }

    public Response getPet(long petId) {
        return given()
                .when()
                .get("/pet/" + petId);
    }

    public Response updatePet(long id, String name, String status) {

        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("name", name);
        body.put("status", status);

        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put("/pet");
    }

    public Response deletePet(long petId) {
        return given()
                .when()
                .delete("/pet/" + petId);
    }

    // =========================
    // TC2 METHODS
    // =========================

    // Get Inventory
    public Response getPetsByStatus(String status) {
        return given()
                .queryParam("status", status)
                .log().all()
                .when()
                .get("/pet/findByStatus")
                .then()
                .log().all()
                .extract()
                .response();

    }

    // =========================
    // TC4 METHODS
    // =========================

        // CREATE PET

    public Response createPet_TC4(Map<String, Object> body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .log().all()
                .when()
                .post("/pet")
                .then()
                .log().all()
                .extract()
                .response();
    }

        // UPDATE PET

    public Response updatePet_TC4(Map<String, Object> body) {
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .log().all()
                .when()
                .put("/pet")
                .then()
                .log().all()
                .extract()
                .response();
    }

        //GET PET BY ID

    public Response getPetById_TC4(long petId) {
        return given()
                .log().all()
                .when()
                .get("/pet/" + petId)
                .then()
                .log().all()
                .extract()
                .response();
    }

        //GET PET BY STATUS

    public Response getPetsByStatus_TC4(String status) {
        return given()
                .queryParam("status", status)
                .log().all()
                .when()
                .get("/pet/findByStatus")
                .then()
                .log().all()
                .extract()
                .response();
    }
}