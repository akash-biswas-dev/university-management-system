package com.cromxt.ums.services;

import com.cromxt.ums.dtos.requests.NewUserRequest;
import com.cromxt.ums.dtos.responses.UserResponse;
import com.cromxt.ums.models.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  UserModel loadUserByUserId(String userId);

  UserResponse addUser(NewUserRequest newUserRequest);
}
