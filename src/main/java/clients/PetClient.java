package clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetClient {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    // CREATE PET
    public Response createPet(long id, String name, String status) {

        return given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": " + id + ",\n" +
                        "  \"name\": \"" + name + "\",\n" +
                        "  \"status\": \"" + status + "\"\n" +
                        "}")
                .when()
                .post(BASE_URL + "/pet")
                .then()
                .extract()
                .response();
    }

    // GET PET
    public Response getPet(long petId) {

        return given()
                .when()
                .get(BASE_URL + "/pet/" + petId)
                .then()
                .extract()
                .response();
    }

    // UPDATE PET
    public Response updatePet(long id, String name, String status) {

        return given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": " + id + ",\n" +
                        "  \"name\": \"" + name + "\",\n" +
                        "  \"status\": \"" + status + "\"\n" +
                        "}")
                .when()
                .put(BASE_URL + "/pet")
                .then()
                .extract()
                .response();
    }

    // DELETE PET
    public Response deletePet(long petId) {

        return given()
                .when()
                .delete(BASE_URL + "/pet/" + petId)
                .then()
                .extract()
                .response();
    }
}