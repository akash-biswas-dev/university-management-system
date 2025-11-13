package com.cromxt.ums.controller;

import com.cromxt.ums.exception.AccountNotEnabledException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cromxt.ums.dtos.requests.UserCredentials;
import com.cromxt.ums.dtos.responses.AuthTokensResponse;
import com.cromxt.ums.services.AuthService;

import lombok.RequiredArgsConstructor;

import javax.security.auth.login.AccountLockedException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping
  public ResponseEntity<AuthTokensResponse> login(
    @RequestBody UserCredentials userCredentials
  ) throws AccountLockedException {

    AuthTokensResponse tokens = authService.login(userCredentials);
    return new ResponseEntity<>(tokens, HttpStatus.CREATED);
  }

  @PostMapping(value = "/refresh-token")
  public ResponseEntity<AuthTokensResponse> refreshToken(Authentication authentication) throws AccountLockedException{
    String userId = (String) authentication.getPrincipal();
    return ResponseEntity.ok(authService.refreshToken(userId));
  }

}
