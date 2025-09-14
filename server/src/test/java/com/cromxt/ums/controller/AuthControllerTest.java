package com.cromxt.ums.controller;

import com.cromxt.ums.config.SecurityConfig;
import com.cromxt.ums.dtos.requests.RegisterUserDTO;
import com.cromxt.ums.dtos.requests.UserCredentialDTO;
import com.cromxt.ums.dtos.responses.AuthTokensDTO;
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
    void shouldRegisterUser() throws Exception {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO("username", "email", "password");

        String data = objectMapper.writeValueAsString(registerUserDTO);

        mockMvc.perform(
                post(BASE_URL + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(data)
                ).andExpect(status().isNoContent());
    }

    @Test
    void shouldCreateTokens() throws Exception {

        UserCredentialDTO userCredentialDTO = new UserCredentialDTO("username", "password", true);

        String accessToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();

        AuthTokensDTO authTokensDTO = new AuthTokensDTO(accessToken, refreshToken);

        when(authService.login(any(UserCredentialDTO.class))).thenReturn(authTokensDTO);

        mockMvc.perform(
                post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCredentialDTO))
                )
                .andExpect(status().isCreated())
                .andExpect(content().string(objectMapper.writeValueAsString(authTokensDTO)));
    }

    @Test
    void shouldGet404WhenGiveInvalidCredentials() throws Exception {
        UserCredentialDTO userCredentials = new UserCredentialDTO("username", "password", true);

        when(authService.login(userCredentials)).thenThrow(new BadCredentialsException("User with "+ userCredentials.emailOrUsername()+" not found"));

        mockMvc.perform(post(BASE_URL).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(userCredentials))
        ).andExpect(status().isNotFound())
                .andExpect(header().string("Message", "User with "+ userCredentials.emailOrUsername()+" not found"));
    }

}
