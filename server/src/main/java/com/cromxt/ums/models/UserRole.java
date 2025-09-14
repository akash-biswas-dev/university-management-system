package com.cromxt.ums.models;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {
  USER,
  ADMIN;

  List<SimpleGrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
  }
}
