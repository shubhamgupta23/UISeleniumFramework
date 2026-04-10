package com.utils;

import io.restassured.response.Response;

public class TokenManager {

    private static String token;
    private static int token_validity = 50 * 60 * 1000;
    private static long expiry;

    private TokenManager(){
        throw new UnsupportedOperationException("Operation not allowed");
    }

    public static String getToken(){
        if(token == null || isTokenExpired()){
            refreshToken();
        }
        return token;
    }

    public static synchronized void refreshToken(){
       token = AuthUtils.getAuthToken();
       expiry = System.currentTimeMillis() + token_validity;
    }

    public static boolean isTokenExpired(){
        return System.currentTimeMillis() > expiry;
    }



}
