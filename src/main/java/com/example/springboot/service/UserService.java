package com.example.springboot.service;


import com.example.springboot.model.User;

import java.util.List;

public interface UserService {

    User loadUserByUsername (String name);

    User fiendUserById(Long id);

    List<User> getAllUsers();

    void removeUser(Long id);

    void createOrUpdateUser(User user);

}
