package com.cromxt.ums.services.impl;

import com.cromxt.ums.dtos.db.UserPermissions;
import com.cromxt.ums.dtos.requests.UserCredentials;
import com.cromxt.ums.dtos.responses.AuthTokensResponse;
import com.cromxt.ums.exception.AccountNotEnabledException;
import com.cromxt.ums.exception.UserNotFoundException;
import com.cromxt.ums.models.Permissions;
import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.models.UserRole;
import com.cromxt.ums.repository.RolePermissionsRepository;
import com.cromxt.ums.services.AuthService;
import com.cromxt.ums.services.JwtService;
import com.cromxt.ums.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountLockedException;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final RolePermissionsRepository rolePermissionsRepository;
  private final PasswordEncoder passwordEncoder;


  @Override
  public AuthTokensResponse login(UserCredentials userCredentials)
    throws AccountLockedException,
    AccountNotEnabledException,
    BadCredentialsException {


    final UserModel user;
    try {
      user = userService.getUserModelByUsernameOrEmail(userCredentials.emailOrUsername());
    } catch (UserNotFoundException e) {
      log.error(e.getMessage());
      throw new UserNotFoundException("No user found with username or email: " + userCredentials.emailOrUsername());
    }

    boolean isPasswordMatched = passwordEncoder.matches(userCredentials.password(), user.getPassword());

    if (!isPasswordMatched) {
      throw new BadCredentialsException("Invalid credentials");
    }

    return generateAuthTokens(user);
  }

  private final JwtService jwtService;

  @Override
  public AuthTokensResponse refreshToken(String userId) {
    return null;
  }

  private AuthTokensResponse generateAuthTokens(UserModel user) throws AccountLockedException, AccountNotEnabledException {

    if (!user.isUserEnabled()) {
      throw new AccountNotEnabledException("Account not enabled");
    }

    if (user.isUserLocked()) {
      throw new AccountLockedException("Account locked");
    }

    UserRole userRole = user.getUserRole();

    List<Permissions> permissions =
      rolePermissionsRepository
        .findById_RoleName(userRole.getRoleName())
        .stream()
        .map(UserPermissions::getId_PermissionName)
        .toList();

    String authToken = jwtService.generateToken(
      user,
      permissions,
      new HashMap<>()
    );
    String refreshToken = jwtService.generateRefreshToken(user.getUserId());
    return new AuthTokensResponse(authToken, refreshToken);
  }

}
