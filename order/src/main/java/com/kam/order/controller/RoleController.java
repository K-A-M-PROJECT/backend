package com.kam.order.controller;

import com.kam.order.models.user.Role;
import com.kam.order.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/roles")
public class RoleController {

    private final RoleService roleService;


    @Operation(summary = "To get all roles from DB.")
    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(this.roleService.getAllRoles(), HttpStatus.OK);
    }

    @Operation(summary = "To get a role from DB by id")
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Role> getRoles(@PathVariable("id") int id) {
        return new ResponseEntity<>(this.roleService.findRoleById(id), HttpStatus.OK);
    }


    @Operation(summary = "To add a role to DB. You will add without id key of JSON or set Id = 0")
    @PostMapping
    public ResponseEntity<Role> addRoles(@RequestBody Role role) {
        Role savedRole =  this.roleService.save(role);
        return new ResponseEntity<>(savedRole, HttpStatus.OK);
    }

    @Operation(summary = "To update a role in DB.")
    @PutMapping
    public ResponseEntity<Role> updateRoles(@RequestBody Role role) {
        Role updatedRule =  this.roleService.save(role);
        return new ResponseEntity<>(updatedRule, HttpStatus.OK);
    }

    @Operation(summary = "To delete a role from DB by id")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable("id") int id) {
        this.roleService.deleteRoleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
