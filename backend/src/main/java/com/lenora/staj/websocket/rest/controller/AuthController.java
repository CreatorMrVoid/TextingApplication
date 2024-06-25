package com.lenora.staj.websocket.rest.controller;

import com.lenora.staj.websocket.persistence.model.User;
import com.lenora.staj.websocket.persistence.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        User user = userService.registerUser(username, password);
        return user != null ? "Registration successful" : "Registration failed";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userService.loginUser(username, password);
        return "Login failed";
    }

    @GetMapping("/login")
    public String login2() {
        return "Login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout successful";
    }
}
