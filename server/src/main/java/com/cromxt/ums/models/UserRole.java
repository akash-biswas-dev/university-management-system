package com.cromxt.ums.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Getter
@Setter
@Table(name = "user_role")
public class UserRole {
  @Id
  @Column(name = "name",length = 30)
  private String roleName;
  @Column(length = 100)
  private String description;
}
