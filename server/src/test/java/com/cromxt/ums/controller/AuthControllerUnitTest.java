package com.cromxt.ums.controller;

import com.cromxt.ums.dtos.requests.UserCredentials;
import com.cromxt.ums.dtos.responses.AuthTokensResponse;
import com.cromxt.ums.services.AuthService;
import com.cromxt.ums.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerUnitTest {

  private static final String BASE_URL = "/api/v1/auth";

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private AuthService authService;

  @MockitoBean
  private UserService userService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldCreateTokens() throws Exception {

    UserCredentials userCredentials = new UserCredentials("username", "password", true);

    AuthTokensResponse authTokensResponse = new AuthTokensResponse(
      UUID.randomUUID().toString(),
      UUID.randomUUID().toString());

    when(authService.login(userCredentials)).thenReturn(authTokensResponse);

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

    BadCredentialsException exception = new BadCredentialsException("User with " + userCredentials.username() + " not found");


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
