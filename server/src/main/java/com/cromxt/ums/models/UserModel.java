package com.cromxt.ums.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserModel implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true, nullable = false, length = 100)
  private String email;

  @Getter(AccessLevel.NONE)
  @Column(unique = true, nullable = false, length = 100)
  private String username;

  @Column(nullable = false, length = 100)
  private String password;

  @Column(name = "first_name", nullable = false, length = 50)
  private String firstName;

  @Column(name = "middle_name", length = 50)
  private String middleName;

  @Column(name = "last_name", nullable = false, length = 50)
  private String lastName;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Column(name = "contact_number", nullable = false, length = 15)
  private String contactNumber;

  @ManyToOne
  @JoinColumn(name = "user_role")
  private UserRole userRole;

  @Getter(AccessLevel.NONE)
  @Column(name = "is_locked", nullable = false)
  private Boolean isNonLocked;

  @Getter(AccessLevel.NONE)
  @Column(name = "is_enabled", nullable = false)
  private Boolean isEnabled;

  @Column(name = "joined_on", nullable = false)
  private LocalDate joinedOn;

  @OneToOne(mappedBy = "userModel")
  private UserProfile profile;

  @Transient
  private Set<Permissions> permissions;

  public String getRealUsername(){
    return this.username;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
//    TODO: Create the roles from the permissions transient property.
    return null;
  }

  @Override
  public String getUsername() {
    return this.id.toString();
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.isNonLocked;
  }

  @Override
  public boolean isEnabled() {
    return this.isEnabled;
  }

}
