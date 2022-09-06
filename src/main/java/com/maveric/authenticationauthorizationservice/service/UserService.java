package com.maveric.authenticationauthorizationservice.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new User("raja@gmail.com","$2a$10$qqwTNjcxZ/r57osHvK3.iOAHXEIZgKcuMLymDdFdQRFjkZR5ksUtS",new ArrayList<>());
    }
}
