package com.lenora.staj.websocket.rest.controller;

import com.lenora.staj.websocket.persistence.model.Topic;
import com.lenora.staj.websocket.persistence.model.User;
import com.lenora.staj.websocket.persistence.service.TopicService;
import com.lenora.staj.websocket.persistence.service.UserService;
import com.lenora.staj.websocket.rest.request.TopicCreateView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lenora.staj.websocket.rest.response.TopicListView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/forum/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<TopicListView> getAllTopics() {
        return topicService.findAll().stream()
                .map(topic -> new TopicListView(
                        topic.getId().toString(),
                        topic.getTopic(),
                        topic.getTitle(),
                        topic.getCreator().getUsername()
                ))
                .collect(Collectors.toList());
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody TopicCreateView topicCreateView, @RequestAttribute("username") String username) {
        User user = userService.getUser(username);
        Topic topic = topicService.createTopic(topicCreateView.getTopic(), topicCreateView.getTitle(), user);
        if (user != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}