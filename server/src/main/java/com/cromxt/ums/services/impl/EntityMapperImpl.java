package com.cromxt.ums.services.impl;

import com.cromxt.ums.dtos.responses.UserDTO;
import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.services.EntityMapper;
import org.springframework.stereotype.Service;


@Service
public class EntityMapperImpl implements EntityMapper {
    @Override
    public UserDTO mapToUserDTO(UserModel user) {
        return new UserDTO(user.getRealUsername(),user.getEmail(),user.getUserRole());
    }
}
