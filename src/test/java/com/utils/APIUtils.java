package com.utils;

import io.restassured.response.Response;

import java.util.Map;
import java.util.function.Supplier;

import static io.restassured.RestAssured.given;

public class APIUtils {

    private static final int MAX_COUNT = 3;
    public static Response postWithToken(String endpoint, String payload){
        return executeWithRetry(()->given()
                .spec(SpecBuilderUtils.getRequestSpec())
                        .body(payload)
                            .when()
                                .post(endpoint).then().extract().response());
    }

    public static Response getWithToken(String endpoint){
        return executeWithRetry(()->given()
                .spec(SpecBuilderUtils.getRequestSpec())
                .when()
                .get(endpoint).then().extract().response());
    }

    public static Response post(String endpoint, String payload){
        return given()
                .spec(SpecBuilderUtils.getRequestSpecWithoutToken())
                .body(payload)
                .when()
                .post(endpoint).then().extract().response();
    }

    public static Response get(String endpoint){
        return given()
                .spec(SpecBuilderUtils.getRequestSpecWithoutToken())
                .when()
                .get(endpoint).then().extract().response();
    }

    public static Response executeWithRetry(Supplier<Response> requestSupplier){
        Response response = requestSupplier.get();
        int retry_count = 0;
        while(response.getStatusCode()==401 && retry_count < MAX_COUNT){
            TokenManager.refreshToken();
            response = requestSupplier.get();
            retry_count++;
        }
        return response;
    }

}
