package com.cromxt.ums.config;

import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AdminConfigTest {

  @Autowired
  private UserRepository userRepository;


  @Test
  void shouldFindTheAdminUserByUsername(){
    Optional<UserModel> userModel = userRepository
      .findUserModelByUsername("admin");

    assertTrue(userModel.isPresent());
  }
}
