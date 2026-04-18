package clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetClient {
    static {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    // TC1 METHODS
    // CREATE PET
    public Response createPet(long id, String name, String status) {
        String body = "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/pet");
    }

    // UPDATE PET
    public Response updatePet(long id, String name, String status) {

        String body = "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";
        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .put("/pet");
    }

    // TC2 METHOD
    // GET PETS BY STATUS
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

    // TC4 METHODS
    // CREATE PET
    public Response createPet(Map<String, Object> body) {
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
    public Response updatePet(Map<String, Object> body) {
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

    // COMMON METHODS (USED BY ALL TEST CASES)
    // GET PET
    public Response getPet(long id) {
        return given()
                .get("/pet/" + id);
    }

    // DELETE PET
    public Response deletePet(long id) {
        return given()
                .delete("/pet/" + id);
    }
}
