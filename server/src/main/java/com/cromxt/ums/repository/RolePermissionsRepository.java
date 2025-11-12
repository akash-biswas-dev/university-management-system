package com.cromxt.ums.repository;

import com.cromxt.ums.dtos.db.UserPermissions;
import com.cromxt.ums.models.RolePermissionId;
import com.cromxt.ums.models.RolePermissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionsRepository extends JpaRepository<RolePermissions, RolePermissionId> {

  List<UserPermissions> findById_RoleName(String roleName);
}
