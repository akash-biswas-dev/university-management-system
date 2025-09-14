package com.cromxt.ums.exception.handlers;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public void badCredentialExceptionHandler(RuntimeException exception, HttpServletResponse response){
        response.addHeader("Content-Type", "application/json");
        response.setHeader("Message", exception.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }
}


