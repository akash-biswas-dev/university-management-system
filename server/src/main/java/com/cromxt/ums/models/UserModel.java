package com.cromxt.ums.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel implements UserDetails {

  @Id
  @Getter
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(unique = true)
  @Getter
  private String email;

  @Column(unique = true)
  private String username;

  private String password;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  @OneToOne(mappedBy = "userId")
  private UserProfile profile;

  private Boolean isLocked;

  private Boolean isEnabled;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.id;
  }


  public boolean isUserEnabled() {
    return this.isEnabled;
  }

  public boolean isUserLocked() {
      return this.isLocked;
  }

  public String getRealUsername() {
    return this.username;
  }

  public UserRole getUserRole() {
      return this.role;
  }
}
