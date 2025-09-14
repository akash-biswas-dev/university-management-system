package com.cromxt.ums.services.impl;

import com.cromxt.ums.dtos.responses.UserDTO;
import com.cromxt.ums.exception.AccountNotEnabledException;
import com.cromxt.ums.models.UserRole;
import com.cromxt.ums.services.EntityMapper;
import com.cromxt.ums.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cromxt.ums.dtos.requests.RegisterUserDTO;
import com.cromxt.ums.dtos.requests.UserCredentialDTO;
import com.cromxt.ums.dtos.responses.AuthTokensDTO;
import com.cromxt.ums.models.UserModel;
import com.cromxt.ums.repository.UserRepository;
import com.cromxt.ums.services.AuthService;
import com.cromxt.ums.services.UserService;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements AuthService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EntityMapper entityMapper;

    @Override
    public UserDTO registerUser(RegisterUserDTO registerUser) {
        UserModel user = UserModel.builder()
                .email(registerUser.email())
                .username(registerUser.username())
                .password(passwordEncoder.encode(registerUser.password()))
                .role(UserRole.USER)
                .build();
        UserModel savedUser = userRepository.save(user);
//        Send a confirm user account mail to user's email.

        return entityMapper.mapToUserDTO(savedUser);
    }

    @Override
    public AuthTokensDTO login(UserCredentialDTO userCredentials) {
        UsernamePasswordAuthenticationToken credentialToken = new UsernamePasswordAuthenticationToken(userCredentials.emailOrUsername(), userCredentials.password());

        final Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(credentialToken);
        }catch (AuthenticationException e) {
            log.error(e.getMessage());
            throw new BadCredentialsException(e.getMessage());
        }
        UserModel user = (UserModel) authentication.getPrincipal();

        System.out.println(user.isUserEnabled());
        if(!user.isUserEnabled()){
            throw new AccountNotEnabledException("User is not enabled");
        }

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user.getId());

        return new AuthTokensDTO(accessToken, refreshToken);
    }

}
