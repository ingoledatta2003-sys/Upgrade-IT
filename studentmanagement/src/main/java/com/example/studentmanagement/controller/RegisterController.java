package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.User;
import com.example.studentmanagement.service.RegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    // SHOW REGISTER PAGE
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // HANDLE REGISTER (ONLY ONE METHOD)
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {

        String message = registerService.registerUser(user);

        if (message.equals("User registered successfully!")) {
            return "redirect:/login";
        } else {
            model.addAttribute("msg", message);
            return "register";
        }
    }
}