package com.cromxt.ums.services.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.cromxt.ums.models.Permissions;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.cromxt.ums.services.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

  private final String secret;
  private final String issuer;
  private final Long expiration;
  private final Long refreshExpiration;

  private static final String AUTHORITIES = "authorities";
  private static final String USER_ENABLED = "isUserEnabled";
  private static final String USER_LOCKED = "isUserLocked";


    public JwtServiceImpl(Environment environment) {
        String secret = environment.getProperty("jwt.secret");
        String issuer = environment.getProperty("jwt.issuer");
        Long  expiration = environment.getProperty("jwt.expiration", Long.class);
        Long refreshExpiration = environment.getProperty("jwt.refresh-expiration", Long.class);

        if (secret == null || secret.isEmpty() || issuer == null || issuer.isEmpty() || expiration == null || refreshExpiration == null) {
            throw new IllegalArgumentException("Some Jwt properties are missing");
        }

        log.info("Jwt service configured with issuer: {}, expiration: {}, refreshExpiration {}", issuer, expiration, refreshExpiration);

        this.expiration = expiration;
        this.refreshExpiration = refreshExpiration;
        this.issuer = issuer;
        this.secret = secret;
    }

    @Override
    public Integer getRefreshTokenAge() {
        return (int) (this.refreshExpiration /60);
    }

    @Override
    public String getIssuer() {
        return this.issuer;
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
    return buildToken(userId, new HashMap<>(), refreshExpiration);
  }

  @Override
  public String extractUserId(String refreshToken) throws ExpiredJwtException {
    return extractClaim(refreshToken, Claims::getSubject);
  }

  @Override
  public String generateToken(
    UserDetails user,
    List<Permissions> permissions,
    Map<String, Object> extraClaims
  ) {
    Map<String, Object> extraPayload = new HashMap<>();

    List<String> authorities = getAuthorities(permissions);
    extraPayload.put(AUTHORITIES, authorities);
    extraPayload.put(USER_ENABLED, user.isEnabled());
    extraPayload.put(USER_LOCKED, user.isAccountNonLocked());

    extraClaims.keySet().forEach(eachKey -> extraPayload.put(eachKey, extraClaims.get(eachKey)));
    return buildToken(user.getUsername(), extraPayload, expiration);
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
