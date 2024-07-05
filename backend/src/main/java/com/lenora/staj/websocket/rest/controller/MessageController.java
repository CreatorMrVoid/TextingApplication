package com.lenora.staj.websocket.rest.controller;

import com.lenora.staj.websocket.persistence.model.Message;
import com.lenora.staj.websocket.persistence.model.Topic;
import com.lenora.staj.websocket.persistence.model.User;
import com.lenora.staj.websocket.persistence.repository.TopicRepository;
import com.lenora.staj.websocket.persistence.service.MessageService;
import com.lenora.staj.websocket.persistence.service.TopicService;
import com.lenora.staj.websocket.persistence.service.UserService;
import com.lenora.staj.websocket.rest.request.MessageView;
import com.lenora.staj.websocket.rest.response.TopicListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/forum/messages")
public class MessageController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageView messageView;
    @Autowired
    private UserService userService;

    @GetMapping("/{topicId}")
    public ResponseEntity<List<MessageView>> getMessagesForTopic(@PathVariable UUID topicId) {
        Topic topic = topicService.findById(topicId);
        if (topic != null) {
            List<MessageView> messages = topic.getMessages().stream()
                    .map(message -> messageView.convertToMessageView(message))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(messages);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{topicId}/send")
    public ResponseEntity<MessageView> sendMessage(@PathVariable UUID topicId,  @RequestParam String text, @RequestAttribute("username") String username) {
        User user = userService.getUser(username);
        Topic topic = topicService.findById(topicId);
        Message message = messageService.saveMessage(text, user, topic);

        MessageView messageView = messageView.convertToMessageView(message);

        return ResponseEntity.ok(messageView);

    }
}
