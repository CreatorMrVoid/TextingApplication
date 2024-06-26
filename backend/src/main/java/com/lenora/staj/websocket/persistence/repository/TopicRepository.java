package com.lenora.staj.websocket.persistence.repository;

import com.lenora.staj.websocket.persistence.model.Topic;
import com.lenora.staj.websocket.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {

}
