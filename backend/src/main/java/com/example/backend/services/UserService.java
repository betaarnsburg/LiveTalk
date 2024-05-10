package com.example.backend.services;

import com.example.backend.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    List<User> findUserByLastName(String lastName);

    void saveUser(User user);

    void deleteUser(Long id);

}
