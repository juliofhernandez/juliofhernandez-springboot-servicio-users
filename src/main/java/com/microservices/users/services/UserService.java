package com.microservices.users.services;

import com.microservices.users.entities.Role;
import com.microservices.users.entities.User;
import com.microservices.users.entities.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    User  save(User user);
    Optional<User> update(UserDTO userDTO, Long id);
    void deleteById(Long id);
    Optional<User> setRole(Long id, Role role);
}
