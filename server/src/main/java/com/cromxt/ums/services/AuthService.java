package com.cromxt.ums.services;

import com.cromxt.ums.dtos.requests.UserCredentials;
import com.cromxt.ums.dtos.responses.AuthTokensResponse;
import com.cromxt.ums.exception.AccountNotEnabledException;
import org.springframework.security.authentication.BadCredentialsException;

import javax.security.auth.login.AccountLockedException;

public interface AuthService {

  AuthTokensResponse login(UserCredentials userCredentials) throws
    AccountLockedException,
    AccountNotEnabledException,
    BadCredentialsException;

  AuthTokensResponse refreshToken(String userId) throws AccountLockedException;

}
