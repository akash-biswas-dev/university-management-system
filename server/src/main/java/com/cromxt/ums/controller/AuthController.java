package com.cromxt.ums.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cromxt.ums.dtos.requests.RegisterUserDTO;
import com.cromxt.ums.dtos.requests.UserCredentialDTO;
import com.cromxt.ums.dtos.responses.AuthTokensDTO;
import com.cromxt.ums.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

  private final AuthService authService;


  @PostMapping(value = "/register")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
    authService.registerUser(registerUserDTO);
  }

  @PostMapping
  public ResponseEntity<AuthTokensDTO> login(@RequestBody UserCredentialDTO userCredentialDTO) {
    AuthTokensDTO tokens = authService.login(userCredentialDTO);
    return new ResponseEntity<>(tokens, HttpStatus.CREATED);
  }

}
