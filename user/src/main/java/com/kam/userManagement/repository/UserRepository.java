package com.kam.userManagement.repository;

import com.kam.userManagement.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean findByEmail(String email);
}
