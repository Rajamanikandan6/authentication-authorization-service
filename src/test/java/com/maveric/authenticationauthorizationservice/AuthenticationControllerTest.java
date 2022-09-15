package com.maveric.authenticationauthorizationservice;

import com.maveric.authenticationauthorizationservice.controller.AuthController;
import com.maveric.authenticationauthorizationservice.feignclient.UserFeignService;
import com.maveric.authenticationauthorizationservice.service.JWTService;
import com.maveric.authenticationauthorizationservice.service.UserService;
import feign.FeignException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockHttpServletRequestDsl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = AuthController.class,  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@Tag("Integration tests")
public class AuthenticationControllerTest {

    private static final String API_V1_AUTH = "http://localhost:3000/api/v1/auth";
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserFeignService userFeignService;

    @MockBean
    private JWTService jwtService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mvc;


    @Test
    void shouldReturnSuccessWhenTokenIsValid() throws Exception {
        String bearerToken = "Bearer eyJhbGciOiJIUzI1NJ9.eyJzdWIiOiJyYWphQGdtYWlsLmNvbSIsImV4cCI6MTY2MzI1NzA5MCwiaWF0IjoxNjYzMjIxMDkwfQ.wQl4ssfFUiRtqpsbVYGtFT1kS7MFMI6PwSJc3K5Jw2M";
        mvc.perform(get(API_V1_AUTH+"/validateToken").
                                header("Authorization", bearerToken)).
                andExpect(status().isOk())
                .andDo(print());


    }

}
