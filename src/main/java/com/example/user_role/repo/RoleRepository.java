package com.example.user_role.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.user_role.entity.Role;
import com.example.user_role.entity.RoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
