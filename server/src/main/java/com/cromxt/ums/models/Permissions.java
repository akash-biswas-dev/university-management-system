package com.cromxt.ums.models;


import lombok.Getter;

@Getter
public enum Permissions {
  STUDENT_READ("student:read"),
  STUDENT_WRITE("student:write"),
  STUDENT_MANAGE("student:manage");

  private final String permission;

  Permissions(String permission) {
    this.permission = permission;
  }
}
