package com.example.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.studentmanagement.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";   // opens login.html
    }

    
}