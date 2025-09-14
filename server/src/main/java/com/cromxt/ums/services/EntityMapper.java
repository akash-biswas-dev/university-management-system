package com.cromxt.ums.services;


import com.cromxt.ums.dtos.responses.UserDTO;
import com.cromxt.ums.models.UserModel;

public interface EntityMapper {

    UserDTO mapToUserDTO(UserModel user);
}
