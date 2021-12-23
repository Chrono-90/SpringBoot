package com.example.springboot.controller;

import com.example.springboot.model.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public String createOrUpdate(@ModelAttribute("new") User user) {
        user.setPassword(encoder(user.getPassword()));
        userService.createOrUpdateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String giveAllUsers(Model userModel) {
        List<User> someUsers = userService.getAllUsers();
        userModel.addAttribute("users", someUsers);
        return "admin";
    }

    @GetMapping("/fill")
    public String fillUserParametrs(Model userModel) {
        User user = new User();
        userModel.addAttribute("user", user);
        return "create&UpdateNewUser";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, Model userModel) {
        User user = userService.fiendUserById(id);
        userModel.addAttribute("user", user);
        return "create&UpdateNewUser";
    }
    private String encoder(String codeHash) {

        if (codeHash.length() < 60) {
            codeHash = passwordEncoder.encode(codeHash);
        }
        return codeHash;

    }
}
