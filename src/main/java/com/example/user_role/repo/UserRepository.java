package com.example.user_role.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user_role.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
