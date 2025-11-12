package com.cromxt.ums.dtos.requests;

public record UserProfileRequest(
  String firstName,
  String middleName,
  String lastName,
  String dateOfBirth,
  String gender,
  String phone,
  String address,
  String city,
  String state,
  String country,
  String pinCode
) {
}
