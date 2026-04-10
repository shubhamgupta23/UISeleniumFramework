package com.utils;

import io.restassured.response.Response;

public class AuthUtils {

    private static String endpoint = "/login";

    private static String payload = """
            {
              "username": "emilys",
              "password": "emilyspass"
            }
            """;
    public static String getAuthToken(){
        Response res = APIUtils.post(endpoint,payload);
        return JSONUtils.readJsonString(JSONUtils.readJson(res.getBody().asString()),"$.accessToken");
    }

}
