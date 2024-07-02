package com.lenora.staj.websocket.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity(name = "Users") // veri tabanı için entity
@Data //Getter setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    @Column (length = 255)
    private String password;
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private Set<Message> messages;
    @OneToMany(mappedBy = "creator" )
    private Set<Topic> createdTopics;
    @ManyToMany
    private Set<Topic> subs;
}
