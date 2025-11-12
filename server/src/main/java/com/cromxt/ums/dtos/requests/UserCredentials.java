package com.cromxt.ums.dtos.requests;

public record UserCredentials(
    String emailOrUsername,
    String password,
    Boolean rememberMe
) {

}
