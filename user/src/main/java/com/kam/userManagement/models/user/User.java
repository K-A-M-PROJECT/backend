package com.kam.userManagement.models.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;


@Entry(
        base = "ou=people,dc=mj,dc=com",
        objectClasses = { "person", "inetOrgPerson", "top" })
@Data
@NoArgsConstructor
public class User {
    @Id
    private Name id;

    private @Attribute(name = "cn") String username;
    private @Attribute(name = "sn") String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
