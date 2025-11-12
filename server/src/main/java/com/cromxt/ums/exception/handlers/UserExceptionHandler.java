package com.cromxt.ums.exception.handlers;


import com.cromxt.ums.exception.AccountNotEnabledException;
import com.cromxt.ums.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

  @ExceptionHandler(value = {
    BadCredentialsException.class,
    UserNotFoundException.class
  })
  public void badCredentialExceptionHandler(RuntimeException exception, HttpServletResponse response) {
    response.addHeader("Content-Type", "application/json");
    response.setHeader("X-Message", exception.getMessage());
    response.setStatus(HttpStatus.NOT_FOUND.value());
  }

  @ExceptionHandler(value = AccountNotEnabledException.class)
  void accountNotEnabledExceptionHandle(RuntimeException exception, HttpServletResponse response) {

  }
}



