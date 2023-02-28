package com.kam.userManagement.controller;


import com.kam.userManagement.models.user.User;
import com.kam.userManagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/register")
@AllArgsConstructor
@RestController
public class RegistrationController {


    @Autowired
    public UserService userService;

    @PostMapping
    public void register(@RequestBody User user){
        this.userService.create(user.getUsername(), user.getPassword());
    }




}
