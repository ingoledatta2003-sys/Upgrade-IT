package com.example.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.model.User;
import com.example.studentmanagement.repository.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateUser(String username, String password) {

        User user = userRepository.findByUsername(username);

        System.out.println("DB USER: " + user);

        if (user == null) {
            return false;
        }

        System.out.println("DB PASSWORD: " + user.getPassword());
        System.out.println("ENTERED PASSWORD: " + password);

        return user.getPassword().trim().equals(password.trim());
    }
}