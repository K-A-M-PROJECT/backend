package com.kam.userManagement.config;

import com.kam.userManagement.models.user.User;
import com.kam.userManagement.models.user.UserSession;
import com.kam.userManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;

import java.util.Arrays;

@Slf4j
@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userLdapRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        try{
            User userLdap = userLdapRepository.findByUsernameAndPassword(username,password);

            if(userLdap == null){
                throw new BadCredentialsException("Invalid username and password combination");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        UserSession userSession = new UserSession(username,"", Arrays.asList(new SimpleGrantedAuthority("USER")));
        return new UsernamePasswordAuthenticationToken(userSession,password,Arrays.asList(new SimpleGrantedAuthority("USER")));
    }

}