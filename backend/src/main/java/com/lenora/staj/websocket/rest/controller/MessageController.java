package com.lenora.staj.websocket.rest.controller;

import com.lenora.staj.websocket.persistence.model.Message;
import com.lenora.staj.websocket.persistence.model.Topic;
import com.lenora.staj.websocket.persistence.repository.TopicRepository;
import com.lenora.staj.websocket.persistence.service.TopicService;
import com.lenora.staj.websocket.rest.response.TopicListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/forum/topics/messages")
public class MessageController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private Message message;
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/{topicId}/messages")
    public ResponseEntity<List<Message>> getMessagesForTopic(@PathVariable UUID topicId) {
        Optional<Topic> topic = topicRepository.findById(topicId);
        if (topic.isPresent()) {
            return ResponseEntity.ok(new ArrayList<>(topic.get().getMessages()));
            // return new ResponseEntity<>(HttpStatus.OK, new ArrayList<>(topic.get().getMessages()));
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
