package com.lenora.staj.websocket.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "Messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String text;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer_id")
    private User writer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;


}
