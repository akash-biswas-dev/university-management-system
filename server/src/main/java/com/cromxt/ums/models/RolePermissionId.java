package com.cromxt.ums.models;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RolePermissionId implements Serializable {

  private String roleName;
  @Column(name = "permission_name")
  @Enumerated(EnumType.STRING)
  private Permissions permissionName;
}
