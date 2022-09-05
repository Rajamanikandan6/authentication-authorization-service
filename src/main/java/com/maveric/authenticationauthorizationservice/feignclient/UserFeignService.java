package com.maveric.authenticationauthorizationservice.feignclient;

import com.maveric.authenticationauthorizationservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "feignUser",url = "http://localhost:3005/api/v1")
public interface UserFeignService {

    @GetMapping("/users/getUserByEmail")
    User getUserByEmail(String email);
}
