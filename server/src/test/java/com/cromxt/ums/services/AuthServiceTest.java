package com.cromxt.ums.services;

import com.cromxt.ums.dtos.db.UserPermissions;
import com.cromxt.ums.dtos.requests.UserCredentials;
import com.cromxt.ums.dtos.responses.AuthTokensResponse;
import com.cromxt.ums.models.Permissions;
import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.models.UserRole;
import com.cromxt.ums.repository.RolePermissionsRepository;
import com.cromxt.ums.services.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.security.auth.login.AccountLockedException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  @Mock
  private UserService userService;

  @Mock
  private RolePermissionsRepository rolePermissionsRepository;

  @Mock
  private JwtService jwtService;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private AuthServiceImpl authService;


  //  Test data which used to test the methods.
  private UserModel user;

  private List<UserPermissions> userPermissions;

  @BeforeEach
  void setUp() {
    this.user = UserModel.builder()
      .id(UUID.randomUUID())
      .email("test@gmail.com")
      .username("test")
      .password("password")
      .userRole(new UserRole("Admin", "Long description"))
      .isLocked(false)
      .isEnabled(true)
      .joinedOn(LocalDate.now())
      .build();

    this.userPermissions = List.of(
      getUserPermission(Permissions.PERMISSION_MANAGE),
      getUserPermission(Permissions.STUDENT_WRITE)
    );
  }

  private UserPermissions getUserPermission(Permissions permissions) {
    return new UserPermissions() {
      @Override
      public Permissions getId_PermissionName() {
        return permissions;
      }
    };
  }

  @Test
  void shouldCreateAuthTokensWhenUserIsFound() throws AccountLockedException {

    when(userService.getUserModelByUsernameOrEmail(user.getEmail()))
      .thenReturn(user);
    when(passwordEncoder.matches("password", user.getPassword()))
      .thenReturn(true);
    when(rolePermissionsRepository.findById_RoleName(user.getUserRole().getRoleName()))
      .thenReturn(this.userPermissions);

    List<Permissions> permissionsList = this.userPermissions.stream()
      .map(UserPermissions::getId_PermissionName)
      .toList();

    String accessToken = "Access Token";
    String refreshToken = "Refresh Token";

    when(jwtService.generateToken(user,permissionsList,new HashMap<>())).thenReturn(accessToken);
    when(jwtService.generateRefreshToken(user.getUserId())).thenReturn(refreshToken);


    UserCredentials userCredentials = new UserCredentials(
      user.getEmail(),
      user.getPassword(),
      true);

    AuthTokensResponse authTokens = authService.login(userCredentials);

    assertEquals(accessToken, authTokens.accessToken());
    assertEquals(refreshToken, authTokens.refreshToken());
  }
}
