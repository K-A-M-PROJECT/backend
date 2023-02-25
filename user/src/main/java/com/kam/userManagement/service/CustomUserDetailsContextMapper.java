package com.kam.userManagement.service;

import com.kam.userManagement.models.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserDetailsContextMapper implements UserDetailsContextMapper {

    @Autowired
    private DataSource dataSource;

    private static String loadUserByUsernameQuery = "select username, password, " +
            "enabled, nickname from public.user where username = ?";

    @Override
    public UserDetails mapUserFromContext(DirContextOperations dirContextOperations, String s, Collection<? extends GrantedAuthority> collection) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        final CustomUserDetails userDetails = new CustomUserDetails(
                dirContextOperations.getStringAttribute("uid"),
                "pass",
                Collections.EMPTY_LIST);

        jdbcTemplate.queryForObject(loadUserByUsernameQuery, new RowMapper<CustomUserDetails>() {

            @Override
            public CustomUserDetails mapRow(ResultSet resultSet, int i) throws SQLException {
                userDetails.setNickname(resultSet.getString("nickname"));
                return userDetails;
            }
        }, dirContextOperations.getStringAttribute("uid"));

        return userDetails;
    }

    @Override
    public void mapUserToContext(UserDetails userDetails, DirContextAdapter dirContextAdapter) {

    }
}
