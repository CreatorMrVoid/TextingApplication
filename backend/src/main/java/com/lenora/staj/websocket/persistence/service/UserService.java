package com.lenora.staj.websocket.persistence.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.lenora.staj.websocket.persistence.model.User;
import com.lenora.staj.websocket.persistence.repository.UserRepository;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        user.setPassword(hashedPassword); // Ideally, the password should be hashed
        return userRepository.save(user);

    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword().toCharArray());
            if (result.verified) {
                return user;
            }
        }
        return null;
    }

}

