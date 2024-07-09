<template>
  <div class="q-pa-md row justify-center">
    <div style="width: 100%; max-width: 400px">
      <q-chat-message :name="props.writer" :text="[props.text]" sent />
    </div>
  </div>
</template>

<script setup lang="ts">
import { MessagesCardProps } from "src/types/types";
import { io } from "src/websocket/server.js";

const socket = io(); // Connect to the same server (adjust the URL if needed)

const props = defineProps<MessagesCardProps>();

//You can now emit and listen for events between the server and clients. For example:
// Server-side
io.on("connection", (socket) => {
  socket.on("message", (data) => {
    console.log(`Received message: ${data}`);
    // Broadcast the message to other clients
    socket.broadcast.emit("message", data);
  });
});

// Client-side
socket.emit("message", "Hello from the client!");
socket.on("message", (data) => {
  console.log(`Received message from server: ${data}`);
});
</script>
