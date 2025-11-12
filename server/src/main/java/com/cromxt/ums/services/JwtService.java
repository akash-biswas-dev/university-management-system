package com.cromxt.ums.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.cromxt.ums.models.Permissions;
import com.cromxt.ums.models.UmsUser;
import com.cromxt.ums.models.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

  UserDetails extractUserDetails(String token);


  String extractUserId(String refreshToken);

  String generateToken(UmsUser umsUser,
                       List<Permissions> permissions,
                       Map<String, Object> extraPayload);

  String generateRefreshToken(String userId);


  boolean isTokenExpired(String token);
}
