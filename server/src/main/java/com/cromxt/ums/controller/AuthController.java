package com.cromxt.ums.controller;

import com.cromxt.ums.dtos.requests.UserCredentials;
import com.cromxt.ums.dtos.responses.AuthTokensResponse;
import com.cromxt.ums.services.AuthService;
import com.cromxt.ums.services.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountLockedException;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    private static final String REFRESH_TOKEN = "refresh_token";


    @PostMapping
    public ResponseEntity<AuthTokensResponse> login(
            @RequestBody UserCredentials userCredentials,
            @RequestParam(name = "rememberMe", required = false, defaultValue = "false") Boolean rememberMe
    ) throws AccountLockedException {
        AuthTokensResponse tokens = authService.login(userCredentials, rememberMe);
        if(rememberMe) {
            Cookie cookie = new Cookie(REFRESH_TOKEN, tokens.refreshToken());
            cookie.setDomain(jwtService.getIssuer());
            cookie.setMaxAge(jwtService.getRefreshTokenAge());
            cookie.setPath("/api/v1/auth/refresh-token");
            cookie.setHttpOnly(true);
        }
        return new ResponseEntity<>(tokens, HttpStatus.CREATED);
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<AuthTokensResponse> refreshToken(HttpServletRequest request) throws AccountLockedException {
        Cookie refreshToken = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("refresh_token"))
                .findFirst()
                .orElse(null);
        if (refreshToken == null) {
            throw new InsufficientAuthenticationException("No refresh token found");
        }
        String token = refreshToken.getValue();
        return ResponseEntity.ok(authService.refreshAuthTokens(token));
    }

}
