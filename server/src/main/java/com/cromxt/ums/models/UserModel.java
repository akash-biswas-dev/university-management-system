package com.cromxt.ums.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
  private UUID id;

  @Getter
  @Column(unique = true, nullable = false, length = 100)
  private String email;

  @Column(unique = true, nullable = false, length = 100)
  private String username;

  @Column(nullable = false, length = 100)
  private String password;

  @ManyToOne
  @JoinColumn(name = "role_name")
  private UserRole roleName;

  @Column(name = "is_locked", nullable = false)
  private Boolean isLocked;

  @Column(name = "is_enabled", nullable = false)
  private Boolean isEnabled;

  @Column(name = "joined_on", nullable = false)
  private LocalDate joinedOn;

  @OneToOne(mappedBy = "userModel")
  private UserProfile profile;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
//    TODO: Fetch all the permissions from the role entity then create
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.id.toString();
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
