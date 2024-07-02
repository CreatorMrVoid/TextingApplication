package com.lenora.staj.websocket.persistence.service;

import com.lenora.staj.websocket.persistence.model.Message;
import com.lenora.staj.websocket.persistence.model.Topic;
import com.lenora.staj.websocket.persistence.model.User;
import com.lenora.staj.websocket.persistence.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
