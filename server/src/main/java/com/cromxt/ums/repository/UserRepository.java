package com.cromxt.ums.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cromxt.ums.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, String> {

  Optional<UserModel> findByEmailOrUsername(String email, String username);
}
