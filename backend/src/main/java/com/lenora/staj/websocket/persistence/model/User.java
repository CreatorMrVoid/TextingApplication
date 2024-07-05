package com.lenora.staj.websocket.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Users") // veri tabanı için entity
@Data //Getter setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    @Column(length = 255)
    private String password;
    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
    private Set<Message> messages;
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private Set<Topic> createdTopics;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "members")
    private Set<Topic> likedTopics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
