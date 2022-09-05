package com.maveric.authenticationauthorizationservice.model;

import com.maveric.authenticationauthorizationservice.constant.Gender;
import lombok.Data;

@Data
public class User {
    private  String id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private String dateOfBirth;

    private Gender gender;

    private String role;

    private String password;
}
