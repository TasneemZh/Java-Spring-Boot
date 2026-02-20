package com.tutorial.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.tutorial.model.User;

@Service
public class UserService {

    private final Map<String, User> usersByEmail = new ConcurrentHashMap<>();

    public User register(User user) {
        if (user == null || user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        usersByEmail.put(user.getEmail(), user);
        return user;
    }

    public User findByEmail(String email) {
        if (email == null || email.isBlank()) {
            return null;
        }

        return usersByEmail.get(email);
    }
}
