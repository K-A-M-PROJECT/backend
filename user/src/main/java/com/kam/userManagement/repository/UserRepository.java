package com.kam.userManagement.repository;

import com.kam.userManagement.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import javax.naming.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.naming.directory.Attributes;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private LdapTemplate ldapTemplate;

    private static final String USERS_BASE_DN = "ou=users,dc=example,dc=com";

    public void addUser(User user) {
        DirContextAdapter context = new DirContextAdapter(getUserDn(user.getUsername()));
        mapToContext(user, user.getPassword(), context);
        ldapTemplate.bind(context);
    }

    public User findByUsername(String username) {
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person"));
        filter.and(new EqualsFilter("uid", username));

        List<User> users = ldapTemplate.search(USERS_BASE_DN, filter.encode(), new UserAttributesMapper());
        if (users.size() == 1) {
            return users.get(0);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public User findByEmail(String email) {
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person"));
        filter.and(new EqualsFilter("mail", email));

        List<User> users = ldapTemplate.search(USERS_BASE_DN, filter.encode(), new UserAttributesMapper());
        if (users.size() == 1) {
            return users.get(0);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public void updateUsername(String username, String newUsername) {
        User user = findByUsername(username);
        String oldDn = getUserDn(username);
        String newDn = getUserDn(newUsername);

        DirContextOperations context = ldapTemplate.lookupContext(oldDn);
        context.setAttributeValue("uid", newUsername);
        context.setAttributeValue("dn", newDn);
        ldapTemplate.rename(oldDn, String.valueOf(context));
    }

    public void updatePassword(String username, String newPassword) {
        User user = findByUsername(username);
        DirContextOperations context = ldapTemplate.lookupContext(getUserDn(username));
        context.setAttributeValue("userPassword", encodePassword(newPassword));
        ldapTemplate.modifyAttributes(context);
    }

    public void updateEmail(String username, String newEmail) {
        User user = findByUsername(username);
        DirContextOperations context = ldapTemplate.lookupContext(getUserDn(username));
        context.setAttributeValue("mail", newEmail);
        ldapTemplate.modifyAttributes(context);
    }

    private String getUserDn(String username) {
        return "uid=" + username + "," + USERS_BASE_DN;
    }

    private void mapToContext(User user, String password, DirContextAdapter context) {
        context.setAttributeValues("objectclass",
                new String[] {"top", "person", "organizationalPerson", "inetOrgPerson"});

        context.setAttributeValue("uid", user.getUsername());
        context.setAttributeValue("mail", user.getEmail());
        context.setAttributeValue("cn", user.getFullName());
        context.setAttributeValue("userPassword", encodePassword(password));
    }

    private String encodePassword(String password) {
        // Use BCrypt to encode the password before storing it in the LDAP server
        return new BCryptPasswordEncoder().encode(password);
    }

    private static class UserAttributesMapper implements AttributesMapper<User> {
        @Override
        public User mapFromAttributes(Attributes attributes) throws NamingException {
            User user = new User();
            user.setUsername((String) attributes.get("uid").get());
            user.setEmail((String) attributes.get("mail").get());
            user.setFullName((String) attributes.get("cn").get());

            return user;
        }
    }

}
