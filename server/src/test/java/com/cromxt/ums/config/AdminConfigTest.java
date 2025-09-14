package com.cromxt.ums.config;

import com.cromxt.ums.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AdminConfigTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateAdminUser(){
        boolean isUserExist = userRepository.findByEmailOrUsername("admin", "admin").isPresent();
        assertTrue(isUserExist);
    }
}