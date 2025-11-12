package com.cromxt.ums.dtos.responses;



public record UserResponse(
  String username,
  String email,
  String roleName
) {
}
