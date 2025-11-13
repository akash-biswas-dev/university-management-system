package com.cromxt.ums.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cromxt.ums.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

  Optional<UserModel> findUserModelByUsername(String username);

  String username(String username);

}
