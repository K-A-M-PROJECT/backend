package com.kam.userManagement.controller;


import com.kam.userManagement.models.user.User;
import com.kam.userManagement.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/register")
public class RegistrationController {


    @Autowired
    public RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user){
        return this.registrationService.register(user);
    }




}
