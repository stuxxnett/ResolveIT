package com.resolveit.service;

import com.resolveit.model.User;
import com.resolveit.dto.LoginRequest;
import com.resolveit.dto.UserResponse;
import com.resolveit.repository.UserRepository;
import com.resolveit.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;  // ✅ ADD THIS


    public AuthService(UserRepository userRepo,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {   // ✅ INJECT HERE
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;     // ✅ ASSIGN HERE
    }

    public UserResponse login(LoginRequest request) {

        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // ✅ Generate JWT Token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getUsername(),

                user.getEmail(),
                user.getRole(),
                token  // ✅ Send token to frontend
        );
    }
}
