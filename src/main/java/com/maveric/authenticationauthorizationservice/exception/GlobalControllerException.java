package com.maveric.authenticationauthorizationservice.exception;

import com.maveric.authenticationauthorizationservice.controller.AuthController;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalControllerException {
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<AuthController.ConnValidationResponse> internalServerError(HttpServerErrorException.InternalServerError exception){
        ResponseEntity<AuthController.ConnValidationResponse> connValidationResponse = getResponse();
        return connValidationResponse;
    }


    public ResponseEntity<AuthController.ConnValidationResponse> getResponse() {

        return ResponseEntity.ok(AuthController.ConnValidationResponse.builder().status("Not Valid").methodType(HttpMethod.GET.name())
                .email(null)
                .isAuthenticated(false).build());
    }
}
