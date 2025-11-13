package com.cromxt.ums.unit.services.impl;

import com.cromxt.ums.models.Permissions;
import com.cromxt.ums.models.UmsUser;
import com.cromxt.ums.services.JwtService;
import com.cromxt.ums.services.impl.JwtServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class JwtServiceUnitTest {


  private final JwtService jwtService =
    new JwtServiceImpl(
      "GLSAKgvljkAVJKASbjlblJVJSAKbvkjSKBJAbncJKSNKJCBASHBLJVABHHJVLHJhJAWBIULGIVbkj",
      1000L * 60 * 5,
      1000L * 60 * 60 * 24,
      "localhost"
    );


  private UserDetails user;

  @BeforeEach
  void setUp() {
    user = User.builder()
      .username(UUID.randomUUID().toString())
      .password("long-passowrd")
      .accountExpired(false)
      .accountLocked(false)
      .credentialsExpired(false)
      .disabled(false)
      .build();
  }

  @Test
  void shouldCreateATokenWithAnInstanceOfUmsUser() {
    String authToken = jwtService.generateToken(user, List.of(), new HashMap<>());

    UserDetails userDetails = jwtService.extractUserDetails(authToken);
    assertEquals(userDetails.getUsername(), user.getUsername());
  }

  @Test
  void shouldThrowExceptionWhenTokenExpired() throws InterruptedException {
    JwtService localService = new JwtServiceImpl(
      "GLSAKgvljkAVJKASbjlblJVJSAKbvkjSKBJAbncJKSNKJCBASHBLJVABHHJVLHJhJAWBIULGIVbkj",
      100L,
      1000L * 60 * 60 * 24,
      "localhost"
    );

    String authToken = localService.generateToken(user, List.of(), new HashMap<>());
    Thread.sleep(200);
    assertThrows(ExpiredJwtException.class, () ->
      localService.extractUserDetails(authToken)
    );
  }

  @Test
  void shouldHaveAllThePermissions() {

    List<Permissions> userPermissions = List.of(
      Permissions.STUDENT_MANAGE,
      Permissions.PERMISSION_MANAGE
    );

    String authToken = jwtService.generateToken(user,
      userPermissions,
      new HashMap<>());

    UserDetails userDetails = jwtService.extractUserDetails(authToken);

    assertEquals(userPermissions.size(), userDetails.getAuthorities().size());
  }

  @Test
  void shouldFailedToExtractUserDetailsWhenSecretIsDifferent() {
    JwtService localService = new JwtServiceImpl(
      "hVUASKJVLASJBLVAWULAWJHLLHblasBLSHVSLABHBVVHALBJHBv",
      100L,
      1000L * 60 * 60 * 24,
      "localhost"
    );

    String authToken = jwtService.generateToken(user, List.of(), new HashMap<>());
    assertThrows(SignatureException.class, () ->
      localService.extractUserDetails(authToken)
    );
  }

}
