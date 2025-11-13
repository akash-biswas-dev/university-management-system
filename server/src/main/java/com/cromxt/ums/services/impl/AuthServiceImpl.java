package com.cromxt.ums.services.impl;

import com.cromxt.ums.dtos.db.UserPermissions;
import com.cromxt.ums.dtos.requests.UserCredentials;
import com.cromxt.ums.dtos.responses.AuthTokensResponse;
import com.cromxt.ums.exception.AccountNotEnabledException;
import com.cromxt.ums.models.Permissions;
import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.models.UserRole;
import com.cromxt.ums.repository.RolePermissionsRepository;
import com.cromxt.ums.services.AuthService;
import com.cromxt.ums.services.JwtService;
import com.cromxt.ums.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountLockedException;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final RolePermissionsRepository rolePermissionsRepository;
  private final UserService userService;


  @Override
  public AuthTokensResponse login(UserCredentials userCredentials) throws AccountLockedException {

    UsernamePasswordAuthenticationToken token =
      new UsernamePasswordAuthenticationToken(userCredentials.username(),
        userCredentials.password());
    final Authentication authentication;

    try{
      authentication = authenticationManager.authenticate(token);
    }catch (AuthenticationException ex){
      log.error(ex.getMessage(), ex);
      throw new BadCredentialsException("Invalid credentials");
    }

    UserModel user = (UserModel) authentication.getPrincipal();

    if (!user.isEnabled()) {
      throw new AccountNotEnabledException("Account not enabled");
    }

    return generateAuthTokens(user);
  }

  private final JwtService jwtService;

  @Override
  public AuthTokensResponse refreshToken(String userId) throws AccountLockedException {
    UserModel user = userService.loadUserByUserId(userId);
    return generateAuthTokens(user);
  }

  private AuthTokensResponse generateAuthTokens(UserModel user) throws AccountLockedException {

    if (!user.isAccountNonLocked()) {
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
    String refreshToken = jwtService.generateRefreshToken(user.getUsername());
    return new AuthTokensResponse(authToken, refreshToken);
  }

}
