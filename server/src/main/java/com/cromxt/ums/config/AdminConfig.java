package com.cromxt.ums.config;


import com.cromxt.ums.models.Permissions;
import com.cromxt.ums.models.RolePermissions;
import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.models.UserRole;
import com.cromxt.ums.repository.RolePermissionsRepository;
import com.cromxt.ums.repository.UserRepository;
import com.cromxt.ums.repository.UserRoleRepository;
import com.cromxt.ums.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AdminConfig {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserRoleRepository userRoleRepository;
  private final RolePermissionsRepository rolePermissionsRepository;


  private static final List<Permissions> ADMIN_GRANTED_PERMISSIONS = List.of(
    Permissions.PERMISSION_MANAGE
  );


  @Bean
  CommandLineRunner setUpAdmin() {
    return args -> {

      boolean isAdminUserCreated = userRoleRepository.findById("Admin").isPresent();

      if (isAdminUserCreated) {
        log.info("Admin user already exist");
        return;
      }


      final UserRole adminRole = userRoleRepository.save(UserRole.builder()
        .roleName("Admin")
        .description("System Admin")
        .build());

      ADMIN_GRANTED_PERMISSIONS.forEach(permissions -> {
          RolePermissions rolePermissions = new RolePermissions(
            adminRole.getRoleName(),
            permissions
          );
          rolePermissionsRepository.save(rolePermissions);
        }
      );

      UserModel adminUser = UserModel.builder()
        .username("admin")
        .email("admin@email.com")
        .password(passwordEncoder.encode("password"))
        .userRole(adminRole)
        .firstName("admin")
        .lastName("admin")
        .contactNumber("1234567890")
        .joinedOn(LocalDate.now())
        .dateOfBirth(LocalDate.of(2000, 1, 1))
        .isEnabled(false)
        .isLocked(false)
        .build();

      UserModel savedUser = userRepository.save(adminUser);

      log.info("System admin created with the username: {}", savedUser.getUsername());
    };
  }
}
