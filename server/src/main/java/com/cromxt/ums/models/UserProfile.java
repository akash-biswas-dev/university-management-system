package com.cromxt.ums.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.rmi.UnmarshalException;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "user_profiles")
public class UserProfile {

  @Id
  public UUID userId;

  @MapsId("userId")
  @OneToOne
  @JoinColumn(name = "user_id")
  private UserModel userModel;

  @Column(name = "first_name", nullable = false, length = 50)
  private String firstName;

  @Column(name = "middle_name", length = 50)
  private String middleName;

  @Column(name = "last_name", nullable = false, length = 50)
  private String lastName;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Enumerated(EnumType.STRING)
  @Column(name = "degree_type")
  private DegreeType degreeType;

  @Column(name = "degree_on",nullable = false,length = 100)
  private String degreeIn;

  @OneToOne(fetch =  FetchType.LAZY)
  @JoinColumn(name = "salary_name")
  private Salary salary;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "address_id")
  private Address address;

}
