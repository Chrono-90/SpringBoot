package com.example.springboot.controller;


import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/user")
    public String userByName(Model userModel) {
        userModel.addAttribute("user", userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "user";
    }
    @GetMapping("/access_denied")
    public String acces() {
        return "access_denied";
    }
}