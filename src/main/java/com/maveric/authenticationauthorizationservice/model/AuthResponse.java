package com.maveric.authenticationauthorizationservice.model;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;

    private User user;


}
