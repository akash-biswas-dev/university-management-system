package com.cromxt.ums.dtos.requests;

public record RegisterEmployeeDTO(
        String email,
        String username,
        String password,
        String firstName,
        String lastName
) {
}
