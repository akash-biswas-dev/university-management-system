package com.cromxt.ums.dtos.requests;

public record UserCredentialDTO(
    String emailOrUsername,
    String password,
    Boolean rememberMe
) {

}
