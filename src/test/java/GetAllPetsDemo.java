package basic;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAllPetsDemo {

    @Test
    public void getAllPets() {

        RestAssured.baseURI = "https://petstore.swagger.io";

        given()
                .when()
                .get("/v2/pet/findByStatus?status=available")
                .then()
                .statusCode(200) //  assertion 1
                .body("status", everyItem(equalTo("available"))) //  assertion 2
                .log().all();
    }
}