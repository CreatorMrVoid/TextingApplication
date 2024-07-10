package com.lenora.staj.websocket.ws.view;

import com.lenora.staj.websocket.persistence.model.Topic;
import lombok.Data;

@Data
public class WebSocketMessage {
    private String text;
    private String writer;
    private Topic topic;
}