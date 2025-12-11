package com.example.rideshare.controller;


import com.example.rideshare.dto.LoginRequest;
import com.example.rideshare.dto.RegisterRequest;
import com.example.rideshare.model.User;
import com.example.rideshare.service.UserService;
import com.example.rideshare.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authManager,
                          JwtUtil jwtUtil,
                          UserService userService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request.getUsername(), request.getPassword(), request.getRole());
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        // Authenticate user - throws exception if credentials are invalid
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userService.getByUsername(request.getUsername());

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return Map.of(
                "token", token,
                "role", user.getRole(),
                "username", user.getUsername()
        );
    }
}
