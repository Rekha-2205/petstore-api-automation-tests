package basic;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetAllPetsTest1 {

    @Test
    public void getAllPets() {

        RestAssured.baseURI = "https://petstore.swagger.io";

        given()
                .when()
                .get("/v2/pet/findByStatus?status=available")
                .then()
                .statusCode(200)
                .log().all();
    }
}