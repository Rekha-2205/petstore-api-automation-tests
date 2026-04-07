package base;

import io.restassured.RestAssured;

public class BaseTest {

    static {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.useRelaxedHTTPSValidation();

        // IMPORTANT: avoids connection reuse issues
        System.setProperty("http.keepAlive", "false");
    }
}