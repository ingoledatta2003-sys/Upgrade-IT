package com.example.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.studentmanagement.model.User;
import com.example.studentmanagement.repository.UserRepository;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {

        // Check if username exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already exists!";
        }

        userRepository.save(user);
        return "User registered successfully!";
    }
}