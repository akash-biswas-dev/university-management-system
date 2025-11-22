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
import org.springframework.mock.env.MockEnvironment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class JwtServiceUnitTest {


    private JwtService jwtService;


    private UserDetails user;

    @BeforeEach
    void setUp() {
        MockEnvironment env = new MockEnvironment();
        env.setProperty("jwt.secret", "GLSAKgvljkAVJKASbjlblJVJSAKbvkjSKBJAbncJKSNKJCBASHBLJVABHHJVLHJhJAWBIULGIVbkj");
        env.setProperty("jwt.expiration", "300000");
        env.setProperty("jwt.refresh-expiration", "86400000");
        env.setProperty("jwt.issuer", "localhost");

        this.jwtService = new JwtServiceImpl(env);

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
        MockEnvironment env = new MockEnvironment();
        env.setProperty("jwt.secret", "GLSAKgvljkAVJKASbjlblJVJSAKbvkjSKBJAbncJKSNKJCBASHBLJVABHHJVLHJhJAWBIULGIVbkj");
        env.setProperty("jwt.expiration", "100");
        env.setProperty("jwt.refresh-expiration", "86400000");
        env.setProperty("jwt.issuer", "localhost");

        JwtService localService = new JwtServiceImpl(env);

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

        MockEnvironment env = new MockEnvironment();
        env.setProperty("jwt.secret", "GALSKgvljkAVJKASbjlblJVJSAKbvkjSKBJAbncJKSNKJCBASHBLJVABHHJVLHJhJAWBIULGIVbkj");
        env.setProperty("jwt.expiration", "100");
        env.setProperty("jwt.refresh-expiration", "86400000");
        env.setProperty("jwt.issuer", "localhost");
        JwtService localService = new JwtServiceImpl(env);

        String authToken = jwtService.generateToken(user, List.of(), new HashMap<>());
        assertThrows(SignatureException.class, () ->
                localService.extractUserDetails(authToken)
        );
    }

}
