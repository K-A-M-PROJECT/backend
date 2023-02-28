package com.kam.userManagement.repository;
import com.kam.userManagement.models.user.User;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends LdapRepository<User> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    List<User> findByUsernameLikeIgnoreCase(String username);
}