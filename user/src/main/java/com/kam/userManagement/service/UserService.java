package com.kam.userManagement.service;

import com.kam.userManagement.models.user.User;
import com.kam.userManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // business methods

    public Boolean authenticate(String u, String p) {
        return userRepository.findByUsernameAndPassword(u, p) != null;
    }

    public void create(String username, String password) {
        User newUser = new User(username,password);
        newUser.setId(LdapUtils.emptyLdapName());
        userRepository.save(newUser);
    }

    public void modify(String u, String p) {
        User user = userRepository.findByUsername(u);
        user.setPassword(p);
        userRepository.save(user);
    }

    public List<String> search(String u) {
        List<User> userList = userRepository
                .findByUsernameLikeIgnoreCase(u);

        if (userList == null) {
            return Collections.emptyList();
        }

        return userList.stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }
}
