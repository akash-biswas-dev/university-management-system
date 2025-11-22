package com.cromxt.ums.services;


import com.cromxt.ums.dtos.requests.UserCredentials;
import com.cromxt.ums.dtos.responses.AuthTokensResponse;
import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.models.UserRole;
import com.cromxt.ums.repository.RolePermissionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import javax.security.auth.login.AccountLockedException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthServiceTest {

  @MockitoBean
  private UserService userService;

  @MockitoBean
  private RolePermissionsRepository rolePermissionsRepository;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private AuthService authService;

  @Autowired
  private PasswordEncoder passwordEncoder;


  private final String password = "password";
  private final UserRole roleName = new UserRole("Admin", "Long description");

  private UserModel userModel;

  @BeforeEach
  void beforeEach() {
    this.userModel = UserModel.builder()
      .id(UUID.randomUUID())
      .email("test@gmail.com")
      .username("test")
      .password(passwordEncoder.encode(password))
      .userRole(roleName)
      .isNonLocked(true)
      .isEnabled(true)
      .dateOfBirth(LocalDate.now())
      .joinedOn(LocalDate.now())
      .build();
  }

  @Test
  void shouldCreateAuthTokensUserPassCorrectUserNameAndPassword() throws AccountLockedException {

    UserCredentials userCredentials = new UserCredentials("username", password);

    when(userService.loadUserByUsername(userCredentials.username())).thenReturn(userModel);
    when(rolePermissionsRepository.findById_RoleName(roleName.getRoleName())).thenReturn(List.of());

    AuthTokensResponse authTokensResponse = authService.login(userCredentials, true);

    String extractedJwtUserId = jwtService.extractUserId(authTokensResponse.accessToken());

    assertEquals(userModel.getUsername(), extractedJwtUserId);
  }

  @Test
  void shouldRefreshTokensWhenPassUserId() throws  AccountLockedException {
      String refreshToken = jwtService.generateRefreshToken(userModel.getId().toString());
      when(userService.loadUserByUserId(this.userModel.getId().toString()))
      .thenReturn(userModel);
    when(rolePermissionsRepository.findById_RoleName(roleName.getRoleName())).thenReturn(List.of());

    AuthTokensResponse authTokensResponse = authService.refreshAuthTokens(refreshToken);

    String extractedJwtUserId = jwtService.extractUserId(authTokensResponse.accessToken());

    assertEquals(userModel.getUsername(), extractedJwtUserId);
  }
}
