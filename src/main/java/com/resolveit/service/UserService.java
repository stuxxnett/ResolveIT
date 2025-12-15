package com.resolveit.service;

import com.resolveit.dto.UserRegisterRequest;
import com.resolveit.model.User;
import com.resolveit.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;   // Add PasswordEncoder

    // Constructor injection for UserRepository + PasswordEncoder
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // REGISTER USER
    public User register(UserRegisterRequest req) {

        // Check duplicate username
        if (userRepo.findByUsername(req.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setName(req.getName());
        user.setUsername(req.getUsername());

        // Store hashed password instead of plain text
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        user.setRole(req.getRole());

        return userRepo.save(user);
    }
}
