package com.kam.order.repository;
import com.kam.order.models.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminById(long adminId);
    Optional<Admin> findAdminByEmail(String email);
    Optional<Admin> findAdminByUserName(String username);
    void deleteById(long AdminId);



}
