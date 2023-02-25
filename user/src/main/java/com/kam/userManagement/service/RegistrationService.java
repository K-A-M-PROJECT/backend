package com.kam.userManagement.service;


import com.kam.userManagement.models.user.User;
import com.kam.userManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<String> register(User user){

        if(this.userRepository.findByEmail(user.getEmail()).isEmpty())
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("This email is already used");

        if(this.userRepository.findByUsername(user.getUsername()).isEmpty())
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("This username is already used");

        this.userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User Created");
    }

}
