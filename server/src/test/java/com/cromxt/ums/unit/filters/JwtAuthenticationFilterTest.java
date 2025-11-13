package com.cromxt.ums.unit.filters;

import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.models.UserRole;
import com.cromxt.ums.services.AuthService;
import com.cromxt.ums.services.JwtService;
import com.cromxt.ums.services.impl.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {


  private JwtAuthenticationFilter filter;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain filterChain;

  private JwtService jwtService;

  private UserModel userModel;

  @BeforeEach
  void beforeEach(){
    this.jwtService = new JwtServiceImpl(
      "GLSAKgvljkAVJKASbjlblJVJSAKbvkjSKBJAbncJKSNKJCBASHBLJVABHHJVLHJhJAWBIULGIVbkj",
      1000L * 60 * 5,
      1000L * 60 * 60 * 24,
      "localhost"
    );
    this.filter = new JwtAuthenticationFilter(jwtService);

    this.userModel = UserModel.builder()
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
  }


  @Test
  void shouldRunOnceWhenAccessingASecureEndpoint() throws Exception {

    String accessToken = jwtService.generateToken(userModel, List.of(),new HashMap<>());

    when(request.getHeader("Authorization")).thenReturn(String.format("Bearer %s",accessToken));

    filter.doFilter(request, response, filterChain);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    assertNotNull(authentication);

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    assertEquals(userModel.getUsername(), userDetails.getUsername());

  }

  @Test
  void shouldGetContextClearWhenAuthorizationIsNull() throws Exception {

    when(request.getHeader("Authorization")).thenReturn(null);

    filter.doFilter(request, response, filterChain);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    assertNull(authentication);

  }

  @AfterEach
  void afterEach(){
    SecurityContextHolder.clearContext();
  }


}
