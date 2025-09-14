package com.cromxt.ums.dtos.requests;

public record RegisterUserDTO(
    String username,
    String email,
    String password) {

}
