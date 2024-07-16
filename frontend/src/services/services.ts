// src/services/service.ts
import { Client, Message } from "@stomp/stompjs";

const stompClient = new Client({
  brokerURL: "ws://localhost:8081/ws",
  reconnectDelay: 5000,
  heartbeatIncoming: 4000,
  heartbeatOutgoing: 4000,
});

stompClient.onConnect = (frame) => {
  console.log("Connected: " + frame);
  stompClient.subscribe("/topic/messages", (message: Message) => {
    console.log(message.body);
    // Handle received message
  });
};

stompClient.onStompError = (frame) => {
  console.error("Broker reported error: " + frame.headers["message"]);
  console.error("Additional details: " + frame.body);
};

export const connect = () => {
  stompClient.activate();
};

export const sendMessage = (message: string, topicId: string) => {
  stompClient.publish({
    destination: "forum/messages/" + topicId,
    body: JSON.stringify({ from: "user", text: message }),
  });
};
