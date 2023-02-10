package com.kam.order.controller;
import com.kam.order.models.user.Admin;
import com.kam.order.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {

    private final AdminService adminService;


    @Operation(summary = "To get all admins from DB")
    @GetMapping
    public ResponseEntity<List<Admin>> getStudents() {
        return new ResponseEntity<>(this.adminService.getAllAdmins(), HttpStatus.OK);
    }

    @Operation(summary = "To get an admin from DB by id")
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Admin> getStudent(@PathVariable("id") long id) {
        return new ResponseEntity<>(this.adminService.getAdminById(id), HttpStatus.OK);
    }


    @Operation(summary = "To add an admin to DB, you will add it without id key of JSON or set Id = 0.")
    @PostMapping
    public ResponseEntity<Admin> addStudent(@RequestBody Admin admin) {
        Admin adm =  this.adminService.save(admin);
        return new ResponseEntity<>(adm, HttpStatus.OK);
    }

    @Operation(summary = "To update an admin in DB, you will add it with id key of JSON")
    @PutMapping
    public ResponseEntity<Admin> updateStudent(@RequestBody Admin admin) {
        Admin adm =  this.adminService.save(admin);
        return new ResponseEntity<>(adm, HttpStatus.OK);
    }

    @Operation(summary = "To delete an admin from DB by id")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") long id) {
        this.adminService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
