package com.car.controller;

import com.car.model.User;
import com.car.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api") // API URL prefix
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Handle sign-up requests
    @PostMapping("/signup")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ResponseEntity<?> handleSignUp(@RequestBody User user) {
        System.out.println("Received user details: " + user);
        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email already registered");
        }

        // Save user to the database
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Sign-up successful", "userId", savedUser.getId()));
    }

    // Handle login requests
    @PostMapping("/login")
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    public ResponseEntity<?> handleLogin(@RequestBody User user) {
        // Check if the user exists with the provided credentials
        User existingUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("message", "Login successful", "userId", existingUser.getId()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
    }
}
