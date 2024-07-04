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
import java.util.Set;

@RestController
@RequestMapping("/api/forum/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

    @GetMapping("/liked") // LikedTopics için
    public List<TopicListView> getLikedTopics(@RequestAttribute("username") String username) {
        Set<Topic> topics = userService.getUser(username).getLikedTopics();
        List<TopicListView> views = topics.stream()
                .map(topic -> new TopicListView(
                        topic.getId().toString(),
                        topic.getName(),
                        topic.getDescription(),
                        topic.getCreator().getUsername()
                ))
                .toList();
        return views;
    }

    @GetMapping("/created")
    public List<TopicListView> getCreatedTopics(@RequestAttribute("username") String username) {
        Set<Topic> topics = userService.getUser(username).getCreatedTopics();
        List<TopicListView> views = topics.stream()
                .map(topic -> new TopicListView(
                        topic.getId().toString(),
                        topic.getName(),
                        topic.getDescription(),
                        topic.getCreator().getUsername()
                ))
                .toList();
        return views;
    }

    @GetMapping
    public List<TopicListView> getAllTopics() { // anasayfadaki tüm topicler için
        List<Topic> topics = topicService.findAll();
        List<TopicListView> views = topics.stream()
                .map(topic -> new TopicListView(
                        topic.getId().toString(),
                        topic.getName(),
                        topic.getDescription(),
                        topic.getCreator().getUsername()
                ))
                .toList();
        return views;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TopicCreateView topicCreateView, @RequestAttribute("username") String username) {
        User user = userService.getUser(username);
        Topic topic = topicService.createTopic(topicCreateView.getName(), topicCreateView.getDescription(), user);
        if (user != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/like")
    public ResponseEntity<?> like(@RequestAttribute("id") String topicId, @RequestAttribute("username") String username) { // @RequestParam mı kullanmalıyız
        topicService.likeTopic(topicId, username);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody TopicListView topicListView) { // @RequestBody String topicName
        String topicId = topicListView.getId();
        Topic topic = topicService.findById(topicId);
        //  topicService.getMethod(topicService.getClass())

        if (topic != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}