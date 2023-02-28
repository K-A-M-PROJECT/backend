package com.kam.userManagement.repository;
import com.kam.userManagement.models.user.User;
import org.springframework.data.ldap.repository.LdapRepository;

public interface UserRepository extends LdapRepository<User> {
    User findByUsernameAndPassword(String username, String password);
}
