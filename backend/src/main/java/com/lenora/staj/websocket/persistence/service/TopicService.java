package com.lenora.staj.websocket.persistence.service;

import com.lenora.staj.websocket.persistence.model.Message;
import com.lenora.staj.websocket.persistence.model.Topic;
import com.lenora.staj.websocket.persistence.model.User;
import com.lenora.staj.websocket.persistence.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;


    public Topic createTopic(String topicName, String title, User creator) {
        Topic topic = new Topic();
        topic.setTopic(topicName);
        topic.setTitle(title);
        topic.setCreator(creator);
        return topicRepository.save(topic);
    }
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }
}
