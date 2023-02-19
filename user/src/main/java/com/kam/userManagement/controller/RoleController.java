package com.kam.userManagement.controller;



import com.kam.userManagement.models.user.Role;
import com.kam.userManagement.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/role")
public class RoleController {

    private final RoleService roleService;


    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Role>> getRole() {
        return new ResponseEntity<>(this.roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Role> getRole(@PathVariable("id") int id) {
        return new ResponseEntity<>(this.roleService.findRoleById(id), HttpStatus.OK);
    }


    @PostMapping(path = "/save")
    public ResponseEntity<Role> saveRol(@RequestBody Role role) {
        Role ro =  this.roleService.save(role);
        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable("id") int id) {
        this.roleService.deleteRoleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
