package com.cromxt.ums.config;

import com.cromxt.ums.filters.RefreshTokenAuthentication;
import jakarta.servlet.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Slf4j
public class SecurityConfig {


    private static final String[] WHITE_LIST_URLS = {
            "/api/v1/auth/**"
    };

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(CsrfConfigurer::disable)
                .cors(CorsConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(WHITE_LIST_URLS).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionConfigurer->{
                    exceptionConfigurer.authenticationEntryPoint((
                      req,
                      resp,
                      e) -> {

                        log.error("Insufficient Authentication with message {} at PATH: {}", e.getMessage(), req.getRequestURI());
                        if(e instanceof InsufficientAuthenticationException) {
                            req.getRequestDispatcher("/").forward(req, resp);
                            return;
                        }
//                       Here only handle the exception which occur when make a request to a invalid path
                        req.getRequestDispatcher("/auth").forward(req, resp);
                    });
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder(12);
    }

}
