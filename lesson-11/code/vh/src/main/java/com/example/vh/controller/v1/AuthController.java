package com.example.vh.controller.v1;

import com.example.vh.models.User;
import com.example.vh.service.UserService;
import com.example.vh.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // register api --> username and password

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user.getUsername(), user.getPassword(), user.getRole());
    }

    @PostMapping("/login")
    private String login(@RequestBody User user) {
        User dbUser = userService.loadByUsername(user.getUsername());

        if (dbUser == null) {
            return "User not found";
        }

        return jwtUtil.generateToken(dbUser.getUsername(), dbUser.getRole());
    }

}
