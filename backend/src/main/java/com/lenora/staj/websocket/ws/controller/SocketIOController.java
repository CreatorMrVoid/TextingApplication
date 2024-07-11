package com.lenora.staj.websocket.ws.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ClientListeners;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.lenora.staj.websocket.persistence.model.Message;
import com.lenora.staj.websocket.rest.request.MessageSocketView;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketIOController {
    @Autowired
    private SocketIOServer socketServer;


    @Autowired
    public SocketIOController(SocketIOServer socketServer) {
        this.socketServer = socketServer;
    }
/*
    public void sendDashboardUpdate(String topicId, Message message) {
        socketServer.getRoomOperations(topicId).sendEvent("dashboard_update", message);
    }
*/
    public void onSendMessage (MessageSocketView message) {
        socketServer.getRoomOperations(message.getTopicid().toString()).sendEvent("message_sent", message);
    };
}
