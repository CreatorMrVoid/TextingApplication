package com.lenora.staj.websocket.persistence.service;

import com.lenora.staj.websocket.persistence.model.Message;
import com.lenora.staj.websocket.persistence.model.Topic;
import com.lenora.staj.websocket.persistence.model.User;
import com.lenora.staj.websocket.persistence.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public Message saveMessage(String text, String creator, Topic topic   ) {
        Message message = new Message();
        message.setText(text);
        assert creator != null;
        message.setWriter(creator);
        message.setTopic(topic);
        return messageRepository.save(message);
    }

    public Message findById(UUID id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(UUID id) {
        messageRepository.deleteById(id);
    }

}
