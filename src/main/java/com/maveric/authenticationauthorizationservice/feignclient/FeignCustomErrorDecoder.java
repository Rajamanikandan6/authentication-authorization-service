package com.maveric.authenticationauthorizationservice.feignclient;

import feign.codec.ErrorDecoder;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class FeignCustomErrorDecoder implements ErrorDecoder {

    @Override public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is5xxServerError()) {
            System.out.println(responseBody);
            return new Exception("Generic exception");
        } else if (responseStatus.is4xxClientError()) {
            System.out.println(responseBody);
            return new Exception("Generic exception");
        } else {
            return new Exception("Generic exception");
        }
    }
}
