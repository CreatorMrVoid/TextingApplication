package com.lenora.staj.websocket.persistence.service;

import com.lenora.staj.websocket.persistence.model.Topic;
import com.lenora.staj.websocket.persistence.model.User;
import com.lenora.staj.websocket.persistence.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;


    public Topic createTopic(String name, String description, User creator) {
        assert creator != null;
        Topic topic = new Topic();
        topic.setName(name);
        topic.setDescription(description);
        topic.setCreator(creator);
        HashSet<User> members = new HashSet<>();
        members.add(creator);
        topic.setMembers(members);
        return topicRepository.save(topic);
    }
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }


    public Topic likeTopic(String topicId, User user) {
        Topic topic = findById(topicId);
        Set<User> members = topic.getMembers();
        members.add(user);
        return topic;
    }
    public Topic findById(String topicId) {
        return findById(UUID.fromString(topicId));
    }
    public Topic findById(UUID topicId) {
        return topicRepository.findById(topicId).orElse(null);
    }

}
