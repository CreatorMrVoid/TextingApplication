package com.lenora.staj.websocket.persistence.repository;

import com.lenora.staj.websocket.persistence.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findByText(String text);

    Message findById(UUID writerId);
}
