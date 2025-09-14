package com.cromxt.ums.dtos.responses;

import com.cromxt.ums.models.UserRole;

public record UserDTO(
        String username,
        String email,
        UserRole role
) {
}
