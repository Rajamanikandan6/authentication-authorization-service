package com.maveric.authenticationauthorizationservice.exception;

import com.maveric.authenticationauthorizationservice.constant.ErrorMessageConstant;
import com.maveric.authenticationauthorizationservice.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> internalServerError(Exception exception){
        Error error = getError(String.valueOf(ErrorMessageConstant.EMAIL_PASSWORD_ERROR),String.valueOf(HttpStatus.BAD_REQUEST));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private Error getError(String message , String code){
        Error error = new Error();
        error.setCode(code);
        error.setMessage(message);
        return error;

    }
}
