package com.cromxt.ums.dtos.requests;

public record NewUserRequest(
    String username,
    String email,
    String password) {

}
