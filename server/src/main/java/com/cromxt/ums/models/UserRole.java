package com.cromxt.ums.models;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {
    SYSTEM_ADMIN,
    HEAD_OF_UNIVERSITY,
    HEAD_OF_INSTITUTION,
    HEAD_OF_DEPARTMENT,
    TEACHER,
    STUDENT,
    USER;

    List<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }
}
