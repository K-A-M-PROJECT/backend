package com.kam.userManagement.repository;

import com.kam.userManagement.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole(String role);

    Optional<Role> findRoleById(int id);
}
