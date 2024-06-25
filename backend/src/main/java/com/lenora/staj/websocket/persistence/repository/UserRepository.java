package com.lenora.staj.websocket.persistence.repository;

import com.lenora.staj.websocket.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}