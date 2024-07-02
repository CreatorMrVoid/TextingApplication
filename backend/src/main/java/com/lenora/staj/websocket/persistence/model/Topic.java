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
    private UUID id;
    private String name;
    @OneToMany (mappedBy = "topic")
    private Set<Message> messages;
    private String description;
    @ManyToOne(optional = false)
    @JoinColumn(name = "creator_id")    // buradaki syntax doğru mu?
    private User creator;
   // @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> members;

    // https://www.baeldung.com/jpa-many-to-many
}
