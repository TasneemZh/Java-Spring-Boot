package com.tutorial.jdbc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.jdbc.model.User;
import com.tutorial.jdbc.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public void printAllUsers() {
        List<User> result = userRepo.fetchUsers();
        System.out.println("\nUserRepository: Fetching all users...\n\n" + result);
    }

    public void activateUser(Long id, boolean active) {
        int rows = userRepo.updateUserActiveStatus(active, id);
        System.out.println("\nUserRepository: Updating " + rows + " of users with id " + id + " to " + (active? "": "not ") + "active.");
    }

    public void printUserById(Long id) {
        User usr = userRepo.getUserById(id);
        System.out.println("\nUserRepository: Fetching a user by ID " + usr.getId() + "\n" + usr);
    }

}
