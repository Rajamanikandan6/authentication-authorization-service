package com.maveric.authenticationauthorizationservice.constant;

public class ErrorMessageConstant {
    private ErrorMessageConstant(){

    }
    public static final String EMAIL_PASSWORD_ERROR = "Incorrect Email or Password";
    public static final String ERROR_URL = "/error";
    public static final String AUTH_URL = "/api/v1/auth/login/**";
    public static final String AUTH_URL_V1 = "http://localhost:8000/api/v1/auth/login/**";
}
