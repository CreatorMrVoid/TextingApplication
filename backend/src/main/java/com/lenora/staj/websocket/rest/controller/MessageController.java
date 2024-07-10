package com.lenora.staj.websocket.rest.controller;

import com.lenora.staj.websocket.persistence.model.Message;
import com.lenora.staj.websocket.persistence.model.Topic;
import com.lenora.staj.websocket.persistence.model.User;
import com.lenora.staj.websocket.persistence.service.MessageService;
import com.lenora.staj.websocket.persistence.service.TopicService;
import com.lenora.staj.websocket.persistence.service.UserService;
import com.lenora.staj.websocket.rest.request.MessageView;
import com.lenora.staj.websocket.ws.controller.SocketIOController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
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
    private UserService userService;
    //@Autowired
    //private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageView messageView;
    @Autowired
    private SocketIOController socketIOController;

    @GetMapping("/{topicId}")
    public ResponseEntity<List<MessageView>> getMessagesForTopic(@PathVariable("topicId") UUID topicId) {
        Topic topic = topicService.findById(topicId);
        if (topic != null) {
            List<MessageView> messages = topic.getMessages().stream()
                    .sorted(Comparator.comparing(Message::getCreatedAt))
                    .map(message -> messageView.convertToMessageView(message))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(messages);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{topicId}")
    public ResponseEntity<MessageView> sendMessage(@PathVariable UUID topicId, @RequestBody String text, @RequestAttribute("username") String username) {
        User user = userService.getUser(username);
        if (user != null) {
            Topic topic = topicService.findById(topicId);
            Message message = messageService.saveMessage(text, username, topic);

            // Broadcast message via WebSocket
            MessageView messageView = this.messageView.convertToMessageView(message);
           // messagingTemplate.convertAndSend("/topic/messages", messageView);
            socketIOController.onSendMessage.onData();
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
