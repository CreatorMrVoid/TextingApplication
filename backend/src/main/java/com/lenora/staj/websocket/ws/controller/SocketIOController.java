package com.lenora.staj.websocket.ws.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.lenora.staj.websocket.persistence.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketIOController {

    @Autowired
    private SocketIOServer socketServer;

    @Autowired
    public SocketIOController(SocketIOServer socketServer) {
        this.socketServer = socketServer;
        this.socketServer.addConnectListener(onUserConnectWithSocket);
        this.socketServer.addDisconnectListener(onUserDisconnectWithSocket);
        this.socketServer.addEventListener("messageSendToUser", Message.class, onSendMessage);
    }

    private final ConnectListener onUserConnectWithSocket = client -> {
        // User connected
        System.out.println("User connected: " + client.getSessionId());
    };

    private final DisconnectListener onUserDisconnectWithSocket = client -> {
        // User disconnected
        System.out.println("User disconnected: " + client.getSessionId());
    };
/*
    private final DataListener<Message> onSendMessage = (client, message, ackRequest) -> {
        // Send message to target user
        socketServer.getBroadcastOperations().sendEvent("message", message);
        // Optionally acknowledge the message
        ackRequest.sendAckData("Message sent successfully");
    };
 */

    public DataListener<Message> onSendMessage = new DataListener<Message>() {
        @Override
        public void onData(SocketIOClient client, Message message, AckRequest acknowledge) throws Exception {
            socketServer.getRoomOperations(message.getTopic().getId().toString()).sendEvent("message_sent", message);

        }
    };
}
