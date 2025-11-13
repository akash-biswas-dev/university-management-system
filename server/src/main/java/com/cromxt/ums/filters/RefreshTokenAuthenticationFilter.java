package com.cromxt.ums.filters;

import com.cromxt.ums.services.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class RefreshTokenAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain) throws ServletException, IOException {

    if(SecurityContextHolder.getContext().getAuthentication() != null){
      filterChain.doFilter(request, response);
      return;
    }

    Cookie[] cookies = request.getCookies();
    if(cookies == null){
      filterChain.doFilter(request, response);
      return;
    }

    Optional<Cookie> refreshTokenCookieOptional = Arrays.stream(cookies)
      .filter(cookie -> "refreshToken".equals(cookie.getName()))
      .findFirst();

    if(refreshTokenCookieOptional.isEmpty()){
      filterChain.doFilter(request, response);
      return;
    }

    String refreshToken = refreshTokenCookieOptional.get().getValue();

    final String userId;

    try{
      userId = jwtService.extractUserId(refreshToken);
    }catch (ExpiredJwtException ex){
      log.error("Refresh token expired");
      filterChain.doFilter(request, response);
      return;
    }catch (SignatureException ex){
      log.error("Refresh token signature invalid");
      filterChain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken authentication =
      new UsernamePasswordAuthenticationToken(userId,"no-password", List.of());
//    authentication.setAuthenticated(true);
    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return !request.getServletPath().equals("/api/v1/auth/refresh-token");
  }

}
