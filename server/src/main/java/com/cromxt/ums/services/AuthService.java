package com.cromxt.ums.services;

import com.cromxt.ums.dtos.requests.RegisterUserDTO;
import com.cromxt.ums.dtos.requests.UserCredentialDTO;
import com.cromxt.ums.dtos.responses.AuthTokensDTO;
import com.cromxt.ums.dtos.responses.UserDTO;

public interface AuthService {

  UserDTO registerUser(RegisterUserDTO registerUser);

  AuthTokensDTO login(UserCredentialDTO userCredentials);

}
