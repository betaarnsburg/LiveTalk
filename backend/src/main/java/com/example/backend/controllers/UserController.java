package com.example.backend.controllers;

import com.example.backend.entities.User;
import com.example.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livetalk/user")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id).orElseThrow());
    }
    @GetMapping
    public ResponseEntity<List<User>> getUserByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(userService.findUserByLastName(lastName));
    }
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        if (user.getId() != null) {
            return ResponseEntity.badRequest().build();
        } else {
            userService.saveUser(user);
            return ResponseEntity.ok().build();
        }
    }
    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        if(userService.findUserById(user.getId()).isPresent()) {
            userService.saveUser(user);
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if(userService.findUserById(id).isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.accepted().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
