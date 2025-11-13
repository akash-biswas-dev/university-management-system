package com.cromxt.ums.unit.filters;

import com.cromxt.ums.filters.RefreshTokenAuthenticationFilter;
import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.models.UserRole;
import com.cromxt.ums.services.JwtService;
import com.cromxt.ums.services.impl.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RefreshTokenAuthenticationFilterTest {


  private static final String COOKIE_NAME = "refreshToken";

  private RefreshTokenAuthenticationFilter filter;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain filterChain;

  private JwtService jwtService;

  private String useId;
  private String token;

  @BeforeEach
  void beforeEach(){
    this.jwtService = new JwtServiceImpl(
      "GLSAKgvljkAVJKASbjlblJVJSAKbvkjSKBJAbncJKSNKJCBASHBLJVABHHJVLHJhJAWBIULGIVbkj",
      1000L * 60 * 5,
      1000L * 60 * 60 * 24,
      "localhost"
    );
    this.filter = new RefreshTokenAuthenticationFilter(jwtService);

    UserModel userModel = UserModel.builder()
      .id(UUID.randomUUID())
      .email("test@gmail.com")
      .username("test")
      .password("password")
      .userRole(new UserRole("Admin", "Long description"))
      .isNonLocked(true)
      .isEnabled(true)
      .dateOfBirth(LocalDate.now())
      .joinedOn(LocalDate.now())
      .build();
    this.token = jwtService.generateRefreshToken(userModel.getUsername());
    this.useId = userModel.getUsername();
    SecurityContextHolder.clearContext();
  }

  @AfterEach
  void afterEach(){
    SecurityContextHolder.clearContext();
  }

  @Test
  void shouldRunWhenAccessingRefreshTokenEndpoint() throws Exception {
    when(request.getServletPath()).thenReturn("/api/v1/auth/refresh-token");
    Cookie cookie = new Cookie(COOKIE_NAME, token);
    Cookie[] cookies = new Cookie[1];
    cookies[0] = cookie;
    when(request.getCookies()).thenReturn(cookies);


    filter.doFilter(request, response, filterChain);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    assertNotNull(authentication);
    String authenticatedUser = (String) authentication.getPrincipal();
    assertEquals(authenticatedUser, useId);
  }

  @Test
  void shouldExecuteSuccessfulWhenThePathIsNotEqualToRefreshTokenEndpoint() throws Exception {
    when(request.getServletPath()).thenReturn("/api/v1/secured");
    filter.doFilter(request, response, filterChain);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    assertNull(authentication);
  }


}
