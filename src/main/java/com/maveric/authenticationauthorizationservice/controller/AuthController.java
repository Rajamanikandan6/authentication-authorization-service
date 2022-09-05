package com.maveric.authenticationauthorizationservice.controller;

import com.maveric.authenticationauthorizationservice.feignclient.UserFeignService;
import com.maveric.authenticationauthorizationservice.model.AuthRequest;
import com.maveric.authenticationauthorizationservice.model.AuthResponse;
import com.maveric.authenticationauthorizationservice.model.User;
import com.maveric.authenticationauthorizationservice.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception{

        final User user = userFeignService.getUserByEmail(authRequest.getEmail());

        try {

        }catch (BadCredentialsException badCredentialsException){
            throw new Exception("Incorrect Username or Password");
        }



        final String jwt = jwtService.generateToken(user);

        AuthResponse authResponse = getAuthResponse(jwt , user);

        return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    public AuthResponse getAuthResponse(String token ,User user){
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUser(user);
        authResponse.setToken(token);

        return authResponse;

    }


}
