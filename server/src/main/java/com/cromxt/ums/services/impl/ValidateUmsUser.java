package com.cromxt.ums.services.impl;


import com.cromxt.ums.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class ValidateUmsUser {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;


}
