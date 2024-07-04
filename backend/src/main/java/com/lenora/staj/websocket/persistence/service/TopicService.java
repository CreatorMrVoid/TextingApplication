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
    @Autowired
    private  UserService userService;

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

    public void likeTopic(String topicId, String username) {
        Topic topic = findById(topicId);
        if (topic != null) {
            Set<User> members = topic.getMembers();
            User user = userService.getUser(username);
            assert user != null;
            members.add(user);
            topicRepository.save(topic);
        } else {
            throw new NullPointerException("Topic couldn't be found");
        }
    }
    public Topic findById(String topicId) {
        return findById(UUID.fromString(topicId));
    }
    public Topic findById(UUID topicId) {
        return topicRepository.findById(topicId).orElse(null);
    }

}
