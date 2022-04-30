package com.example.userservice.service;

import com.example.userservice.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User saveUser(User user);

    User updateUser(User user);

    boolean deleteUser(Long id);
}
