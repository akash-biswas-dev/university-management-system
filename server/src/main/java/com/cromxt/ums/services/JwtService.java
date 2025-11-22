package com.cromxt.ums.services;

import java.util.List;
import java.util.Map;

import com.cromxt.ums.models.Permissions;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {


    Integer getRefreshTokenAge();

    String getIssuer();

    UserDetails extractUserDetails(String token);


    String extractUserId(String refreshToken);

    String generateToken(UserDetails userDetails,
                         List<Permissions> permissions,
                         Map<String, Object> extraPayload);

    String generateRefreshToken(String userId);


}
