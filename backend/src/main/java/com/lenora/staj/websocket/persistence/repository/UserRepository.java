package com.lenora.staj.websocket.persistence.repository;

import com.lenora.staj.websocket.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findFirstByUsername(String username);
}

