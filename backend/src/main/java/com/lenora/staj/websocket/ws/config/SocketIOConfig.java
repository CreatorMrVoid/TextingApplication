package com.lenora.staj.websocket.ws.config;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@org.springframework.context.annotation.Configuration
public class SocketIOConfig {

    @Value("${socket.host}")
    private String SOCKETHOST;

    @Value("${socket.port}")
    private int SOCKETPORT;

    private SocketIOServer server;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(SOCKETHOST);
        config.setPort(SOCKETPORT);
        config.setOrigin("*"); // Allow all origins (for development)

        server = new SocketIOServer(config);
        server.start();
        return server;
    }

    private void onUserConnectWithSocket() {
        server.addConnectListener(client -> {
            System.out.println("Client connected: " + client.getSessionId());
        });

        server.addEventListener("join_group", String.class, (client, topicId, ackSender) -> {
            client.joinRoom(topicId);
        });

        server.addDisconnectListener(client -> {
            System.out.println("Client disconnected: " + client.getSessionId());
        });
    }

    @PreDestroy
    public void stopSocketIOServer() {
        this.server.stop();
    }

}
