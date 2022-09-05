package com.maveric.authenticationauthorizationservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

public class AuthSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests()
                .antMatchers("/error").permitAll();
        http
                .authorizeRequests()
                .antMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
        http
                .authorizeRequests()
                .anyRequest().authenticated();
        // apply the custom DSL which adds the custom filter
//        http
//                .apply(customDsl());
//        http
//                .addFilterBefore(new CustomAuthorizationFilter(jwtSecret), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
