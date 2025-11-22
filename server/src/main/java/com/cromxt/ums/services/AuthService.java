package com.cromxt.ums.services;

import com.cromxt.ums.dtos.requests.UserCredentials;
import com.cromxt.ums.dtos.responses.AuthTokensResponse;

import javax.security.auth.login.AccountLockedException;

public interface AuthService {

  AuthTokensResponse login(UserCredentials userCredentials, Boolean rememberMe) throws
    AccountLockedException;

  AuthTokensResponse refreshAuthTokens(String token) throws AccountLockedException;

}
