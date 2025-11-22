package com.cromxt.ums.models;


import lombok.Getter;

@Getter
public enum Permissions {
  SUPER_ADMIN("super:admin"),
  PERMISSION_MANAGE("permission:manage"),
  STUDENT_READ("student:read"),
  STUDENT_WRITE("student:write"),
  STUDENT_MANAGE("student:manage"),
  FACULTY_ADD("faculty:add");

  private final String permission;

  Permissions(String permission) {
    this.permission = permission;
  }
}
