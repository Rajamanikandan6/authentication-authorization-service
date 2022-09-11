package com.maveric.authenticationauthorizationservice.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class AuthRequest {
    @Email
    private String email;

    @Min(3)
    private String password;
}
