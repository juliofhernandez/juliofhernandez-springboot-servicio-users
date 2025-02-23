package com.microservices.users.services;

import com.microservices.users.entities.Role;
import com.microservices.users.entities.User;
import com.microservices.users.entities.UserDTO;
import com.microservices.users.respositories.RoleRepository;
import com.microservices.users.respositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");
        optionalRole.ifPresent(roles::add);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> update(UserDTO userDTO, Long id) {
        return userRepository.findById(id).map(userFromDB -> {
            updateUserFields(userFromDB, userDTO);
            return userRepository.save(userFromDB);
        });
    }

    private void updateUserFields(User user, UserDTO userDTO) {
        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getEnabled() != null) {
            user.setEnabled(userDTO.getEnabled());
        } else {
            user.setEnabled(false); //Disable in case of null
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<User> setRole(Long id, Role role) {
        Optional<Role> roleOptional = roleRepository.findByName(role.getName());
        if (roleOptional.isPresent()) {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Set<Role> roles = user.getRoles();
                roles.add(role);
                user.setRoles(roles);
                return Optional.of(userRepository.save(user));
            }
        }
        return Optional.empty();
    }
}