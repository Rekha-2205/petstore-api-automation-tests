package clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PetClient {

    static {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    // CREATE PET
    public Response createPet(long id, String name, String status) {

        String body = "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";

        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("/pet");
    }

    // GET PET
    public Response getPet(long id) {
        return RestAssured
                .given()
                .get("/pet/" + id);
    }

    // UPDATE PET
    public Response updatePet(long id, String name, String status) {

        String body = "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";

        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(body)
                .put("/pet");
    }

    // DELETE PET
    public Response deletePet(long id) {
        return RestAssured
                .given()
                .delete("/pet/" + id);
    }
}