package com.resolveit.controller;

import com.resolveit.dto.UserRegisterRequest;
import com.resolveit.model.User;
import com.resolveit.service.AuthService;
import com.resolveit.service.UserService;
import com.resolveit.dto.LoginRequest;
import com.resolveit.dto.UserResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    // LOGIN API
    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);  // 🚀 AuthService handles token + response
    }

    // REGISTER API
    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRegisterRequest request) {

        User saved = userService.register(request);

        // AuthController MUST NOT use token here
        return new UserResponse(
                saved.getId(),
                saved.getName(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getRole(),
                null        // token = null because signup does NOT auto-login
        );
    }
}
