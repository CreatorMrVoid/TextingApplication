package com.lenora.staj.websocket.persistence.service;

import com.lenora.staj.websocket.persistence.model.Message;
import com.lenora.staj.websocket.persistence.model.Topic;
import com.lenora.staj.websocket.persistence.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Transactional
    public void test() {
        Topic t = topicRepository.findByText("aaa");

        List<Message> messages = t.getMessages();
    }
}
