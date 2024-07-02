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
    private String topic;
    @OneToMany (mappedBy = "topic")
    private Set<Message> messages;
    private String title;
    @ManyToOne(optional = false)
    @JoinColumn(name = "creator_id")
    private User creator;
    //@OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
   // @JoinColumn(name = "subs_id")
    //private Set<User> subs;

    // https://www.baeldung.com/jpa-many-to-many
}
