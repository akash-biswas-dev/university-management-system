package com.cromxt.ums.services;

import com.cromxt.ums.dtos.requests.NewUserRequest;
import com.cromxt.ums.dtos.responses.UserResponse;
import com.cromxt.ums.models.UserModel;

public interface UserService {

  UserResponse addUser(NewUserRequest newUserRequest);

  UserModel getUserModelByUsernameOrEmail(String usernameOrEmail);
}
