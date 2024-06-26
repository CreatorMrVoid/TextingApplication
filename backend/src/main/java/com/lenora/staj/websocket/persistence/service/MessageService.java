package com.lenora.staj.websocket.persistence.service;

import com.lenora.staj.websocket.persistence.model.Message;
import com.lenora.staj.websocket.persistence.model.User;
import com.lenora.staj.websocket.persistence.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(String text, User writer) {
        Message message = new Message();
        message.setText(text);
        message.setWriter(writer);
        return messageRepository.save(message);
    }

    public Message findById(UUID id) {
        return messageRepository.findById(id).orElse(null);
    }

}
