package com.lenora.staj.websocket.rest.request;

import com.lenora.staj.websocket.persistence.model.Message;
import com.lenora.staj.websocket.persistence.model.Topic;
import lombok.Data;

import java.util.UUID;

@Data
public class MessageSocketView extends MessageView {
    private UUID topicid;

    public static MessageSocketView convertToMessageView(Message message) {
        MessageSocketView messageView = new MessageSocketView();
        messageView.setText(message.getText());
        messageView.setWriter(message.getWriter());
        messageView.setTopicid(message.getTopic().getId());
        messageView.setId(message.getId().toString());
        return messageView;
    }
}
