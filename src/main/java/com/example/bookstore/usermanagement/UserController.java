package com.example.bookstore.usermanagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Allow frontend requests
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            if (user == null) {
                return ResponseEntity.badRequest().body("User data cannot be null");
            }
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        try {
            if (loginRequest == null) {
                return ResponseEntity.badRequest().body("Login request cannot be null");
            }
            
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            
            if (username == null || username.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Username cannot be empty");
            }
            
            if (password == null || password.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Password cannot be empty");
            }

            Optional<User> userOpt = userService.login(username.trim(), password.trim());
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                // Create a response object without the password
                User responseUser = new User();
                responseUser.setId(user.getId());
                responseUser.setUsername(user.getUsername());
                responseUser.setEmail(user.getEmail());
                responseUser.setRole(user.getRole());
                return ResponseEntity.ok(responseUser);
            } else {
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            return ResponseEntity.status(500).body("An error occurred during login: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        Optional<User> userOpt = userService.findByUsername(username);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        User user = userOpt.get();
        if (user.getRole() != null && user.getRole().equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.badRequest().body("Admin account cannot be deleted");
        }
        userService.deleteUser(username);
        return ResponseEntity.ok("User deleted successfully");
    }
}
