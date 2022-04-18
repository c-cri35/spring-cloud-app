package com.example.userservice.datasource;

import com.example.userservice.exception.DuplicateEmailException;
import com.example.userservice.exception.NotFoundException;
import com.example.userservice.exception.UserAlreadyExistsException;
import com.example.userservice.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Registrations {
    private final List<User> registeredUsers = new ArrayList<>();

    public Optional<User> getById(Long id) {
        return registeredUsers.stream()
                .filter(user -> user.getId().equals(id)).findFirst();
    }

    public List<User> getAll() {
        return new ArrayList<>(registeredUsers);
    }

    public User save(User user){
        getById(user.getId()).ifPresent(u -> {
            throw new UserAlreadyExistsException(u.getId());
        });
        if (registeredUsers.stream().anyMatch(u -> u.getEmail().equals(user.getEmail())))
            throw new DuplicateEmailException(user.getEmail());

        registeredUsers.add(user);
        return user;
    }

    public User update(User user){
        User existingUser = getById(user.getId()).orElseThrow(
                () -> new NotFoundException(User.class, user.getId()));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());

        existingUser.setAddress(user.getAddress());
        existingUser.setType(user.getType());

        return user;
    }

    public boolean delete(Long id){
        User user = getById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        return registeredUsers.remove(user);
    }
}
