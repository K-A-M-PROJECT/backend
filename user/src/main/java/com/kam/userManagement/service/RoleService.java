package com.kam.userManagement.service;


import com.kam.userManagement.models.user.Role;
import com.kam.userManagement.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role save(Role role){
       return this.roleRepository.save(role);
    }

    public List<Role> getAllRoles(){
        return this.roleRepository.findAll();
    }

    public void deleteRoleById(int id){
        Role role = this.roleRepository.findRoleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        this.roleRepository.deleteById(id);
    }

    public Role findRoleById(int id){
        return this.roleRepository.findRoleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
    }




}
