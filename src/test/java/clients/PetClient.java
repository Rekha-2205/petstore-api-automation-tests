package clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetClient {

    static {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.useRelaxedHTTPSValidation();
    }
    public Response createPet(long id, String name, String status) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("name", name);
        body.put("status", status);

        return given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/pet");
    }

    public Response getPet(long petId) {
        return given()
                .relaxedHTTPSValidation()
                .when()
                .get("/pet/" + petId);
    }

    public Response updatePet(long id, String name, String status) {

        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("name", name);
        body.put("status", status);

        return given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put("/pet");
    }

    public Response deletePet(long petId) {
        return given()
                .relaxedHTTPSValidation()
                .when()
                .delete("/pet/" + petId);
    }

    //  TC2 METHODS
    // Get Inventory
    public Response getInventory() {
        return given()
                .when()
                .get("/store/inventory");
    }

    // Get Inventory
    public Response getPetsByStatus(String status) {
        return given()
                .queryParam("status", status)
                .when()
                .get("/pet/findByStatus");
    }

//  TC3 METHODS

// Create User
public Response createUser(Map<String, Object> body) {
    return given()
            .header("Content-Type", "application/json")
            .body(body)
            .when()
            .post("/user");
}

// Get User by Username
public Response getUser(String username) {
    return given()
            .when()
            .get("/user/" + username);
}

// Login User
public Response loginUser(String username, String password) {
    return given()
            .queryParam("username", username)
            .queryParam("password", password)
            .when()
            .get("/user/login");
}
}