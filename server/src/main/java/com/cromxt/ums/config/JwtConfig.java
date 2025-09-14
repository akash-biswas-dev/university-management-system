package com.cromxt.ums.config;

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

    Assert.notNull(issuer, "issuer must not be null");
    Assert.notNull(secret, "secret must not be null");
    return new JwtServiceImpl(secret, issuer);
  }

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  JwtAuthenticationFilter authenticationFilter(JwtService jwtService) {
    return new JwtAuthenticationFilter(jwtService);
  }
}
