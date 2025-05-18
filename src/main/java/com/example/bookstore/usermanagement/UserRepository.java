package com.example.bookstore.usermanagement;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.InitializingBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository implements InitializingBean {
    private final ConcurrentHashMap<String, User> usersByUsername;
    private final List<User> users;

    public UserRepository() {
        this.usersByUsername = new ConcurrentHashMap<>();
        this.users = new ArrayList<>();
    }

    @Override
    public void afterPropertiesSet() {
        // Add default admin if not exists
        if (!usersByUsername.containsKey("admin")) {
            User admin = new User("0", "admin", "admin@bookstore.com", "admin123");
            admin.setRole("ADMIN");
            addUser(admin);
            System.out.println("Admin user created");
        }
    }

    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        
        String username = user.getUsername().trim();
        if (usersByUsername.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        usersByUsername.put(username, user);
        users.add(user);
        System.out.println("Added user: " + username);
    }

    public Optional<User> findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return Optional.empty();
        }
        User user = usersByUsername.get(username.trim());
        System.out.println("Searching for user: " + username + ", found: " + (user != null));
        return Optional.ofNullable(user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public boolean validateUser(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        User user = usersByUsername.get(username.trim());
        if (user == null) {
            System.out.println("User not found: " + username);
            return false;
        }
        boolean isValid = user.getPassword().equals(password);
        System.out.println("Validating user: " + username + ", valid: " + isValid);
        return isValid;
    }

    public void deleteUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        User user = usersByUsername.remove(username.trim());
        if (user != null) {
            users.remove(user);
            System.out.println("Deleted user: " + username);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
} 