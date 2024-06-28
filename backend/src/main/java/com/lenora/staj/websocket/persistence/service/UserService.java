package com.lenora.staj.websocket.persistence.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.lenora.staj.websocket.persistence.model.User;
import com.lenora.staj.websocket.persistence.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null ) {
            user = new User();
            user.setUsername(username);
            String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            user.setPassword(hashedPassword);
            return userRepository.save(user);
        } else return null;
    }

    /**
     * Finds the user via given username, verifies the password and returns the user if found, or null otherwise.
     * @param username user to login as
     * @param password password of user
     * @return User object or null
     */
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && StringUtils.isNotBlank(password)) {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword().toCharArray());
            if (result.verified) {
                return user;
            }
        }
        return null;
    }

    // databaseden kullanıcı sorgula
    public User getUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null ) {
            return null;
        } else
            return user;
    }
}

