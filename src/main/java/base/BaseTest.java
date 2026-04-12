package base;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;

public class BaseTest {

    static {
        // Base URL
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Relax SSL (important for stability)
        RestAssured.useRelaxedHTTPSValidation();

        // Avoid connection reuse issues
        System.setProperty("http.keepAlive", "false");

        // Timeouts (VERY IMPORTANT in real projects)
        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 5000)
                        .setParam("http.socket.timeout", 5000)
                );
    }
}