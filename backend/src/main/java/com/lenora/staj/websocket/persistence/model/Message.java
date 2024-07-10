package com.lenora.staj.websocket.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Messages")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(length = 255)
    private String text;
    @Column(name = "writer_name", length = 255)
    private String writer;
    @JoinColumn(name = "topic_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Topic topic;
    @CreationTimestamp
    private Timestamp createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
