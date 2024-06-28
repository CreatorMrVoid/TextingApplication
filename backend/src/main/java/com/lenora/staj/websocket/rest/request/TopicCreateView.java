package com.lenora.staj.websocket.rest.request;


import lombok.Data;

@Data
public class TopicCreateView {
    String topic;
    String title;
    String creator;
}
