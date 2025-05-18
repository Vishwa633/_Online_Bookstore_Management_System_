package com.example.bookstore.usermanagement;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        
        try {
            userRepository.addUser(user);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Registration failed: " + e.getMessage());
        }
    }

    public Optional<User> login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Login failed: Username is empty");
            return Optional.empty();
        }
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Login failed: Password is empty");
            return Optional.empty();
        }

        if (userRepository.validateUser(username, password)) {
            return userRepository.findByUsername(username);
        }
        
        System.out.println("Login failed: Invalid credentials for user " + username);
        return Optional.empty();
    }

    public void printAllUsers() {
        System.out.println("Current users in system:");
        for (User user : userRepository.getAllUsers()) {
            System.out.println(user.getUsername() + " - " + user.getRole());
        }
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(String username) {
        userRepository.deleteUser(username);
    }
} 