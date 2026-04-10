package com.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SpecBuilderUtils {

    private static final String BASEURL = "https://dummyjson.com";
    private static final String PATH = "/auth";


    private SpecBuilderUtils(){
        throw new UnsupportedOperationException("Operation not allowed");
    }

    public static RequestSpecification getRequestSpecWithoutToken(){
        return new RequestSpecBuilder()
                .setBaseUri(BASEURL)
                .setBasePath(PATH)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder()
                .setBaseUri(BASEURL)
                .setBasePath(PATH)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer "+TokenManager.getToken())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

}
