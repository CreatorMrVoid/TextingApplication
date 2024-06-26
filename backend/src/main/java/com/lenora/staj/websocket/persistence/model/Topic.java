package com.lenora.staj.websocket.persistence.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID topicId;
    private String topic;
    @OneToMany (mappedBy = "user_id")
    private Set<Message> messages;
    private String title;
    private User creator;

}
