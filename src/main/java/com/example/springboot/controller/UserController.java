package com.example.springboot.controller;


import com.example.springboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;



    @GetMapping("/user")
    public String userByName(Model userModel) {
        userModel.addAttribute("user", userServiceImpl.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "user";
    }
    @GetMapping("/access_denied")
    public String acces() {
        return "access_denied";
    }
}