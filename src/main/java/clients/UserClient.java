package clients;

import io.restassured.response.Response;
import models.User;

import static io.restassured.RestAssured.given;

public class UserClient
{
    // CREATE USER
    public Response createUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .log().all()
                .when()
                .post("/user")
                .then()
                .log().all()
                .extract().response();
    }

    // GET USER
    public Response getUser(String username) {
        return given()
                .log().all()
                .when()
                .get("/user/" + username)
                .then()
                .log().all()
                .extract().response();
    }

    // LOGIN USER
    public Response loginUser(String username, String password) {
        return given()
                .queryParam("username", username)
                .queryParam("password", password)
                .log().all()
                .when()
                .get("/user/login")
                .then()
                .log().all()
                .extract().response();
    }
}
