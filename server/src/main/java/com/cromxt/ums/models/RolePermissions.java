package com.cromxt.ums.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RolePermissions {

  @EmbeddedId
  private RolePermissionId id;

  @MapsId(value = "roleName")
  @ManyToOne
  @JoinColumn(name = "role_name")
  private UserRole role;

  public RolePermissions(String roleName, Permissions permissions){
    this.id = new RolePermissionId(roleName, permissions);
    this.role = UserRole.builder().roleName(roleName).build();
  }

}
