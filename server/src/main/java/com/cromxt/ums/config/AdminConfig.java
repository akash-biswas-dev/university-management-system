package com.cromxt.ums.config;


import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.models.UserRole;
import com.cromxt.ums.repository.UserRepository;
import com.cromxt.ums.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AdminConfig {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner setUpAdmin(){
        return args -> {
            boolean isAdminUserCreated = userRepository.findByEmailOrUsername("admin", "admin").isPresent();

            if(isAdminUserCreated){
                log.info("Admin user already exist");
            }

            UserModel adminUser = UserModel.builder()
                    .username("admin")
                    .email("admin@email.com")
                    .password(passwordEncoder.encode("password"))
                    .role(UserRole.SYSTEM_ADMIN)
                    .isEnabled(true)
                    .isLocked(false)
                    .build();

            UserModel savedUser = userRepository.save(adminUser);

            log.info("System admin created with the username: {}", savedUser.getRealUsername());
        };
    }
}
