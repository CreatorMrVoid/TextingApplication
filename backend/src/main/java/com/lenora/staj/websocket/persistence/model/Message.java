package com.lenora.staj.websocket.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "Messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String text;
    @ManyToOne(fetch = FetchType.EAGER)
    private User writer;


}
