package com.kam.userManagement.service;

import com.kam.userManagement.models.user.User;
import com.kam.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        // create a new instance of Spring Security's User object
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                "{noop}" + user.getPassword(),
                true, true, true, true,
                AuthorityUtils.createAuthorityList("USER"));
    }
}
