package com.example.userservice.service;

import com.example.userservice.datasource.Registrations;
import com.example.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final Registrations userRegistrations;

    @Autowired
    public UserServiceImpl(Registrations userRegistrations) {
        this.userRegistrations = userRegistrations;
    }

    @Override
    public List<User> getAllUsers() {
        return userRegistrations.getAll();
    }

    @Override
    public User saveUser(User user) {
        return userRegistrations.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRegistrations.update(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRegistrations.delete(id);
    }
}
