package com.kam.order.repository;

import com.kam.order.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

      Optional<Role> findRoleById(int id);
      Optional<Role> deleteById(int id);
}
