package clients;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserClient {

    // CREATE USER
    public Response createUser(int id, String username, String firstName,
                               String lastName, String email, String password,
                               String phone, int userStatus) {

        Map<String, Object> body = new HashMap<>();

        body.put("id", id);
        body.put("username", username);
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("email", email);
        body.put("password", password);
        body.put("phone", phone);
        body.put("userStatus", userStatus);

        return given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/user");
    }

    // GET USER
    public Response getUser(String username) {
        return given()
                .when()
                .get("/user/" + username);
    }

    // LOGIN USER
    public Response loginUser(String username, String password) {
        return given()
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .get("/user/login");
    }

    // LOGOUT USER
    public Response logoutUser() {
        return given()
                .when()
                .get("/user/logout");
    }
}