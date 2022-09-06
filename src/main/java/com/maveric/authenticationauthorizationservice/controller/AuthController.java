package com.maveric.authenticationauthorizationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception{
        User user = null;

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));

        }catch (BadCredentialsException badCredentialsException){
           throw new Exception("Incorrect email or password",badCredentialsException);
        }

        ResponseEntity<User> objectResponseEntity = userFeignService.getUserByEmail(authRequest.getEmail());
        user = objectResponseEntity.getBody();

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
