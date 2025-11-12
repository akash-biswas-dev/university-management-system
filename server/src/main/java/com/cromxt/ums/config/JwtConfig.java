package com.cromxt.ums.config;

import com.cromxt.ums.filters.RefreshTokenAuthentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import com.cromxt.ums.filters.JwtAuthenticationFilter;
import com.cromxt.ums.services.JwtService;
import com.cromxt.ums.services.impl.JwtServiceImpl;

@Configuration
public class JwtConfig {

  @Bean
  JwtService jwtService(Environment environment) {
    String secret = environment.getProperty("jwt.secret");
    String issuer = environment.getProperty("jwt.issuer");

    if (secret == null || secret.isEmpty() || issuer == null || issuer.isEmpty()) {
      throw new IllegalArgumentException("jwt.secret or jwt.issuer is empty");
    }

    Long expiration = 1000L * 60 * 5; // 5 min
    Long refreshTokenExpiration = 1000L * 60 * 60 * 24; // 1 day.
    return new JwtServiceImpl(secret, expiration, refreshTokenExpiration,issuer);
  }

  @Bean
  @Order(1)
  JwtAuthenticationFilter authenticationFilter(JwtService jwtService) {
    return new JwtAuthenticationFilter(jwtService);
  }
  RefreshTokenAuthentication refreshTokenAuthentication(JwtService jwtService){
    return new RefreshTokenAuthentication(jwtService);
  }
}
