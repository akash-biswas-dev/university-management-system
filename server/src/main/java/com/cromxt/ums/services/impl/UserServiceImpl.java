package com.cromxt.ums.services.impl;

import com.cromxt.ums.dtos.requests.NewUserRequest;
import com.cromxt.ums.dtos.responses.UserResponse;
import com.cromxt.ums.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.repository.UserRepository;
import com.cromxt.ums.services.UserService;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;


  @Override
  public UserModel getUserModelByUsernameOrEmail(String usernameOrEmail) {
    return userRepository
      .findUserModelByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
      .orElseThrow(
        () -> new UserNotFoundException("User with username or Email: " + usernameOrEmail + " not found")
      );
  }

  @Override
  public UserResponse addUser(NewUserRequest newUserRequest) {
    return null;
  }

}
