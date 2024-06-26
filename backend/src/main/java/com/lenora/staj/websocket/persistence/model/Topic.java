package com.lenora.staj.websocket.persistence.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID topicId;
    private String topic;
    private List<Message> messages;

}
