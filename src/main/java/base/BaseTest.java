package base;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;

public class BaseTest {

    static {
        // Base URL for all API requests
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Relax SSL validation (avoids HTTPS certificate issues)
        RestAssured.useRelaxedHTTPSValidation();

        // Disable keep-alive to avoid connection reuse issues
        System.setProperty("http.keepAlive", "false");

        // Add timeout configuration to prevent hanging requests
        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 5000) // 5 sec connection timeout
                        .setParam("http.socket.timeout", 5000)     // 5 sec response timeout
                );
    }
}