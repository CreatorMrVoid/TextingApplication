package com.lenora.staj.websocket.rest.controller;

import com.lenora.staj.websocket.persistence.model.User;
import com.lenora.staj.websocket.persistence.service.UserService;
import com.lenora.staj.websocket.rest.request.UserPassView;
import com.lenora.staj.websocket.util.JWTUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserPassView userPass) {
        User user = userService.registerUser(userPass.getUsername(), userPass.getPassword());
        if(user != null && StringUtils.isNotBlank(userPass.getPassword())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserPassView userPass) {
        String jwt = jwtUtil.createJWT(userPass.getUsername());
        User user = userService.loginUser(userPass.getUsername(), userPass.getPassword());
        if(user != null && StringUtils.isNotBlank(userPass.getPassword())) {
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
