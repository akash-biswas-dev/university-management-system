package com.cromxt.ums.controller;

import com.cromxt.ums.config.SecurityConfig;
import com.cromxt.ums.dtos.requests.UserCredentials;
import com.cromxt.ums.dtos.responses.AuthTokensResponse;
import com.cromxt.ums.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
public class AuthControllerTest {

  private static final String BASE_URL = "/api/v1/auth";

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private AuthService authService;

  @MockitoBean
  private AuthenticationProvider authenticationProvider;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldCreateTokens() throws Exception {

    UserCredentials userCredentials = new UserCredentials("username", "password", true);


    AuthTokensResponse authTokensResponse = new AuthTokensResponse(
      UUID.randomUUID().toString(),
      UUID.randomUUID().toString());

    when(authService.login(any(UserCredentials.class))).thenReturn(authTokensResponse);

    mockMvc.perform(
        post(BASE_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(userCredentials))
      )
      .andExpect(status().isCreated())
      .andExpect(content().string(objectMapper.writeValueAsString(authTokensResponse)));
  }

  @Test
  void shouldGet404WhenGiveInvalidCredentials() throws Exception {
    UserCredentials userCredentials = new UserCredentials("username", "password", true);

    BadCredentialsException exception = new BadCredentialsException("User with " + userCredentials.emailOrUsername() + " not found");


    when(authService.login(userCredentials)).thenThrow(exception);

    mockMvc
      .perform(
        post(BASE_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(userCredentials))
      )
      .andExpect(status().isNotFound())
      .andExpect(header().string("X-Message", exception.getMessage()));
  }

}
