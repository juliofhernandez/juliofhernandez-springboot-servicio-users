package com.microservices.users.controllers;

import com.microservices.users.entities.Role;
import com.microservices.users.entities.User;
import com.microservices.users.entities.UserDTO;
import com.microservices.users.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(User.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("UserController getAllUsers GET request");
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        logger.info("UserController getUserById GET request with id: " + id);
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        logger.info("UserController getUserByUsername GET request with username: " + username);
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("UserController createUser POST request with user: " + user);
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        logger.info("UserController updateUser PUT request with id: " + id + " and userDTO: " + userDTO);
        return userService.update(userDTO, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("UserController deleteUser DELETE request with id: " + id);
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/setrole/{id}")
    public ResponseEntity<User> setRole(@PathVariable Long id, @RequestBody Role role) {
        logger.info("UserController setRole PUT request with id: " + id + " and role: " + role);
        return userService.setRole(id, role)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
