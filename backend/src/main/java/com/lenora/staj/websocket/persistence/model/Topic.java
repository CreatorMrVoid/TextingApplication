package com.lenora.staj.websocket.persistence.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Objects;
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
    @JoinColumn(name = "creator_id") 
    private User creator;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "topic_members",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> members;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(id, topic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
