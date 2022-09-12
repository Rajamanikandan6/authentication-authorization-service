package com.maveric.authenticationauthorizationservice.feignclient;

import com.maveric.authenticationauthorizationservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "feignUser",url = "http://localhost:3005/api/v1")
public interface UserFeignService {

    @GetMapping("/users/getUserByEmail/{emailId}")
    ResponseEntity<User> getUserByEmail(@PathVariable String emailId);
}
