package com.cromxt.ums.services.impl;

import com.cromxt.ums.dtos.requests.NewUserRequest;
import com.cromxt.ums.dtos.responses.UserResponse;
import com.cromxt.ums.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.repository.UserRepository;
import com.cromxt.ums.services.UserService;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService , UserDetailsService {

  private final UserRepository userRepository;


  @Override
  public UserModel loadUserByUserId(String userId) {
    Optional<UserModel> userOptional = userRepository.findById(UUID.fromString(userId));
    return userOptional.orElseThrow(() -> new UserNotFoundException("User not found with the user id: "+ userId));
  }

  @Override
  public UserResponse addUser(NewUserRequest newUserRequest) {
    return null;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
      .findUserModelByUsername(username)
      .orElseThrow(
        () -> new UsernameNotFoundException("User with username or Email: " + username + " not found")
      );
  }
}
