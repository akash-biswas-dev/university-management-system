package com.cromxt.ums.services.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.cromxt.ums.models.Permissions;
import com.cromxt.ums.models.UmsUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.cromxt.ums.services.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtServiceImpl implements JwtService {

  private final String secret;
  private final String issuer;
  private final Long expiration;
  private final Long refreshTokenExpiration;

  private static final String AUTHORITIES = "authorities";
  private static final String USER_ENABLED = "isUserEnabled";
  private static final String USER_LOCKED = "isUserLocked";


  public JwtServiceImpl(
    String secret,
    Long expiration,
    Long refreshTokenExpiration,
    String issuer) {
    this.secret = secret;
    this.expiration = expiration;
    this.issuer = issuer;
    this.refreshTokenExpiration = refreshTokenExpiration;
  }

  @Override
  public UserDetails extractUserDetails(String token) {
    Claims claims = extractAllClaims(token);
    String userId = claims.getSubject();

    boolean enabled = claims.get(USER_ENABLED, Boolean.class);
    boolean accountNonLocked = claims.get(USER_LOCKED, Boolean.class);

    @SuppressWarnings("unchecked")
    List<String> authorityList = (List<String>) claims.get(AUTHORITIES, List.class);

    List<Permissions> permissionList = authorityList.stream().map(Permissions::valueOf).toList();

    List<SimpleGrantedAuthority> authorities = permissionList.stream().map(
      permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
      .toList();

    return new User(
      userId,
      "no_password",
      enabled,
      true,
      true,
      accountNonLocked,
      authorities);
  }

  @Override
  public String generateRefreshToken(String userId) {
    return buildToken(userId, new HashMap<>(), refreshTokenExpiration);
  }

  @Override
  public String extractUserId(String refreshToken) {
    return extractClaim(refreshToken, Claims::getSubject);
  }

  @Override
  public boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }


  @Override
  public String generateToken(
    UmsUser user,
    List<Permissions> permissions,
    Map<String, Object> extraClaims
  ) {
    Map<String, Object> extraPayload = new HashMap<>();

    List<String> authorities = getAuthorities(permissions);
    extraPayload.put(AUTHORITIES, authorities);
    extraPayload.put(USER_ENABLED, user.isUserEnabled());
    extraPayload.put(USER_LOCKED, user.isUserLocked());

    extraClaims.keySet().forEach(eachKey -> extraPayload.put(eachKey, extraClaims.get(eachKey)));
    return buildToken(user.getUserId(), extraPayload, expiration);
  }

  private static List<String> getAuthorities(List<Permissions> permissions) {
    return permissions.stream().map(Enum::name).toList();
  }

  private String buildToken(String userId, Map<String, Object> claims, Long expiredAfter) {
    return Jwts.builder()
      .setClaims(claims)
      .setIssuer(issuer)
      .setSubject(userId)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + expiredAfter))
      .signWith(getSignKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(getSignKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

}
