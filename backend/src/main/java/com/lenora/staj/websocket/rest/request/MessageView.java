package com.lenora.staj.websocket.rest.request;

import com.lenora.staj.websocket.persistence.model.Message;
import com.lenora.staj.websocket.persistence.model.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component // BURASI ÖNEMLİ YOKSA DİĞER TARAFTA GÖRMÜYOR
public class MessageView {
    private String id;
    private String text;
    private String writer;

    public MessageView convertToMessageView(Message message) {
        MessageView messageView = new MessageView();
        messageView.setId(message.getId().toString());
        messageView.setText(message.getText());
        messageView.setWriter(message.getWriter());
        return messageView;
    }
}
