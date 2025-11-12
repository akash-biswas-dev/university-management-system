package com.cromxt.ums.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserModel implements UmsUser{

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true, nullable = false, length = 100)
  private String email;

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
  private Boolean isLocked;

  @Getter(AccessLevel.NONE)
  @Column(name = "is_enabled", nullable = false)
  private Boolean isEnabled;

  @Column(name = "joined_on", nullable = false)
  private LocalDate joinedOn;

  @OneToOne(mappedBy = "userModel")
  private UserProfile profile;

  @Override
  public String getUserId() {
    return this.id.toString();
  }

  @Override
  public Boolean isUserEnabled() {
    return this.isEnabled;
  }

  @Override
  public Boolean isUserLocked() {
    return this.isLocked;
  }
}
