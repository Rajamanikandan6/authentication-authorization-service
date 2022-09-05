package com.maveric.authenticationauthorizationservice.service;

import org.springframework.stereotype.Service;

@Service
public class AuthManager {

    public boolean authenticate(String email,String password){
        return true;
    }
}
