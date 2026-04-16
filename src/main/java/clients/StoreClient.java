package clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StoreClient {

    public Response getInventory() {
        return given()
                .log().all()
                .when()
                .get("/store/inventory")
                .then()
                .log().all()
                .extract()
                .response();
    }

}